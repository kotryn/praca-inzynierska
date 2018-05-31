import React from 'react';
import {connect} from "react-redux";

import {editInputValue} from "../../actions/formData";

class InputDate extends React.Component{

    constructor(props) {
        super(props);
    }

    handle(e) {
        this.props.editInputValue(e.target.value,  this.props.id);
    }

    render() {

        const {name, id} = this.props;

        return (
            <label>
                <div>{name}</div>
                <input type="date" name="value" value={this.props.values[id]} onChange={this.handle.bind(this)} />
            </label>
        );
    }
}

InputDate = connect(
    state =>  state.formData,
    { editInputValue }
)(InputDate)

export default InputDate;