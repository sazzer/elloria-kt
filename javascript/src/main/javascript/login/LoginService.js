define(["ajax"], function(ajax) {
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
            return ajax.post("/api/oauth2/token")
                .type("form")
                .send({
                    "grant_type": "urn:uk.co.grahamcox.elloria:webapp-login",
                    "username": username,
                    "password": password
                })
                .set("Accept", "application/json")
                .promise();
        }
    };
});

