define([], function() {
    return React.createClass({displayName: 'LoginDialog',
        show: function() {
            var modalBase = this.refs.modalBase,
                domNode = modalBase.getDOMNode();
            $(domNode).modal();
        },
        render: function() {
            return React.createElement('div', {className: 'modal fade', ref: 'modalBase'}, [
                React.createElement('div', {className: 'modal-dialog'}, [
                    React.createElement('div', {className: 'modal-content'}, [
                        React.createElement('div', {className: 'modal-header'}, [
                            React.createElement('button', {
                                'className': 'close',
                                'data-dismiss': 'modal',
                                'aria-label': 'Close'
                            }, [
                                React.createElement('span', {'aria-hidden': 'true'}, '&times;')
                            ]),
                            React.createElement('h4', {className: 'modal-title'}, 'Modal Title')
                        ]),
                        React.createElement('div', {className: 'modal-body'}, [
                            React.createElement('p', {}, 'One fine body')
                        ]),
                        React.createElement('div', {className: 'modal-footer'}, [
                            React.createElement('button', {
                                'type': 'button',
                                'className': 'btn btn-default',
                                'data-dismiss': 'modal'
                            }, 'Close'),
                            React.createElement('button', {
                                'type': 'button',
                                'className': 'btn btn-primary'
                            }, 'Save')
                        ])
                    ])
                ])
            ]);
        }
    });
});


