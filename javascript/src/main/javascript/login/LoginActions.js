define(['login/LoginConstants', 'login/LoginService'], function(LoginConstants, LoginService) {
    return {
        login: function(username, password) {
            this.dispatch(LoginConstants.STANDARD_LOGIN);
            LoginService.login(username, password).then(function(s) {
                this.dispatch(LoginConstants.LOGIN_SUCCESS, s);
            }.bind(this)).catch(function(e) {
                this.dispatch(LoginConstants.LOGIN_FAILURE, e);
            }.bind(this));
        }
    };
});
