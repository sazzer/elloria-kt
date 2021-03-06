package uk.co.grahamcox.elloria.webapp.authentication

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.http.ResponseEntity
import org.springframework.http.HttpStatus
import org.springframework.http.HttpHeaders
import java.net.URI
import org.springframework.web.util.UriComponentsBuilder
import uk.co.grahamcox.elloria.authentication.oauth2.TokenIssuer
import uk.co.grahamcox.elloria.authentication.oauth2.Scopes
import uk.co.grahamcox.elloria.authentication.oauth2.IssuedToken
import uk.co.grahamcox.elloria.user.UserService
import uk.co.grahamcox.elloria.user.UsernameCredentials
import uk.co.grahamcox.elloria.user.Password
import uk.co.grahamcox.elloria.user.UnknownUserCredentialsException
import uk.co.grahamcox.elloria.user.InvalidUserCredentialsException

// Exceptions on the way out if it goes wrong
/**
 * Base class for all OAuth2 errors. All exceptions of this type will get translated into an OAuth2 error payload
 * on the way out
 * @param code The error code
 * @param state The state from the request
 * @param redirectUri The Redirect URI from the request
 * @param description The description
 */
open class OAuth2Exception(val code: String,
                           val state: String? = null,
                           val redirectUri: String? = null,
                           description: String? = null) : Exception(description)

/**
 * The request is missing a required parameter, includes an
 * invalid parameter value, includes a parameter more than
 * once, or is otherwise malformed.
 * @param state The state from the request
 * @param redirectUri The Redirect URI from the request
 * @param description The error description
 */
data class InvalidRequestException(state: String? = null,
                                   redirectUri: String? = null,
                                   description: String? = null) :
        OAuth2Exception("invalid_request", state, redirectUri, description)

/**
 * The client is not authorized to request an authorization
 * code using this method.
 * @param state The state from the request
 * @param redirectUri The Redirect URI from the request
 * @param description The error description
 */
data class UnauthorizedClientException(state: String? = null,
                                       redirectUri: String? = null,
                                       description: String? = null) :
        OAuth2Exception("unauthorized_client", state, redirectUri, description)

/**
 * The resource owner or authorization server denied the
 * request.
 * @param state The state from the request
 * @param redirectUri The Redirect URI from the request
 * @param description The error description
 */
data class AccessDeniedException(state: String? = null,
                                 redirectUri: String? = null,
                                 description: String? = null) :
        OAuth2Exception("access_denied", state, redirectUri, description)

/**
 * The user was unknown, in an Elloria Webapp grant
 * @param state The state from the request
 * @param redirectUri The Redirect URI from the request
 * @param description The error description
 */
data class UnknownUserException(state: String? = null,
                                redirectUri: String? = null,
                                description: String? = null) :
        OAuth2Exception("unknown_user", state, redirectUri, description)

/**
 * The authorization server does not support obtaining an
 * authorization code using this method.
 * @param state The state from the request
 * @param redirectUri The Redirect URI from the request
 * @param description The error description
 */
data class UnsupportedResponseTypeException(state: String? = null,
                                            redirectUri: String? = null,
                                            description: String? = null) :
        OAuth2Exception("unsupported_response_type", state, redirectUri, description)

/**
 * The requested scope is invalid, unknown, or malformed.
 * @param state The state from the request
 * @param redirectUri The Redirect URI from the request
 * @param description The error description
 */
data class InvalidScopeException(state: String? = null,
                                 redirectUri: String? = null,
                                 description: String? = null) :
        OAuth2Exception("invalid_scope", state, redirectUri, description)

/**
 * The authorization server encountered an unexpected
 * condition that prevented it from fulfilling the request.
 * (This error code is needed because a 500 Internal Server
 * Error HTTP status code cannot be returned to the client
 * via an HTTP redirect.)
 * @param state The state from the request
 * @param redirectUri The Redirect URI from the request
 * @param description The error description
 */
data class ServerErrorException(state: String? = null,
                                redirectUri: String? = null,
                                description: String? = null) :
        OAuth2Exception("server_error", state, redirectUri, description)

/**
 * The authorization server is currently unable to handle
 * the request due to a temporary overloading or maintenance
 * of the server.  (This error code is needed because a 503
 * Service Unavailable HTTP status code cannot be returned
 * to the client via an HTTP redirect.)
 * @param state The state from the request
 * @param redirectUri The Redirect URI from the request
 * @param description The error description
 */
data class TemporarilyUnavailableException(state: String? = null,
                                           redirectUri: String? = null,
                                           description: String? = null) :
        OAuth2Exception("temporarily_unavailable", state, redirectUri, description)

/**
 * Client authentication failed (e.g., unknown client, no
 * client authentication included, or unsupported
 * authentication method).  The authorization server MAY
 * return an HTTP 401 (Unauthorized) status code to indicate
 * which HTTP authentication schemes are supported.  If the
 * client attempted to authenticate via the "Authorization"
 * request header field, the authorization server MUST
 * respond with an HTTP 401 (Unauthorized) status code and
 * include the "WWW-Authenticate" response header field
 * matching the authentication scheme used by the client.
 * @param state The state from the request
 * @param description The error description
 */
