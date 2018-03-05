import React from 'react';
import { connect } from 'react-redux'

import {getPageDataInfo} from './../actions/data'
import TextComponent from './elements/TextComponent'
import LineComponent from './elements/LineComponent'
import ButtonComponent from './elements/ButtonComponent'
import TableComponent from './elements/TableComponent'
import TitleComponent from './elements/TitleComponent'
import FormInputComponent from "./elements/FormInputComponent";

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
            //console.log(this.props, fetching, error, config, status);
            return <div>loading...</div>
        }

        let components = [];
        function setComponentType(element){
            switch(element.type){
                case "title":
                    components.push(<TitleComponent config={element}/>);
                    break;
                case "text":
                    components.push(<TextComponent config={element}/>);
                    break;
                case "line":
                    components.push(<LineComponent config={element}/>);
                    break;
                case "button":
                    components.push(<ButtonComponent name={element.name} url={element.url} fnc={null}/>);
                    break;
                case "table":
                    components.push(<TableComponent config={element}/>);
                    break;
                case "form":
                    components.push(<FormInputComponent config={element}/>);
                    break;
                default:
                    console.log('unknown type of component');
                    break;
            }
        }

        config.body.items.map((element)=>{
            setComponentType(element);
        });

        return <main>{components}</main>
    }
}

Main = connect(
    state =>  state.pageData,
    { getPageDataInfo }
)(Main)

export default Main;