define([], function() {
    return {
        login: function(username, password) {
            return new Promise(function(resolve, reject) {
                reject({
                    err: "UNKNOWN_USER"
                });
            });
        }
    };
});

