define(['ui/NavBar', 'bootstrap/nav/NavLink', 'bootstrap/nav/NavDropdown', 'ui/login/LoginDialog'], function(NavBar, NavLink, NavDropdown, LoginDialog) {
    /**
     * Create the header bar for the application
     */
    return React.createClass({displayName: 'Header',
        /**
         * Get the initial state of the header bar
         * @returns {Object} the initial state of the header bar
         */
        getInitialState: function() {
            return {
                loggedIn: false
            };
        },

        /**
         * Render the header bar
         * @returns {ReactElement} the React representation of the header bar
         */
        render: function() {
            var left = [
                React.createElement(NavLink, {
                    to: 'state',
                    params: {abbr: 'OH'},
                    label: 'page.header.world.label'
                }),
                React.createElement(NavDropdown, {
                    label: 'page.header.games.label',
                    entries: [
                        React.createElement(NavLink, {
                            to: 'state',
                            params: {abbr: 'TX'},
                            label: 'page.header.games.roguelike'
                        }),
                        React.createElement(NavLink, {
                            to: 'state',
                            params: {abbr: 'TY'},
                            label: 'page.header.games.mud'
                        }),
                        React.createElement(NavLink, {
                            to: 'state',
                            params: {abbr: 'TZ'},
                            label: 'page.header.games.tcg'
                        })
                    ]
                })
            ];
            var right = [];
            if (this.state.loggedIn) {
                right.push(React.createElement(NavLink, {
                    to: 'state',
                    params: {abbr: 'CA'},
                    label: 'page.header.profile.label'
                }));
            } else {
                right.push(React.createElement(NavLink, {
                    to: 'state',
                    params: {abbr: 'CA'},
                    onClick: this._onLoginClick,
                    label: 'page.header.login.label'
                }));
            }
            return React.createElement('div', {}, [
                React.createElement(NavBar, {left: left, right: right}),
                React.createElement(LoginDialog, {ref: 'loginDialog'})
            ]);
        },

        /**
         * Handler for when the user clicks the Login link
         * @private
         */
        _onLoginClick: function() {
            this.refs.loginDialog.show();
        }
    });
});
