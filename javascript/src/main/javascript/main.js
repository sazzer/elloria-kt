define(['test'], function(test) {

    var Index = React.createClass({displayName: "Index",
        render: function() {
            return React.createElement("p", {}, "Select a state");
        }
    });

    var State = React.createClass({displayName: "State",
        mixins: [ReactRouter.State],
        render: function() {
            return React.createElement("p", {}, "Selected state: " + this.getParams().abbr);
        }

    });

    var App = React.createClass({displayName: "App",
        render: function() {
            return React.createElement("div", {}, [
                React.createElement(ReactRouter.Link, {to: "state", params: {abbr: "OH"}}, "Ohio"),
                React.createElement(ReactRouter.Link, {to: "state", params: {abbr: "TX"}}, "Texas"),
                React.createElement(ReactRouter.Link, {to: "state", params: {abbr: "CA"}}, "California"),
                React.createElement(ReactRouter.RouteHandler, {})
            ]);
        }
    });

    var routes = React.createElement(ReactRouter.Route, {handler: App}, [
        React.createElement(ReactRouter.DefaultRoute, {handler: Index}),
        React.createElement(ReactRouter.Route, {name: "state", path: "state/:abbr", handler: State})
    ]);

    ReactRouter.run(routes, ReactRouter.HistoryLocation, function(Handler) {
        React.render(React.createElement(Handler, null), document.body)
    });

});
