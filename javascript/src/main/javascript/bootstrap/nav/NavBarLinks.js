define(['bootstrap/nav/NavLink'], function(NavLink) {
    return React.createClass({displayName: 'NavLinks',
        propTypes: {
            'align': React.PropTypes.string,
            'links': React.PropTypes.array.isRequired
        },

        render: function() {
            return React.createElement('ul', {
                className: ['nav', 'navbar-nav', this.props.align ? 'navbar-' + this.props.align : ''].join(' ')
            }, this.props.links.map(function(link) {
                return React.createElement(NavLink, {
                    to: link.to,
                    params: link.params,
                    label: link.label
                });
            }));
        }
    });
});


