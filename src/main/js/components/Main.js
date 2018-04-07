import React from 'react';
import { connect } from 'react-redux'

import {getPageDataInfo} from './../actions/data'
import TextComponent from './elements/TextComponent'
import LineComponent from './elements/LineComponent'
import ButtonComponent from './elements/ButtonComponent'
import ButtonBackComponent from './elements/ButtonBackComponent'
import ButtonDeleteComponent from './elements/ButtonDeleteComponent'
import TableComponent from './elements/TableComponent'
import TitleComponent from './elements/TitleComponent'
import FormComponent from "./elements/FormComponent";
import ImageComponent from "./elements/ImageComponent"

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
                    components.push(<ButtonComponent config={element} />);
                    break;
                case "button-back":
                    components.push(<ButtonBackComponent config={element} />);
                    break;
                case "button-delete":
                    components.push(<ButtonDeleteComponent config={element} />);
                    break;
                case "table":
                    components.push(<TableComponent config={element}/>);
                    break;
                case "form":
                    components.push(<FormComponent config={element}/>);
                    break;
                case "image":
                    components.push(<ImageComponent config={element} />);
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