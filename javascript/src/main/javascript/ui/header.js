define(['ui/NavBar', 'bootstrap/nav/NavLink', 'bootstrap/nav/NavDropdown'], function(NavBar, NavLink, NavDropdown) {
    return React.createClass({displayName: 'Header',
        mixins: [ReactIntl.Mixin],

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
                            label: 'page.header.games.label'
                        }),
                        React.createElement(NavLink, {
                            to: 'state',
                            params: {abbr: 'TY'},
                            label: 'page.header.games.label'
                        })
                    ]
                })
            ];
            var right = [
                React.createElement(NavLink, {
                    to: 'state',
                    params: {abbr: 'CA'},
                    label: 'page.header.profile.label'
                })
            ];
            return React.createElement(NavBar, {left: left, right: right});
        }
    });
});
