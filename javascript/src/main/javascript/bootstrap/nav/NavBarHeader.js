define(['bootstrap/nav/CollapseButton'], function(CollapseButton) {
    /**
     * Wrapper around the header in a Bootstrap Nav
     */
    return React.createClass({displayName: 'NavBarHeader',
        mixins: [ReactIntl.Mixin],
        propTypes: {
            'label': React.PropTypes.string.isRequired,
            'collapseLabel': React.PropTypes.string.isRequired,
            'collapseTarget': React.PropTypes.string.isRequired,
            'to': React.PropTypes.string.isRequired,
            'params': React.PropTypes.object
        },

        /**
         * Render the React nodes that represent the header
         * @return {ReactElement} the React representation of the header
         */
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


