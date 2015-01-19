define(['ui/header'], function(Header) {
    /**
     * Create the main Application overview
     */
    return React.createClass({displayName: "App",
        /**
         * Render the application
         * @returns {ReactElement} The React representation of the application
         */
        render: function() {
            return React.createElement("div", {}, [
                React.createElement(Header),
                React.createElement(ReactRouter.RouteHandler, {})
            ]);
        }
    });
});