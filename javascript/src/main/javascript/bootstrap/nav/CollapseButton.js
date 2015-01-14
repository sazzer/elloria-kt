define([], function() {
    return React.createClass({displayName: 'CollapseButton',
        mixins: [ReactIntl.Mixin],
        propTypes: {
            'label': React.PropTypes.string.isRequired,
            'target': React.PropTypes.string
        },

        render: function() {
            return React.createElement('button', {
                type: 'button',
                className: 'navbar-toggle',
                'data-toggle': 'collapse',
                'data-target': this.props.target
            }, [
                React.createElement('span', {className: 'sr-only'}, [
                    React.createElement(ReactIntl.Message, {}, this.getIntlMessage(this.props.label))
                ]),
                React.createElement('span', {className: 'icon-bar'}),
                React.createElement('span', {className: 'icon-bar'}),
                React.createElement('span', {className: 'icon-bar'})
            ]);
        }
    });
});


