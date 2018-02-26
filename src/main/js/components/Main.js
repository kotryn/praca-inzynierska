import React from 'react';
import axios from 'axios';

import TextComponent from './elements/TextComponent'
import LineComponent from './LineComponent'
import ButtonComponent from './elements/ButtonComponent'
import TableComponent from './elements/TableComponent'
import TitleComponent from './elements/TitleComponent'
import FormInputComponent from "./elements/FormInputComponent";

class Main extends React.Component{

    constructor(props) {
        super(props);
        this.state = {
            config: [],
            loading: true
        };
    }

    componentDidMount() {
        axios
            .get(this.props.url, {
                headers: {
                    'Content-type': 'application/json'
                }
            })
            .then(res => {
                this.setState({config: res.data, loading: false});
            })
            .catch(error => console.log(error))
    }

    render() {
        if(this.state.loading){
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

        this.state.config.body.items.map((element)=>{
            setComponentType(element);
        });

        return <main>{components}</main>
    }
}

export default Main;