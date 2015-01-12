define([], function() {
    var Component = React.createClass({displayName: 'Component', 
        render: function() {
            return React.createElement('div', 
                {
                    className: 'Testing'
                }, 'Hello, World!');
        }
    });
    return Component;
});
