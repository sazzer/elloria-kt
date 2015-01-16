define(['bootstrap/Dialog', 'bootstrap/form/Form', 'bootstrap/form/Input'], function(Dialog, Form, Input) {
    return React.createClass({displayName: 'LoginDialog',
        show: function() {
            this.refs.dialog.show();
            this.refs.username.focus();
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
                React.createElement(Form, {}, [
                    React.createElement(Input, {
                        name: 'username',
                        label: 'loginDialog.form.username.label',
                        placeholder: 'loginDialog.form.username.placeholder',
                        type: 'email',
                        ref: 'username'
                    }),
                    React.createElement(Input, {
                        name: 'password',
                        label: 'loginDialog.form.password.label',
                        placeholder: 'loginDialog.form.password.placeholder',
                        type: 'password',
                        ref: 'password'
                    })
                ])
            ]);
        }
    });
});


