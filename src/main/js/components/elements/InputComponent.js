import React from 'react';
import {connect} from "react-redux";

import {editInputValue} from "../../actions/inputData";

class InputComponent extends React.Component{

    constructor(props) {
        super(props);
    }

    handleNameChange(e) {
        this.props.editInputValue(e.target.value,  this.props.id);
    }

    render() {

        const {name, id} = this.props;

        return (
            <label>
                <div>{name}</div>
                <input type="text" name="value" value={this.props.values[id]} onChange={this.handleNameChange.bind(this)} />
            </label>
        );
    }
}

InputComponent = connect(
    state =>  state.inputData,
    { editInputValue }
)(InputComponent)

export default InputComponent;