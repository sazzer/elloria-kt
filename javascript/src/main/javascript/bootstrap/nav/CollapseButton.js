define([], function() {
    /**
     * Wrapper around a Collapse Button as part of a Bootstrap Nav
     */
    return React.createClass({displayName: 'CollapseButton',
        mixins: [ReactIntl.Mixin],
        propTypes: {
            'label': React.PropTypes.string.isRequired,
            'target': React.PropTypes.string
        },

        /**
         * Render the React nodes that represent the collapse button
         * @return {ReactElement} the React representation of the collapse button
         */
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


