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

        let components = [];
        function setComponentType(element){
            switch(element.type){
                case "title":
                    components.push(<Title config={element}/>);
                    break;
                case "text":
                    components.push(<Text config={element}/>);
                    break;
                case "line":
                    components.push(<Line config={element}/>);
                    break;
                case "button":
                    components.push(<Button config={element} />);
                    break;
                case "button-back":
                    components.push(<ButtonBack config={element} />);
                    break;
                case "button-delete":
                    components.push(<ButtonDelete config={element} />);
                    break;
                case "button-form":
                    components.push(<ButtonForm config={element} />);
                    break;
                case "table":
                    components.push(<Table config={element}/>);
                    break;
                case "input":
                    components.push(<Form config={element}/>);
                    break;
                case "image":
                    components.push(<Image config={element} />);
                    break;
                case "checkbox":
                    components.push(<Form config={element} />);
                    break;
                default:
                    console.log('unknown type of component');
                    break;
            }
        }

        config.body.items.map((element)=>{
            setComponentType(element.item);
        });

        return <main className={'container'}>{components}</main>
    }
}

Main = connect(
    state =>  state.pageData,
    { getPageDataInfo }
)(Main)

export default Main;