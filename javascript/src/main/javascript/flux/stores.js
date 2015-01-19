define(['login/SessionStore', 'login/AuthenticationStore'], function(SessionStore, AuthenticationStore) {
    /**
     * The collection of all Flux stores to use
     */
    return {
        SessionStore: new SessionStore(),
        AuthenticationStore: new AuthenticationStore()
    };
});