data class InvalidClientException(state: String? = null,
                                  description: String? = null) :
        OAuth2Exception("invalid_client", state, null, description)

// Input values on the API calls


/**
 * Controller to manage OAuth2 requests for authentication, as described in RFC-6749
 * @param userService the mechanism for loading user records
 * @param tokenIssuer the mechanism for issuing OAuth2 tokens
 */
[Controller]
[RequestMapping(value = array("/api/oauth2"))]
class OAuth2Controller(private val userService: UserService, private val tokenIssuer: TokenIssuer) {
    /** The logger to use */
    private val log = LoggerFactory.getLogger(javaClass<OAuth2Controller>())

    /**
     * Handle when an Invalid Client Exception occurs
     * This is identical to the OAuth2 Exception Handler except it explicitly sets the status code to 401 and sets the
     * WWW-Authenticate header
     * @param e The error to handle
     * @return the appropriate response
     */
    [ExceptionHandler(javaClass<InvalidClientException>())]
    [ResponseBody]
    fun handleInvalidClientException(e: InvalidClientException): ResponseEntity<Map<String, String>> {
        return handleOauth2Exception(e)
    }
    /**
     * Handle when an OAuth2 error occurs
     * @param e The error to handle
     * @return the appropriate response - either a 302 Redirect or a JSON payload depending on if a Redirect URI was
     * present
     */
    [ExceptionHandler(javaClass<OAuth2Exception>())]
    [ResponseBody]
    fun handleOauth2Exception(e: OAuth2Exception): ResponseEntity<Map<String, String>> {
        val response = hashMapOf("error" to e.code)
        if (e.state != null) {
            response.put("state", e.state)
        }
        if (e.getMessage() != null) {
            response.put("error_description", e.getMessage())
        }

        val output = if (e.redirectUri == null) {
            ResponseEntity(response : Map<String, String>, HttpStatus.BAD_REQUEST)
        } else {
            val headers = HttpHeaders()
            headers.setLocation(redirectUriWithParams(e.redirectUri, response))
            ResponseEntity(headers, HttpStatus.FOUND)
        }
        return output
    }

    /**
     * Get the redirect URI with some additional parameters
     * @param uri The redirect URI to add the parameters to
     * @param params the parameters to add to the URI
     * @return the URI
     */
    private fun redirectUriWithParams(uri: String, params : Map<String, String>) : URI {
        return params.entrySet().fold(UriComponentsBuilder.fromUriString(uri)) { u, e ->
            u.queryParam(e.key, e.value)
        }.build().encode().toUri()
    }
    /**
     * Handle the request for an Authorization Code Grant, as described in Section 4.1
     * @param clientId The Client ID for the request
     * @param redirectUri The Redirect URI for the request
     * @param scope The scopes for the request
     * @param state The state for the request
     * @throws UnsupportedOperationException we've not implemented it yet
     */
    [RequestMapping(value = array("/authorize"),
            method = array(RequestMethod.GET),
            params = array("response_type=code"))]
    [ResponseBody]
    fun authorizationCodeGrant([RequestParam(value = "client_id", required = false)] clientId: String?,
                               [RequestParam(value = "redirect_uri", required = false)] redirectUri: String?,
                               [RequestParam(value = "scope", required = false)] scope: String?,
                               [RequestParam(value = "state", required = false)] state: String?) {
        log.info("Received request for {}/{}/{}/{}", clientId, redirectUri, scope, state)
        throw UnsupportedResponseTypeException(state = state, redirectUri = redirectUri)
    }

    /**
     * Handle the request for an Authorization Code Token, as a follow up for the request for an Authorization Code
     * Grant, as described in Section 4.1
     * @param code The Authorization Code to exchange for a token
     * @param clientId The Client ID for the request
     * @param redirectUri The Redirect URI for the request
     * @throws UnsupportedOperationException we've not implemented it yet
     */
    [RequestMapping(value = array("/token"),
            method = array(RequestMethod.POST),
            params = array("grant_type=authorization_code"))]
    [ResponseBody]
    fun authorizationCodeToken([RequestParam(value = "code", required = false)] code: String,
                               [RequestParam(value = "client_id", required = false)] clientId: String?,
                               [RequestParam(value = "redirect_uri", required = false)] redirectUri: String?) {
        log.info("Received request for {}/{}/{}", code, clientId, redirectUri)
        throw UnsupportedResponseTypeException(redirectUri = redirectUri)
    }

    /**
     * Handle the request for an Implicit Code Grant, as described in Section 4.2
     * @param clientId The Client ID for the request
     * @param redirectUri The Redirect URI for the request
     * @param scope The scopes for the request
     * @param state The state for the request
     * @throws UnsupportedOperationException we've not implemented it yet
     */
    [RequestMapping(value = array("/authorize"),
            method = array(RequestMethod.GET),
            params = array("response_type=token"))]
    [ResponseBody]
    fun implicitGrant([RequestParam(value = "client_id", required = false)] clientId: String?,
                      [RequestParam(value = "redirect_uri", required = false)] redirectUri: String?,
                      [RequestParam(value = "scope", required = false)] scope: String?,
                      [RequestParam(value = "state", required = false)] state: String?) {
        log.info("Received request for {}/{}/{}/{}", clientId, redirectUri, scope, state)
        throw UnsupportedResponseTypeException(state = state, redirectUri = redirectUri)
    }

