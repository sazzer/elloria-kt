define(['bootstrap/nav/NavBarHeader', 'bootstrap/nav/NavBarLinks'], function(NavBarHeader, NavBarLinks) {
    return React.createClass({displayName: 'NavBarHeader',
        mixins: [ReactIntl.Mixin],
        propTypes: {
            'links': React.PropTypes.array
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
                        collapseTarget: '#elloria-navbar-collapse',
                        to: 'home'
                    }),
                    React.createElement('div', {
                        className: 'collapse navbar-collapse',
                        id: 'elloria-navbar-collapse'
                    }, [
                        React.createElement(NavBarLinks, {
                            links: this.props.links.filter(function(link) {
                                return link.align !== 'right'
                            }),
                            align: 'left'
                        }),
                        React.createElement(NavBarLinks, {
                            links: this.props.links.filter(function(link) {
                                return link.align === 'right'
                            }),
                            align: 'right'
                        })
                    ])
                ])
            ]);
        }
    });
});



