define(['bootstrap/nav/CollapseButton'], function(CollapseButton) {
    return React.createClass({displayName: 'NavBarHeader',
        mixins: [ReactIntl.Mixin],
        propTypes: {
            'label': React.PropTypes.string.isRequired,
            'collapseLabel': React.PropTypes.string.isRequired,
            'collapseTarget': React.PropTypes.string.isRequired,
            'to': React.PropTypes.string.isRequired,
            'params': React.PropTypes.object
        },

        render: function() {
            return React.createElement('div', {
                className: 'navbar-header'
            }, [
                React.createElement(CollapseButton, {
                    label: this.props.collapseLabel,
                    target: this.props.collapseTarget
                }),
                React.createElement(ReactRouter.Link, {
                    className: 'navbar-brand', 
                    to: this.props.to,
                    params: this.props.params
                }, [
                    React.createElement(ReactIntl.Message, {}, this.getIntlMessage(this.props.label))
                ])
            ]);
        }
    });
});


