define([], function() {
    return React.createClass({displayName: 'NavLink',
        mixins: [ReactIntl.Mixin],
        propTypes: {
            'label': React.PropTypes.string.isRequired,
            'to': React.PropTypes.string.isRequired,
            'params': React.PropTypes.object
        },

        render: function() {
            return React.createElement('li', {}, [
                React.createElement(ReactRouter.Link, {
                    to: this.props.to,
                    params: this.props.params
                }, [
                    React.createElement(ReactIntl.Message, {}, this.getIntlMessage(this.props.label))
                ])
            ]);
        }
    });
});

