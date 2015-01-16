define(['bootstrap/Dialog'], function(Dialog) {
    return React.createClass({displayName: 'LoginDialog',
        show: function() {
            this.refs.dialog.show();
        },
        render: function() {
            return React.createElement(Dialog, {
                ref: 'dialog',
                label: 'page.header.login.label',
                buttons: [
                    {
                        label: 'loginDialog.buttons.close',
                        defaultButton: true,
                        dismiss: true
                    }, {
                        label: 'loginDialog.buttons.login',
                        primaryButton: true
                    }
                ]
            }, [
                React.createElement('p', {}, 'One fine body')
            ]);
        }
    });
});


