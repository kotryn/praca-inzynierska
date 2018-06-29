import React from 'react';
import {editInputValue} from "../../actions/formData";
import {connect} from "react-redux";

class Radio extends React.Component{
    constructor(props) {
        super(props);
        this.state = {
            name: this.props.names[0]
        };
    }

    onButtonChange(e) {
        const {names} = this.props;
        this.setState({
            name: e.currentTarget.value
        });

        names.map((element, index) => {
            if(e.currentTarget.value === element){
                this.props.editInputValue(e.currentTarget.value,  this.props.id+index);
            }else{
                this.props.editInputValue(false,  this.props.id+index);
            }
        });
    }

    render() {
        const {names} = this.props;

        const component = names.map((element, index) => (
            <label className={'radio radio-container'} key={index}>
                <input
                    type="radio"
                    name="radio"
                    value={element}
                    checked={this.state.name === element}
                    onChange={this.onButtonChange.bind(this)}/>
                {element}
                <span className="radio-checkmark"></span>
            </label>
        ));

        return (
            <form className={"radio"}>{component}</form>
        );
    }
}

Radio = connect(
    state =>  state.formData,
    { editInputValue }
)(Radio)

export default Radio;