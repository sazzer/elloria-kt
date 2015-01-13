define([], function() {
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
                        React.createElement('button', {
                            type: 'button',
                            className: 'navbar-toggle',
                            'data-toggle': 'collapse',
                            'data-target': '#elloria-navbar-collapse'
                        }, [
                            React.createElement('span', {className: 'sr-only'}, 'Toggle navigation'),
                            React.createElement('span', {className: 'icon-bar'}),
                            React.createElement('span', {className: 'icon-bar'}),
                            React.createElement('span', {className: 'icon-bar'})
                        ]),
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
                            React.createElement('li', {}, [
                                React.createElement(ReactRouter.Link, {to: 'state', params: {abbr: 'OH'}}, 'About')
                            ]),
                            React.createElement('li', {}, [
                                React.createElement(ReactRouter.Link, {to: 'state', params: {abbr: 'TX'}}, 'Services')
                            ]),
                            React.createElement('li', {}, [
                                React.createElement(ReactRouter.Link, {to: 'state', params: {abbr: 'CA'}}, 'Contact')
                            ])
                        ])
                    ])
                ])
            ]);
        }
    });
});
