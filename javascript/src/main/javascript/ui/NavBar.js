define(['bootstrap/nav/NavBarHeader', 'bootstrap/nav/NavLink'], function(NavBarHeader, NavLink) {
    return React.createClass({displayName: 'NavBarHeader',
        mixins: [ReactIntl.Mixin],
        propTypes: {
        },

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
                        collapseTarget: '#elloria-navbar-collapse'
                        to: 'home'
                    }),
                    React.createElement('div', {
                        className: 'collapse navbar-collapse',
                        id: 'elloria-navbar-collapse'
                    }, [
                        React.createElement('ul', {
                            className: 'nav navbar-nav'
                        }, [
                            React.createElement(NavLink, {to: 'home', label: 'page.header.world.label'}),
                            React.createElement(NavLink, {to: 'home', label: 'page.header.games.label'}),
                            React.createElement(NavLink, {to: 'home', label: 'page.header.profile.label'})
                        ])
                    ])
                ])
            ]);
        }
    });
});



