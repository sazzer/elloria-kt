define(['login/SessionStore'], function(SessionStore) {
    /**
     * The collection of all Flux stores to use
     */
    return {
        SessionStore: new SessionStore()
    };
});

