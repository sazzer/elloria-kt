define(['login/LoginConstants', 'login/LoginService'], function(LoginConstants, LoginService) {
    /**
     * The collection of all Flux Actions for authentication
     */
    return {
        /**
         * Attempt to log in to the system with a username and password
         * @param username {String} The username
         * @param password {String} The password
         */
        login: function(username, password) {
            this.dispatch(LoginConstants.STANDARD_LOGIN);
            LoginService.login(username, password).then(function(s) {
                this.dispatch(LoginConstants.LOGIN_SUCCESS, s);
            }.bind(this)).catch(function(e) {
                this.dispatch(LoginConstants.LOGIN_FAILURE, e.res.body);
            }.bind(this));
        }
    };
});
