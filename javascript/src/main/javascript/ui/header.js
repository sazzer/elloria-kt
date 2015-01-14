define(['ui/NavBar'], function(NavBar) {
    return React.createClass({displayName: 'Header',
        mixins: [ReactIntl.Mixin],

        render: function() {
            return React.createElement(NavBar, {
                links: [{
                    to: 'state',
                    params: {abbr: 'OH'},
                    label: 'page.header.world.label'
                }, {
                    to: 'state',
                    params: {abbr: 'TX'},
                    label: 'page.header.games.label'
                }, {
                    to: 'state',
                    params: {abbr: 'CA'},
                    label: 'page.header.profile.label'
                }]
            });
        }
    });
});