    /**
     * Handle the request for a Resource Owner Password Credentials Grant, as described in Section 4.3
     * @param username The username to authenticate
     * @param password The password to authenticate
     * @param scope The scopes for the request
     * @return The access token that was issued
     */
    [RequestMapping(value = array("/token"),
            method = array(RequestMethod.POST, RequestMethod.GET),
            params = array("grant_type=password"))]
    [ResponseBody]
    fun resourceOwnerPasswordCredentialsGrant([RequestParam(value = "username", required = false)] username: String?,
                                              [RequestParam(value = "password", required = false)] password: String?,
                                              [RequestParam(value = "scope", required = false)] scope: String?) :
        Map<String, Any> {
        log.info("Received request for {}/{}/{}", username, password, scope)
        if (username == null) {
            throw InvalidRequestException()
        }
        if (password == null) {
            throw InvalidRequestException()
        }

        val scopes = if (scope == null) {
            null
        } else {
            Scopes(scope)
        }

        try {
            val user = userService.getUser(UsernameCredentials(username, Password(username, password)))

            return buildResponse(tokenIssuer.issueToken(user,
                    scopes))
        } catch (e: Exception) {
            when (e) {
                is UnknownUserCredentialsException -> throw AccessDeniedException()
                is InvalidUserCredentialsException -> throw AccessDeniedException()
                else -> {
                    log.error("Unexpected error getting user", e)
                    throw ServerErrorException()
                }
            }
        }
    }

    /**
     * Handle the request for a Client Credentials Grant, as described in Section 4.4
     * @param scope The scopes for the request
     * @throws UnsupportedOperationException we've not implemented it yet
     */
    [RequestMapping(value = array("/token"),
            method = array(RequestMethod.POST),
            params = array("grant_type=client_credentials"))]
    [ResponseBody]
    fun clientCredentialsGrant([RequestParam(value = "scope", required = false)] scope: String?) {
        log.info("Received request for {}", scope)
        throw UnsupportedResponseTypeException()
    }

    /**
     * Handle the request to refresh a token, as described in Section 6
     * @param refreshToken the refresh token to work with
     * @param scope The scopes for the request
     * @throws UnsupportedOperationException we've not implemented it yet
     */
    [RequestMapping(value = array("/token"),
            method = array(RequestMethod.POST),
            params = array("grant_type=refresh_token"))]
    [ResponseBody]
    fun refresh([RequestParam(value = "refresh_token", required = false)] refreshToken: String?,
                [RequestParam(value = "scope", required = false)] scope: String?) {
        log.info("Received request for {}", scope)
        throw UnsupportedResponseTypeException()
    }

    /**
     * Handle the request for an Elloria Webapp Login. This is virtually identical to a Resource Owner Password Credentials grant, 
     * except that it will distinguish between Unknown User and Invalid Password in the response.
     * @param username The username to authenticate
     * @param password The password to authenticate
     * @param scope The scopes for the request
     * @return The access token that was issued
     */
    [RequestMapping(value = array("/token"),
            method = array(RequestMethod.POST, RequestMethod.GET),
            params = array("grant_type=urn:uk.co.grahamcox.elloria:webapp-login"))]
    [ResponseBody]
    fun elloriaWebappGrant([RequestParam(value = "username", required = false)] username: String?,
                           [RequestParam(value = "password", required = false)] password: String?,
                           [RequestParam(value = "scope", required = false)] scope: String?) :
        Map<String, Any> {
        log.info("Received request for {}/{}/{}", username, password, scope)
        if (username == null) {
            throw InvalidRequestException()
        }
        if (password == null) {
            throw InvalidRequestException()
        }

        val scopes = if (scope == null) {
            null
        } else {
            Scopes(scope)
        }

        try {
            val user = userService.getUser(UsernameCredentials(username, Password(username, password)))

            return buildResponse(tokenIssuer.issueToken(user,
                    scopes))
        } catch (e: Exception) {
            when (e) {
                is UnknownUserCredentialsException -> throw UnknownUserException()
                is InvalidUserCredentialsException -> throw AccessDeniedException()
                else -> {
                    log.error("Unexpected error getting user", e)
                    throw ServerErrorException()
                }
            }
        }
    }

    /**
     * Build the correct OAuth2 API Response for the given issued access token
     * @param accessToken the access token to build the response for
     * @return the correct response
     */
    private fun buildResponse(accessToken: IssuedToken) : Map<String, Any> {
        val response = hashMapOf(
                "access_token" to accessToken.accessToken.value,
                "token_type" to accessToken.type
        )

        if (accessToken.refreshToken != null) {
            response.put("refresh_token", accessToken.refreshToken.value)
        }

        if (accessToken.scopes != null) {
            response.put("scope", accessToken.scopes.scopes.join(" "))
        }

        return response
    }
}
