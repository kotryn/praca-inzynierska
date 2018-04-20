import React from 'react';
import {connect} from "react-redux";

import {editInputValue} from "../../actions/formData";

class Input extends React.Component{

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
                <input type="text" name="value" value={this.props.values[id]} onChange={this.handle.bind(this)} />
            </label>
        );
    }
}

Input = connect(
    state =>  state.formData,
    { editInputValue }
)(Input)

export default Input;