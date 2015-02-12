define([], function() {
    /**
     * Wrapper around a Bootstrap Alert
     */
    return React.createClass({displayName: 'Alert',
        mixins: [ReactIntl.Mixin],
        propTypes: {
            'message': React.PropTypes.string.isRequired,
            'type': React.PropTypes.string,
            'className': React.PropTypes.string
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
            var classNames = [
                'alert',
                'alert-' + this.props.type
            ];
            if (this.props.className) {
                classNames.push(this.props.className);
            }

            return React.createElement('div', {
                className: classNames.join(' '),
                role: 'alert'
            }, this.getIntlMessage(this.props.message));
        }
    });
});



