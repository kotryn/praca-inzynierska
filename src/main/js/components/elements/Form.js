import React from 'react';
import {connect} from "react-redux";

import Checkbox from './Checkbox'
import Radio from './Radio'
import Input from "./Input"
import { createInputData, clear, createOutputData, deleteOutputElement, changeNextId, changeCheckboxData, changeRadioData } from "../../actions/formData";

class Form extends React.Component{

    constructor(props) {
        super(props);
        this.state = {
            id: this.props.currentId
        };
        this._setFormData(this.props.config, this.props);
    }

    _setFormData({type, values, value, names}, {createInputData, changeNextId}){
        let formData = [];
        switch(type){
            case "checkbox":
                formData = values.map(()=> false);
                break;
            case "radio":
                formData = values.map((e, i)=> {
                    if(i === 0){
                        return names[i]
                    }return false
                });
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
        const {values, type, value} = this.props.config;
        let output = {values};
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
            case "radio":
                output.values.forEach((e, i)=>{
                    output[e] = this.props.values[id+i];
                    if(output[e] !== false){
                        this.props.changeRadioData(output[e], value);
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
        const {names, type, kind, values} = this.props.config;
        const id = this.state.id;

        let ComponentName = "";
        let style = "";
        switch(type){
            case "checkbox":
                ComponentName = Checkbox;
                style = "form-checkbox";
                break;
            case "radio":
                return (
                    <Radio names={names} id={id} />
                );
            case "input":
                ComponentName = Input;
                style = "form-text";
                break;
            default:
                console.log("Form: unknown type - componentName!!!");
                return;
        }

        const component = names.map((element, index) => (
            <ComponentName key={index} name={element} id={id+index} kind={kind} value={values[index]}/>
        ));

        return (
            <form className={style} autoComplete={"off"}>
                {component}
            </form>
        );
    }
}

Form = connect(
    state =>  state.formData,
    { createInputData, clear, createOutputData, deleteOutputElement, changeNextId, changeCheckboxData, changeRadioData }
)(Form)

export default Form;