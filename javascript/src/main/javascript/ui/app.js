define(['ui/header'], function(Header) {
    return React.createClass({displayName: "App",
        render: function() {
            return React.createElement("div", {}, [
                React.createElement(Header),
                React.createElement(ReactRouter.RouteHandler, {})
            ]);
        }
    });
});