define(['bootstrap/Dialog', 'bootstrap/form/Form', 'bootstrap/form/Input', 'bootstrap/Alert'], function(Dialog, Form, Input, Alert) {
    /**
     * The Login DIalog
     */
    return React.createClass({displayName: 'LoginDialog',
        mixins: [Fluxxor.FluxMixin(React), Fluxxor.StoreWatchMixin("AuthenticationStore")],
        /**
         * Get the initial state of the login dialog
         * @returns {Object} The initial state of the login dialog
         */
        getInitialState: function() {
            return {
                username: '',
                password: '',
                errorMessage: undefined
            };
        },

        /**
         * Get the current state of the dialog from Flux
         */
        getStateFromFlux: function() {
            var flux = this.getFlux(),
                authStore = flux.store("AuthenticationStore"),
                result = {};

            if (authStore.getAuthState() === authStore.AUTH_STATE_FAILED) {
                result.errorMessage = authStore.getAuthErrors().error;
            } else {
                result.errorMessage = undefined;
            }

            return result;
        },
        /**
         * Request that the login dialog is displayed
         */
        show: function() {
            this.setState({username: '', password: ''});
            this.refs.dialog.show();
        },
        /**
         * Callback to set the focus on the Username field when the dialog is displayed
         * @private
         */
        _setFocusOnShow: function() {
            this.refs.username.focus();
        },
        /**
         * Callback to store the username when it changes
         * @param e {Event} The event to handle
         * @private
         */
        _onUsernameChange: function(e) {
            this.setState({username: e.target.value});
        },
        /**
         * Callback to store the password when it changes
         * @param e {Event} The event to handle
         * @private
         */
        _onPasswordChange: function(e) {
            this.setState({password: e.target.value});
        },
        /**
         * Callback to submit the login form
         * @private
         */
        _onSubmit: function() {
            this.getFlux().actions.Login.login(this.state.username, this.state.password);
        },

        /**
         * Render the login dialog
         * @returns {ReactElement} the React representation of the login dialog
         */
        render: function() {
            var errorMessage = undefined;

            if (this.state.errorMessage !== undefined) {
                errorMessage = React.createElement(Alert, {
                    message: 'loginDialog.authenticationError.' + this.state.errorMessage,
                    type: 'danger'
                });
            }

            return React.createElement(Dialog, {
                ref: 'dialog',
                label: 'page.header.login.label',
                onShow: this._setFocusOnShow,
                buttons: [
                    {
                        label: 'loginDialog.buttons.close',
                        defaultButton: true,
                        dismiss: true
                    }, {
                        label: 'loginDialog.buttons.login',
                        primaryButton: true,
                        onClick: this._onSubmit
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
                        onChange: this._onUsernameChange,
                        ref: 'username'
                    }),
                    React.createElement(Input, {
                        name: 'password',
                        label: 'loginDialog.form.password.label',
                        placeholder: 'loginDialog.form.password.placeholder',
                        type: 'password',
                        value: this.state.password,
                        onChange: this._onPasswordChange,
                        ref: 'password'
                    }),
                    errorMessage
                ])
            ]);
        }
    });
});

