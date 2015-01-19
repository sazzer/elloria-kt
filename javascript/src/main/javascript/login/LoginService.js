define([], function() {
    /**
     * The methods necessary to log in to the system
     */
    return {
        /**
         * Actually perform a normal login to the system, using the username and password
         * @param username {String} The username to log in with
         * @param password {String} The password to log in with
         * @returns {Promise} A Promise of the result of logging in
         */
        login: function(username, password) {
            return new Promise(function(resolve, reject) {
                setTimeout(function() {
                    reject({
                        error: "INVALID_PASSWORD"
                    });
                }, 1000);
            });
        }
    };
});

