define(['login/LoginConstants'], function(LoginConstants) {
    return Fluxxor.createStore({
        LOGGED_OUT_STATUS: 'loggedOutStatus',

        initialize: function() {
            this.status = this.LOGGED_OUT_STATUS;

            this.bindActions(
                LoginConstants.STANDARD_LOGIN, this.startLogin,
                LoginConstants.LOGIN_SUCCESS, this.loginSuccess,
                LoginConstants.LOGIN_FAILURE, this.loginFailure
            );
        },

        startLogin: function() {
            console.log("Starting to log in");
        },

        loginSuccess: function() {
            console.log("Logged in!");
        },

        loginFailure: function() {
            console.log("Login failed");
        }

    });
});
