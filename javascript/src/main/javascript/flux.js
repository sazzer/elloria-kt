define(['login/LoginActions'], function(LoginActions) {
    var flux = new Fluxxor.Flux({}, LoginActions);
    flux.on("dispatch", function(type, payload) {
        if (console && console.log) {
            console.log("[Dispatch]", type, payload);
        }
    });
    return flux;
});
