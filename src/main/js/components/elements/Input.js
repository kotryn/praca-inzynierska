import React from 'react';
import {connect} from "react-redux";

import {editInputValue} from "../../actions/formData";

class Input extends React.Component{
    handle(e) {
        this.props.editInputValue(e.target.value,  this.props.id);
    }

    render() {

        const {name, id, type} = this.props;

        let t;
        switch(type){
            case "input-number":
                t = "number";
                break;
            case "input-date":
                t = "date";
                break;
            default:
                t = "text";
        }

        return (
            <div className="form-group row">
                <label className="col-sm-auto col-form-label">
                    {name}
                </label>
                <div className="col-sm-auto">
                    <input type={t} className="form-control" name="value" value={this.props.values[id]} onChange={this.handle.bind(this)} />
                </div>

            </div>
        );
    }
}

Input = connect(
    state =>  state.formData,
    { editInputValue }
)(Input)

export default Input;