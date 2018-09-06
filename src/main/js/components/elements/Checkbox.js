import React from 'react'
import {editInputValue} from "../../actions/formData"
import {connect} from "react-redux"

class Checkbox extends React.Component{
    handle(e) {
        this.props.editInputValue(e.target.checked,  this.props.id)
    }

    render() {
        const {name, id, values, value} = this.props

        return (
            <div className="checkbox">
                <label className={'checkbox-container checkbox'}>
                    <input
                        type="checkbox"
                        checked={values[id]}
                        onChange={this.handle.bind(this)}
                        value={value}
                        name={value}
                    />
                    {name}
                    <span className="checkbox-checkmark"></span>
                </label>
            </div>
        )
    }
}

Checkbox = connect(
    state =>  state.formData,
    { editInputValue }
)(Checkbox)

export default Checkbox