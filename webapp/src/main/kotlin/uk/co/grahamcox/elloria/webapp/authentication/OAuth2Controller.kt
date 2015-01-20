package uk.co.grahamcox.elloria.webapp.authentication

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import org.slf4j.LoggerFactory

/**
 * Representation of the Client ID to use for a request
 * @param value The Value of the Client ID
 */
data class ClientId(val value: String)

/**
 * Representation of the Redirect URI to use for a request
 * @param value The Value of the Redirect URI
 */
data class RedirectUri(val value: String)

/**
 * Representation of the Scopes to use for a request
 * @param value The Value of the Scopes
 */
data class Scopes(val value: String) {
    /**
     * Get the Scopes split out as a set of the actual scope names
     * @return the set of scopes to request
     */
    fun getScopes() = value.split(" ").toSet()
}

/**
 * Representation of the State to use for a request
 * @param value The Value of the State
 */
data class State(val value: String)

/**
 * Controller to manage OAuth2 requests for authentication, as described in RFC-6749
 */
[Controller]
[RequestMapping(value = array("/api/oauth2"))]
class OAuth2Controller {
    /** The logger to use */
    private val log = LoggerFactory.getLogger(javaClass<OAuth2Controller>())
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
    fun authorizationCodeGrant([RequestParam(value = "client_id", required = false)] clientId: ClientId?,
                               [RequestParam(value = "redirect_uri", required = false)] redirectUri: RedirectUri?,
                               [RequestParam(value = "scope", required = false)] scope: Scopes?,
                               [RequestParam(value = "state", required = false)] state: State?) {
        log.info("Received request for {}/{}/{}/{}", clientId, redirectUri, scope, state)
        throw UnsupportedOperationException()
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
                               [RequestParam(value = "client_id", required = false)] clientId: ClientId?,
                               [RequestParam(value = "redirect_uri", required = false)] redirectUri: RedirectUri?) {
        log.info("Received request for {}/{}/{}", code, clientId, redirectUri)
        throw UnsupportedOperationException()
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
    fun implicitGrant([RequestParam(value = "client_id", required = false)] clientId: ClientId?,
                      [RequestParam(value = "redirect_uri", required = false)] redirectUri: RedirectUri?,
                      [RequestParam(value = "scope", required = false)] scope: Scopes?,
                      [RequestParam(value = "state", required = false)] state: State?) {
        log.info("Received request for {}/{}/{}/{}", clientId, redirectUri, scope, state)
        throw UnsupportedOperationException()
    }

    /**
     * Handle the request for a Resource Owner Password Credentials Grant, as described in Section 4.3
     * @param username The username to authenticate
     * @param password The password to authenticate
     * @param scope The scopes for the request
     * @throws UnsupportedOperationException we've not implemented it yet
     */
    [RequestMapping(value = array("/token"),
            method = array(RequestMethod.POST),
            params = array("grant_type=password"))]
    [ResponseBody]
    fun resourceOwnerPasswordCredentialsGrant([RequestParam(value = "username", required = false)] username: String,
                                              [RequestParam(value = "password", required = false)] password: String?,
                                              [RequestParam(value = "scope", required = false)] scope: Scopes?) {
        log.info("Received request for {}/{}/{}", username, password, scope)
        throw UnsupportedOperationException()
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
    fun clientCredentialsGrant([RequestParam(value = "scope", required = false)] scope: Scopes?) {
        log.info("Received request for {}", scope)
        throw UnsupportedOperationException()
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
                [RequestParam(value = "scope", required = false)] scope: Scopes?) {
        log.info("Received request for {}", scope)
        throw UnsupportedOperationException()
    }
}
