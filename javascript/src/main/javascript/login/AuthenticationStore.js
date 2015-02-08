define(['login/LoginConstants'], function(LoginConstants) {
    /**
     * The Flux Store that represents the store of state for our current authentication/registration process
     */
    return Fluxxor.createStore({
        /** We have not authenticated yet */
        AUTH_STATE_NOT_AUTHENTICATED: 'authStateNotAuthenticated',
        /** We have successfully authenticated */
        AUTH_STATE_SUCCESS: 'authStateSuccess',
        /** We have failed to authenticate */
        AUTH_STATE_FAILED: 'authStateFailed',
        /** We have started user registration */
        AUTH_STATE_REGISTER_STARTED: 'authStateRegisterStarted',
        /** We have failed user registration */
        AUTH_STATE_REGISTER_FAILED: 'authStateRegisterFailed',


        /**
         * Initialize the store. This binds handlers for the appropriate Action constants that we want to handle
         */
        initialize: function() {
            this._authState = this.AUTH_STATE_NOT_AUTHENTICATED;
            this._authErrors = undefined;
            this._registerErrors = undefined;

            this.bindActions(
                LoginConstants.STANDARD_LOGIN, this._startLogin,
                LoginConstants.LOGIN_SUCCESS, this._loginSuccess,
                LoginConstants.LOGIN_FAILURE, this._loginFailure
            );
        },

        /**
         * Get the current authentication state
         * @return {String} The current authentication state
         */
        getAuthState: function() {
            return this._authState;
        },

        /**
         * Get the current authentication errors
         * @return {Object} the current authentication errors
         */
        getAuthErrors: function() {
            return this._authErrors;
        },

        /**
         * Handle the fact that a login has started
         * @private
         */
        _startLogin: function() {
            this._authState = this.AUTH_STATE_NOT_AUTHENTICATED;
            this._authErrors = undefined;
            this._registerErrors = undefined;
            this.emit("change");
        },
        /**
         * Handle the fact that a login was successful
         * @private
         */
        _loginSuccess: function() {
            this._authState = this.AUTH_STATE_SUCCESS;
            this._authErrors = undefined;
            this._registerErrors = undefined;
            this.emit("change");
        },

        /**
         * Handle the fact that a login failed
         * @param payload {Object} The payload to the action
         * @private
         */
        _loginFailure: function(payload) {
            if (payload.error === "unknown_user") {
                this._authState = this.AUTH_STATE_REGISTER_STARTED;
                this._authErrors = undefined;
                this._registerErrors = undefined;
            } else {
                this._authState = this.AUTH_STATE_FAILED;
                this._authErrors = payload;
                this._registerErrors = undefined;
            }
            this.emit("change");
        }

    });
});
