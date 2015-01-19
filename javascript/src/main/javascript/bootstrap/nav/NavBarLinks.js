define(['bootstrap/nav/NavLink'], function(NavLink) {
    /**
     * Wrapper around the links in a Bootstrap Nav
     */
    return React.createClass({displayName: 'NavLinks',
        propTypes: {
            'align': React.PropTypes.string,
            'links': React.PropTypes.array.isRequired
        },

        /**
         * Render the React nodes that represent the links
         * @return {ReactElement} the React representation of the links
         */
        render: function() {
            return React.createElement('ul', {
                className: ['nav', 'navbar-nav', this.props.align ? 'navbar-' + this.props.align : ''].join(' ')
            }, this.props.links);
        }
    });
});


