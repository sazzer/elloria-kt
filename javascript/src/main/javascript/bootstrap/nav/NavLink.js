define([], function() {
    /**
     * Wrapper around a link in a Boostrap Nav
     */
    return React.createClass({displayName: 'NavLink',
        mixins: [ReactIntl.Mixin],
        propTypes: {
            'label': React.PropTypes.string.isRequired,
            'to': React.PropTypes.string,
            'params': React.PropTypes.object,
            'onClick': React.PropTypes.func
        },

        /**
         * Render the React nodes that represent the link
         * @return {ReactElement} the React representation of the link
         */
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

