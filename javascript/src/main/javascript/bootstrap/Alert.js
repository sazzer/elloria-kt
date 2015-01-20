define([], function() {
    /**
     * Wrapper around a Bootstrap Alert
     */
    return React.createClass({displayName: 'Alert',
        mixins: [ReactIntl.Mixin],
        propTypes: {
            'message': React.PropTypes.string.isRequired,
            'type': React.PropTypes.string
        },

        /**
         * Get the default property values to use when they aren't specified
         * @returns {Object} The collection of default property values
         */
        getDefaultProps: function() {
            return {
                type: 'danger'
            }
        },
        
        /**
         * Render the React nodes that represent the alert
         * @return {ReactElement} the React representation of the alert
         */
        render: function() {
            return React.createElement('div', {
                className: 'alert alert-' + this.props.type,
                role: 'alert'
            }, this.getIntlMessage(this.props.message));
        }
    });
});



