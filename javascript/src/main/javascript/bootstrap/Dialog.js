define([], function() {
    /**
     * Wrapper around a Bootstrap Dialog
     */
    return React.createClass({displayName: 'Dialog',
        mixins: [ReactIntl.Mixin],
        propTypes: {
            'label': React.PropTypes.string.isRequired,
            'buttons': React.PropTypes.array.isRequired,
            'onShow': React.PropTypes.func,
            'className': React.PropTypes.string
        },

        /**
         * Actually display the dialog on the screen
         */
        show: function() {
            var modalBase = this.refs.modalBase,
                domNode = modalBase.getDOMNode();
            if (this.props.onShow) {
                $(domNode).on('shown.bs.modal', this.props.onShow);
            }
            $(domNode).modal();
        },
        /**
         * Render the React nodes that represent the dialog
         * @return {ReactElement} the React representation of the dialog
         */
        render: function() {
            var buttons = this.props.buttons.map(function(button) {
                var label = this.getIntlMessage(button.label);
                var settings = {
                    type: 'button', 
                    className: ['btn'],
                    onClick: button.onClick
                };

                if (button.dismiss) {
                    settings['data-dismiss'] = 'modal';
                }

                if (button.defaultButton) {
                    settings.className.push('btn-default');
                }
                if (button.primaryButton) {
                    settings.className.push('btn-primary');
                }
                settings.className = settings.className.join(' ');
                return React.createElement('button', settings, label);
            }.bind(this));
            return React.createElement('div', {className: 'modal fade ' + this.props.className, ref: 'modalBase'}, [
                React.createElement('div', {className: 'modal-dialog'}, [
                    React.createElement('div', {className: 'modal-content'}, [
                        React.createElement('div', {className: 'modal-header'}, [
                            React.createElement('button', {
                                'className': 'close',
                                'data-dismiss': 'modal',
                                'aria-label': 'Close'
                            }, [
                                React.createElement('span', {'aria-hidden': 'true'}, '×') // Note, this is the &times; entity
                            ]),
                            React.createElement('h4', {className: 'modal-title'}, this.getIntlMessage(this.props.label))
                        ]),
                        React.createElement('div', {className: 'modal-body'}, this.props.children),
                        React.createElement('div', {className: 'modal-footer'}, buttons)
                    ])
                ])
            ]);
        }
    });
});



