define([], function() {
    /**
     * Wrapper around a Bootstrap Form
     */
    return React.createClass({displayName: 'Form',

        /**
         * Render the React nodes that represent the form
         * @return {ReactElement} the React representation of the form
         */
        render: function() {
            return React.createElement('form', {}, this.props.children);
        }
    });
});




