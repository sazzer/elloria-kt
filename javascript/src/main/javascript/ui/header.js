define(['bootstrap/nav/NavLink', 'bootstrap/nav/CollapseButton'], function(NavLink, CollapseButton) {
    return React.createClass({displayName: 'Header',
        mixins: [ReactIntl.Mixin],

        render: function() {
            return React.createElement('nav', {
                className: 'navbar navbar-inverse navbar-static-top',
                role: 'navigation'
            }, [
                React.createElement('div', {
                    className: 'container'
                }, [
                    React.createElement('div', {
                        className: 'navbar-header'
                    }, [
                        React.createElement(CollapseButton, {
                            label: 'page.header.toggleNavigation',
                            target: '#elloria-navbar-collapse'
                        }),
                        React.createElement(ReactRouter.Link, {
                            className: 'navbar-brand', 
                            to: 'home'
                        }, [
                            React.createElement(ReactIntl.Message, {}, this.getIntlMessage('page.title'))
                        ])
                    ]),
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
