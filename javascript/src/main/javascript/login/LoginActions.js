define(['login/LoginConstants'], function(LoginConstants) {
    return {
        login: function(username, password) {
            this.dispatch(LoginConstants.STANDARD_LOGIN);
            this.dispatch(LoginConstants.LOGIN_FAILURE, {err: 'UNKNOWN_USER'});
        }
    };
});
