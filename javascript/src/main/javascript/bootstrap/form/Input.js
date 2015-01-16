define([], function() {
    return React.createClass({displayName: 'Input',
        mixins: [ReactIntl.Mixin],
        propTypes: {
            'label': React.PropTypes.string.isRequired,
            'name': React.PropTypes.string.isRequired,
            'type': React.PropTypes.string.isRequired,
            'placeholder': React.PropTypes.string,
            'value': React.PropTypes.string,
            'onChange': React.PropTypes.func
        },
        focus: function() {
            this.refs.input.getDOMNode().focus();
        },
        render: function() {
            var placeholder;
            if (this.props.placeholder) {
                placeholder = this.getIntlMessage(this.props.placeholder);
            }

            return React.createElement('div', {className: 'form-group'}, [
                React.createElement('label', {for: this.props.name}, this.getIntlMessage(this.props.label)),
                React.createElement('input', {
                    type: this.props.type, 
                    name: this.props.name,
                    className: 'form-control',
                    placeholder: placeholder,
                    value: this.props.value,
                    onChange: this.props.onChange,
                    ref: 'input'
                })
            ]);
        }
    });
});





