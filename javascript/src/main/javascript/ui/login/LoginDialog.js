define(['bootstrap/Dialog', 'bootstrap/form/Form', 'bootstrap/form/Input'], function(Dialog, Form, Input) {
    return React.createClass({displayName: 'LoginDialog',
        mixins: [Fluxxor.FluxMixin(React)],
        getInitialState: function() {
            return {
                username: '',
                password: ''
            };
        },
        show: function() {
            this.setState({username: '', password: ''});
            this.refs.dialog.show();
        }, 
        setFocusOnShow: function() {
            this.refs.username.focus();
        },
        onUsernameChange: function(e) {
            this.setState({username: e.target.value});
        },
        onPasswordChange: function(e) {
            this.setState({password: e.target.value});
            this.getFlux().actions.login(this.state.username, this.state.password);
        },
        render: function() {
            return React.createElement(Dialog, {
                ref: 'dialog',
                label: 'page.header.login.label',
                onShow: this.setFocusOnShow,
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
                        value: this.state.username,
                        onChange: this.onUsernameChange,
                        ref: 'username'
                    }),
                    React.createElement(Input, {
                        name: 'password',
                        label: 'loginDialog.form.password.label',
                        placeholder: 'loginDialog.form.password.placeholder',
                        type: 'password',
                        value: this.state.password,
                        onChange: this.onPasswordChange,
                        ref: 'password'
                    })
                ])
            ]);
        }
    });
});


