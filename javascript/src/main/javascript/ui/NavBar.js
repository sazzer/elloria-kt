define(['bootstrap/nav/NavBarHeader', 'bootstrap/nav/NavBarLinks'], function(NavBarHeader, NavBarLinks) {
    /**
     * Create the navigation bar in the UI Header
     */
    return React.createClass({displayName: 'NavBarHeader',
        propTypes: {
            'left': React.PropTypes.array,
            'right': React.PropTypes.array
        },

        /**
         * Render the navigation bar
         * @returns {ReactElement} the React representation of the navigation bar
         */
        render: function() {
            return React.createElement('nav', {
                className: 'navbar navbar-inverse navbar-static-top',
                role: 'navigation'
            }, [
                React.createElement('div', {
                    className: 'container'
                }, [
                    React.createElement(NavBarHeader, {
                        label: 'page.title',
                        collapseLabel: 'page.header.toggleNavigation',
                        collapseTarget: '#elloria-navbar-collapse',
                        to: 'home'
                    }),
                    React.createElement('div', {
                        className: 'collapse navbar-collapse',
                        id: 'elloria-navbar-collapse'
                    }, [
                        React.createElement(NavBarLinks, {
                            links: this.props.left,
                            align: 'left'
                        }),
                        React.createElement(NavBarLinks, {
                            links: this.props.right,
                            align: 'right'
                        })
                    ])
                ])
            ]);
        }
    });
});



