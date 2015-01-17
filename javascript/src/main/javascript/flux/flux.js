define(['flux/actions', 'flux/stores'], function(actions, stores) {
    var flux = new Fluxxor.Flux(stores, actions);
    flux.on("dispatch", function(type, payload) {
        if (console && console.log) {
            console.log("[Dispatch]", type, payload);
        }
    });
    return flux;
});
