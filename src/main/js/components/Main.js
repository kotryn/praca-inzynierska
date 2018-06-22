import React from 'react';
import { connect } from 'react-redux'

import {getPageDataInfo} from './../actions/data'
import Text from './elements/Text'
import Line from './elements/Line'
import Button from './elements/Button'
import ButtonBack from './elements/ButtonBack'
import ButtonDelete from './elements/ButtonDelete'
import ButtonForm from './elements/ButtonForm'
import Table from './elements/Table'
import Title from './elements/Title'
import Image from "./elements/Image"
import Form from "./elements/Form"
import List from "./elements/List"
import Graph from "./elements/Graph"
import Dropdown from "./elements/Dropdown"


class Main extends React.Component{
    componentDidMount() {
        this.props.getPageDataInfo()
    }

    render() {
        const {fetching, error, config, status} = this.props;
        if(fetching){
            return <div>loading...</div>
        }

        if(error){
            return <div>{status}</div>
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
                case "text-navbar":
                    component.push(<Text config={element} key={index} />);
                    break;
                case "line":
                    component.push(<Line config={element} key={index} />);
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
                case "input-integer":
                    component.push(<Form config={element} key={index} />);
                    break;
                case "image":
                    component.push(<Image config={element} key={index}  />);
                    break;
                case "checkbox":
                    component.push(<Form config={element} key={index}  />);
                    break;
                case "radio-button":
                    component.push(<Form config={element} key={index}  />);
                    break;
                case "list":
                    component.push(<List config={element} key={index}  />);
                    break;
                case "dropdown":
                    let componentName = [];
                    element.items.map((e, i)=>{
                        setComponentType(e.item, componentName, i);
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
        config.body && config.body.items.map((element, index)=>{
            setComponentType(element.item, components, index);
        });

        let navbarComponents = [];
        config.navbar && config.navbar.items.map((element, index)=>{
            setComponentType(element.item, navbarComponents, index);
        });

        return (
            <div className={'container'}>
                <header>{navbarComponents}</header>
                <main>{components}</main>
            </div>
        )
    }
}

Main = connect(
    state =>  state.pageData,
    { getPageDataInfo }
)(Main)

export default Main;