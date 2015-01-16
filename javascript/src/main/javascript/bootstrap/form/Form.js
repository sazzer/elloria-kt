define([], function() {
    return React.createClass({displayName: 'Form',
        render: function() {
            return React.createElement('form', {}, this.props.children);
        }
    });
});




