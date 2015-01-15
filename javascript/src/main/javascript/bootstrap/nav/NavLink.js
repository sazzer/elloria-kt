define([], function() {
    return React.createClass({displayName: 'NavLink',
        mixins: [ReactIntl.Mixin],
        propTypes: {
            'label': React.PropTypes.string.isRequired,
            'to': React.PropTypes.string,
            'params': React.PropTypes.object,
            'onClick': React.PropTypes.func
        },

        render: function() {
            var link,
                messageElement = React.createElement(ReactIntl.Message, {}, this.getIntlMessage(this.props.label));
            if (this.props.onClick) {
                link = React.createElement('a', {
                    href: '#',
                    onClick: this.props.onClick
                }, messageElement);
            } else {
                link = React.createElement(ReactRouter.Link, {
                    to: this.props.to,
                    params: this.props.params
                }, messageElement);
            }

            return React.createElement('li', {}, link);
        }
    });
});

