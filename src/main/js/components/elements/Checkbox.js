import React from 'react';
import {editInputValue} from "../../actions/formData";
import {connect} from "react-redux";

class Checkbox extends React.Component{
    handle(e) {
        this.props.editInputValue(e.target.checked,  this.props.id);
    }

    render() {
        const {name, id, values} = this.props;

        return (
            <label className={'checkbox'}>
                <input
                    type="checkbox"
                    checked={values[id]}
                    onChange={this.handle.bind(this)} />
                {name}
            </label>
        );
    }
}

Checkbox = connect(
    state =>  state.formData,
    { editInputValue }
)(Checkbox)

export default Checkbox;