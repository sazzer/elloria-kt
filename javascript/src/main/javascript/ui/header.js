define(['ui/NavBar'], function(NavBar) {
    return React.createClass({displayName: 'Header',
        mixins: [ReactIntl.Mixin],

        render: function() {
            return React.createElement(NavBar);
        }
    });
});
