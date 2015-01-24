package uk.co.grahamcox.elloria.webapp.authentication

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.PathVariable
import uk.co.grahamcox.elloria.user.UserService
import uk.co.grahamcox.elloria.authentication.oauth2.TokenIssuer
import uk.co.grahamcox.elloria.user.UserId
import org.springframework.web.bind.annotation.ResponseBody

/**
 * Representation of a user as sent over the wire
 * @param screenName The screen name to use
 * @param realName The real name to use
 * @param email The email address to use
 */
open class UserModel(val screenName: String?,
                     val realName: String?,
                     val email: String?)

/**
 * Representation of a user as sent over the wire
 * @param userId The ID of the user
 * @param screenName The screen name to use
 * @param realName The real name to use
 * @param email The email address to use
 */
class IdentifiedUserModel(val userId: String?,
                          screenName: String?,
                          realName: String?,
                          email: String?) : UserModel(screenName, realName, email)
/**
 * Controller to manage user records, including webapp logins and registrations
 * @param userService the mechanism for managing user records
 * @param tokenIssuer the mechanism for issuing OAuth2 tokens
 */
[Controller]
[RequestMapping(value = array("/api/users"))]
class UserController(private val userService: UserService, private val tokenIssuer: TokenIssuer) {
    /**
     * Get the details of the user with the given ID
     * @param id The ID of the user
     * @return the user details
     */
    [RequestMapping(value = array("/{id}"),
            method = array(RequestMethod.GET))]
    [ResponseBody]
    fun getUserById([PathVariable("id")] id: UserId) : IdentifiedUserModel {
        val user = userService.getUser(id)
        return IdentifiedUserModel(userId = user.userId.id,
                screenName = user.profile.screenName,
                realName = user.profile.realName,
                email = user.profile.email)
    }
}