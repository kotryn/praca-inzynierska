import React from 'react'
import {connect} from "react-redux"

import {editInputValue} from "../../actions/formData"

class Input extends React.Component{
    handle(e) {
        this.props.editInputValue(e.target.value,  this.props.id)
    }

    handleInteger(e) {
        if(e.target.validity.valid){
            this.props.editInputValue(e.target.value,  this.props.id)
        }

    }

    render() {
        const {name, id, kind, value} = this.props

        let t
        switch(kind){
            case "integer":
                t = (<input
                        type={"text"}
                        pattern="[0-9]*"
                        className="form-control"
                        name={value}
                        value={this.props.values[id]}
                        onChange={this.handleInteger.bind(this)}
                    />)
                break
            case "number":
                t = (<input
                        type={"number"}
                        className="form-control"
                        name={value}
                        value={this.props.values[id]}
                        onChange={this.handle.bind(this)}
                    />)
                break
            case "date":
                t = (<input
                        type={"date"}
                        className="form-control"
                        name={value}
                        value={this.props.values[id]}
                        onChange={this.handle.bind(this)}
                    />)
                break
            default:
                t = (<input
                        type={"text"}
                        className="form-control"
                        name={value}
                        value={this.props.values[id]}
                        onChange={this.handle.bind(this)}
                    />)
        }

        return (
            <div className="form-group row">
                <label className="col-sm-auto col-form-label">
                    {name}
                </label>
                <div className="col-sm-auto">
                    {t}
                </div>

            </div>
        )
    }
}

Input = connect(
    state =>  state.formData,
    { editInputValue }
)(Input)

export default Input