import React from 'react';
import {editInputValue} from "../../actions/formData";
import {connect} from "react-redux";

class RadioButton extends React.Component{
    /*constructor(props) {
        super(props);
        this.state = {
            name: false
        };
        //this._setFormData(this.props.config, this.props);
    }*/
    handle(e) {

        this.props.editInputValue(e.target.values,  this.props.id);
        console.log('mmm', this.props.values[this.props.id], this.props.name);
    }

    /*onSiteChanged(e) {
        this.setState({
            name: e.currentTarget.value
        });
        console.log(this.state.name);
    }*/

    render() {
        const {name, id, values} = this.props;

        return (
            <label className={'radio-button'}>
                <input
                    type="radio"
                    name={"radio_name"}
                    value={name}
                    checked={values[id] === name}
                    onChange={this.handle.bind(this)} />
                {name}
            </label>
        );
    }
}

RadioButton = connect(
    state =>  state.formData,
    { editInputValue }
)(RadioButton)

export default RadioButton;