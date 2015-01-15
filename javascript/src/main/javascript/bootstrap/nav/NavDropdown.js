define([], function() {
    return React.createClass({displayName: 'NavDropdown',
        mixins: [ReactIntl.Mixin],
        propTypes: {
            'label': React.PropTypes.string.isRequired,
            'entries': React.PropTypes.array.isRequired
        },

        render: function() {
            return React.createElement('li', {className: 'dropdown'}, [
                React.createElement('a', {
                    'href': '#', 
                    'className': 'dropdown-toggle',
                    'data-toggle': 'dropdown',
                    'role': 'button',
                    'aria-expanded': 'false'
                }, [
                    React.createElement(ReactIntl.Message, {}, this.getIntlMessage(this.props.label)),
                    React.createElement('span', {className: 'caret'})
                ]),
                React.createElement('ul', {
                    className: 'dropdown-menu',
                    role: 'menu'
                }, this.props.entries)
            ]);
        }
    });
});


