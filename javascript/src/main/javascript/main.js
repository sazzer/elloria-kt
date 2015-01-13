define(['ui/app'], function(App) {

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

    var routes = React.createElement(ReactRouter.Route, {name: 'home', path: '/', handler: App}, [
        React.createElement(ReactRouter.DefaultRoute, {handler: Index}),
        React.createElement(ReactRouter.Route, {name: "state", path: "state/:abbr", handler: State})
    ]);

    React.withContext({
        messages: {
            page: {
                title: 'Elloria'
            }
        }
    }, function() {
        ReactRouter.run(routes, ReactRouter.HistoryLocation, function(Handler) {
            React.render(React.createElement(Handler, null), document.body)
        });
    });

});
