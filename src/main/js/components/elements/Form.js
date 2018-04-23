import React from 'react';
import {connect} from "react-redux";

import Checkbox from './Checkbox'
import Input from "./Input"
import { createInputData, clear, createOutputData, deleteOutputElement, changeNextId, changeCheckboxData } from "../../actions/formData";

class Form extends React.Component{

    constructor(props) {
        super(props);
        this.state = {
            id: this.props.currentId
        };
        this._setFormData(this.props.config, this.props);
    }

    _setFormData({type, values}, {createInputData, changeNextId}){
        let formData = [];
        switch(type){
            case "checkbox":
                formData = values.map(()=> false);
                break;
            case "input":
                formData = values.map(()=> "");
                break;
            default:
                console.log("Form: unknown type - formData!!!");
                return;
        }
        createInputData(formData);
        changeNextId(values.length);
    }

    componentWillUnmount(){
        this.props.clear();
    }

    shouldComponentUpdate(nextProps){
        return nextProps.jsonData === this.props.jsonData;
    }

    componentDidUpdate(){
        this.props.createOutputData(this.createOutputData());
    }

    createOutputData(){
        const {values, type} = this.props.config; //values - wartosci elementow, type - checkbox / input
        let output = {values}; //output -> AMC: true
        let checkboxes = new Set([...this.props.checkbox]);
        const id = this.state.id;

        switch(type){
            case "checkbox":
                output.values.forEach((e, i)=>{
                        output[e] = this.props.values[id+i]; //this.props.values - wartosci elementow
                        if(output[e]){
                            checkboxes.add(e);
                            this.props.changeCheckboxData(checkboxes);
                        }else{
                            this.props.deleteOutputElement(e);
                        }
                });
                return null;
            case "input":
                output.values.forEach((e, i)=>{
                    output[e] = this.props.values[id+i];
                });
                return JSON.parse(JSON.stringify(output));
            default:
                console.log("Form: unknown type - jsonData!!!");
                return;
        }
    }

    render() {
        const {names, type} = this.props.config;
        const id = this.state.id;

        let ComponentName = "";
        let style = "";
        switch(type){
            case "checkbox":
                ComponentName = Checkbox;
                style = "form-checkbox";
                break;
            case "input":
                ComponentName = Input;
                style = "form-text";
                break;
            default:
                console.log("Form: unknown type - componentName!!!");
                return;
        }

        const component = names.map((element, index) => (
            <ComponentName key={index} name={element} id={id+index} />
        ));

        return (
            <form className={style}>
                {component}
            </form>
        );
    }
}

Form = connect(
    state =>  state.formData,
    { createInputData, clear, createOutputData, deleteOutputElement, changeNextId, changeCheckboxData }
)(Form)

export default Form;