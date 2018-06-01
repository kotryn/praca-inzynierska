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


class Main extends React.Component{

    constructor(props) {
        super(props);
    }

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

        function setComponentType(element, component){
            switch(element.type){
                case "title":
                    component.push(<Title config={element}/>);
                    break;
                case "text":
                    component.push(<Text config={element}/>);
                    break;
                case "text-navbar":
                    component.push(<Text config={element}/>);
                    break;
                case "line":
                    component.push(<Line config={element}/>);
                    break;
                case "button":
                    component.push(<Button config={element} />);
                    break;
                case "button-start-page":
                    component.push(<Button config={element} />);
                    break;
                case "button-back":
                    component.push(<ButtonBack config={element} />);
                    break;
                case "button-delete":
                    component.push(<ButtonDelete config={element} />);
                    break;
                case "button-form":
                    component.push(<ButtonForm config={element} />);
                    break;
                case "table":
                    component.push(<Table config={element}/>);
                    break;
                case "input":
                    component.push(<Form config={element}/>);
                    break;
                case "input-date":
                    component.push(<Form config={element}/>);
                    break;
                case "image":
                    component.push(<Image config={element} />);
                    break;
                case "checkbox":
                    component.push(<Form config={element} />);
                    break;
                case "list":
                    component.push(<List config={element} />);
                    break;
                default:
                    console.log('unknown type of component');
                    break;
            }
        }

        let components = [];
        config.body && config.body.items.map((element)=>{
            setComponentType(element.item, components);
        });

        let navbarComponents = [];
        config.navbar && config.navbar.items.map((element)=>{
            setComponentType(element.item, navbarComponents);
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