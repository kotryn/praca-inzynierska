import React from 'react';
import axios from 'axios';

import Title from './Title'
import Table from './Table'
import Form from './Form'
import Button from './Button'

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
            .get(this.props.url)
            .then(res => {
                this.setState({config: res.data, loading: false});
            })
            .catch(error => console.log(error))
    }

    render() {
        if(this.state.loading){
            return <div>loading...</div>
        }
        const {title, table, form, button} = this.state.config;
        let titleComponent = "", tableComponent = "", formComponent = "", buttonComponent = "";

        if(title){
            titleComponent = <Title title={title}/>
        }
        if(table){
            tableComponent = <Table table={table} />
        }
        if(form){
            formComponent = <Form form={form} />
        }
        if(button){
            buttonComponent = <Button button={button} />
        }

        return (
            <div>
                {titleComponent}
                {tableComponent}
                {formComponent}
                {buttonComponent}
            </div>
        )
    }
}

export default Main;