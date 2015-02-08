define([], function() {

    /**
     * Add promise support for superagent/supertest
     * This code taken from the superagent-bluebird-promise module found at
     * https://github.com/KyleAMathews/superagent-bluebird-promise
     *
     * Call .promise() to return promise for the request
     *
     * @method promise
     * @return {Bluebird.Promise}
     */
    superagent.Request.prototype.promise = function() {
        var self = this;
        return new Promise(function(resolve, reject) {
            self.end(function(err, res) {
                if (typeof res != 'undefined' && res.status >= 400) {
                    reject({
                        status: res.status,
                        res: res,
                        error: res.error
                    });
                } else if (err) {
                    reject({
                        error: err
                    });
                } else {
                    resolve(res);
                }
            });
        });
    };

    /**
     * Wire up the provided SuperAgent object to have the correct special settings that we need
     * @param request {SuperAgent} The SuperAgent object to setup
     * @returns {SuperAgent} The superagent object that has been setup
     */
    function setupSuperAgent(request) {
        return request.set("X-Elloria-Webapp", "true");
    }

    var exports = {};
    ["get", "post", "put", "del", "head"].forEach(function(m) {
        exports[m] = function(url) {
            return setupSuperAgent(superagent[m](url));
        }
    });

    return exports;
});