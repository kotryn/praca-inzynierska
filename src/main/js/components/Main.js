import React from 'react';
import { connect } from 'react-redux'

import {getPageDataInfo} from './../actions/data'
import Text from './elements/Text'
import Button from './elements/Button'
import ButtonBack from './elements/ButtonBack'
import ButtonDelete from './elements/ButtonDelete'
import ButtonForm from './elements/ButtonForm'
import Table from './elements/Table'
import Title from './elements/Title'
import Form from "./elements/Form"
import List from "./elements/List"
import Graph from "./elements/Graph"
import Dropdown from "./elements/Dropdown"
import ServerError from "./ServerError"


class Main extends React.Component{
    componentDidMount() {
        this.props.getPageDataInfo()
    }

    render() {
        const {fetching, error, config, status} = this.props;
        if(fetching){
            return (
                <div className={'container'}>
                    <main><i className="fas fa-spinner fa-spin spinner"></i></main>
                </div>
            )
        }

        if(error){
            return (
                <div className={'container-fluid'}>
                    <div className={'col col-lg-6 col-md-10 col-sm-12 col-12 offset-lg-3 offset-md-1 m-container -center'}>
                        <ServerError error={error} url={config.startPage} />
                    </div>
                </div>
            )
        }

        let dropdownIndex = 0;
        function setComponentType(element, component, index){
            switch(element.type){
                case "title":
                    component.push(<Title config={element} key={index} />);
                    break;
                case "text":
                    component.push(<Text config={element} key={index} />);
                    break;
                case "text-header":
                    component.push(<Text config={element} key={index} />);
                    break;
                case "button":
                    component.push(<Button config={element} key={index}  />);
                    break;
                case "button-start-page":
                    component.push(<Button config={element} key={index}  />);
                    break;
                case "button-back":
                    component.push(<ButtonBack config={element} key={index}  />);
                    break;
                case "button-delete":
                    component.push(<ButtonDelete config={element} key={index}  />);
                    break;
                case "button-form":
                    component.push(<ButtonForm config={element} key={index}  />);
                    break;
                case "table":
                    component.push(<Table config={element} key={index} />);
                    break;
                case "input":
                    component.push(<Form config={element} key={index} />);
                    break;
                case "input-date":
                    component.push(<Form config={element} key={index} />);
                    break;
                case "input-number":
                    component.push(<Form config={element} key={index} />);
                    break;
                case "checkbox":
                    component.push(<Form config={element} key={index}  />);
                    break;
                case "radio":
                    component.push(<Form config={element} key={index}  />);
                    break;
                case "list":
                    component.push(<List config={element} key={index}  />);
                    break;
                case "dropdown":
                    let componentName = [];
                    element.entities.map((e, i)=>{
                        setComponentType(e.entity, componentName, i);
                    });
                    component.push(<Dropdown config={element} key={index} component={componentName} index={dropdownIndex} />);
                    dropdownIndex++;
                    break;
                case "graph":
                    component.push(<Graph config={element} key={index}  />);
                    break;
                default:
                    console.log('unknown type of component');
                    break;
            }
        }

        let components = [];
        config.body && config.body.entities.map((element, index)=>{
            setComponentType(element.entity, components, index);
        });

        let headerComponents = [];
        config.header && config.header.entities.map((element, index)=>{
            setComponentType(element.entity, headerComponents, index);
        });

        return (
            <div className={'container-fluid'}>
                <div className={'col col-lg-6 col-md-10 col-sm-12 col-12 offset-lg-3 offset-md-1 m-container -center'}>
                    <header>{headerComponents}</header>
                    <main>{components}</main>
                </div>
            </div>
        )

    }
}

Main = connect(
    state =>  state.pageData,
    { getPageDataInfo }
)(Main)

export default Main;