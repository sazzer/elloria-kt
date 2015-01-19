define(['login/LoginConstants'], function(LoginConstants) {
    /**
     * The Flux Store that represents the store of session data
     */
    return Fluxxor.createStore({
        /**
         * Initialize the store. This binds handlers for the appropriate Action constants that we want to handle
         */
        initialize: function() {
            this.bindActions(
                LoginConstants.STANDARD_LOGIN, this._startLogin,
                LoginConstants.LOGIN_SUCCESS, this._loginSuccess,
                LoginConstants.LOGIN_FAILURE, this._loginFailure
            );
        },

        /**
         * Handle the fact that a login has started
         * @private
         */
        _startLogin: function() {
            console.log("Starting to log in");
        },

        /**
         * Handle the fact that a login was successful
         * @private
         */
        _loginSuccess: function() {
            console.log("Logged in!");
        },

        /**
         * Handle the fact that a login failed
         * @private
         */
        _loginFailure: function() {
            console.log("Login failed");
        }

    });
});
