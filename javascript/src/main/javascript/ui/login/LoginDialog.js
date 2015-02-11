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
                errorMessage: undefined
            };
        },

        /**
         * Get the current state of the dialog from Flux
         */
        getStateFromFlux: function() {
            var flux = this.getFlux(),
                authStore = flux.store("AuthenticationStore"),
                result = {
                    authState: authStore.getAuthState(),
                    errorMessage: undefined,
                    registerUser: false
                };

            if (authStore.getAuthState() === authStore.AUTH_STATE_FAILED) {
                result.errorMessage = authStore.getAuthErrors().error;
            } else if (authStore.getAuthState() == authStore.AUTH_STATE_REGISTER_STARTED) {
                result.registerUser = true;
            }

            return result;
        },
        /**
         * Request that the login dialog is displayed
         */
        show: function() {
            this.setState({
                authState: this.getFlux().store("AuthenticationStore").AUTH_STATE_NOT_AUTHENTICATED
            });
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
         * Callback to submit the login form
         * @private
         */
        _onSubmit: function() {
            this.getFlux().actions.Login.login(this.refs.username.value(), this.refs.password.value());
        },

        componentDidUpdate: function() {
            this.refs.username.focus();
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

            var form = [
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
            ];

            if (this.state.registerUser) {
                form.push(React.createElement(Input, {
                    name: 'password2',
                    label: 'loginDialog.form.password2.label',
                    placeholder: 'loginDialog.form.password2.placeholder',
                    type: 'password',
                    ref: 'password2'
                }));
                form.push(React.createElement(Input, {
                    name: 'realName',
                    label: 'loginDialog.form.realName.label',
                    placeholder: 'loginDialog.form.realName.placeholder',
                    type: 'text',
                    ref: 'realName'
                }));
                form.push(React.createElement(Input, {
                    name: 'screenName',
                    label: 'loginDialog.form.screenName.label',
                    placeholder: 'loginDialog.form.screenName.placeholder',
                    type: 'text',
                    ref: 'screenName'
                }));
            }

            form.push(errorMessage);
            return React.createElement(Dialog, {
                ref: 'dialog',
                label: 'page.header.login.label',
                onShow: this._setFocusOnShow,
                className: 'test-login-dialog',
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
                React.createElement(Form, {}, form)
            ]);
        }
    });
});


