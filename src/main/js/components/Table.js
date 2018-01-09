import React from 'react';
import { connect } from 'react-redux'
import axios from 'axios';

import Users from './User/Users'
import Books from './Book/Book'
import Films from './Film/Films'

class Table extends React.Component{

    constructor(props) {
        super(props);
        this.state = {
            data: [],
            loading: true
        };
    }

    componentDidMount() {
        axios
            .get(this.props.table.data.url)
            .then(res => {
                this.setState({
                    data: res.data,
                    loading: false
                });
            })
            .catch(error => console.log(error))
    }

    render() {
        if(this.state.loading){
            return <div>loading...</div>
        }
        const data = this.state.data;
        const {table, query} = this.props;

        let items = "", newItems = "";

        switch (table.type){
            case "users":
                items = data.map(item =>
                    <Users key={item.id} users={item}/>
                );
                newItems = query.map((element, index) =>
                    <Users key={index} users={element}/>
                );
                break;
            case "books":
                items = data.map(item =>
                    <Books key={item.id} books={item}/>
                );
                break;
            case "article":
                items = data.map(item =>
                    <Films key={item.id} film={item}/>
                );
                break;
            default:
                return <div>ERROR</div>
        }

        const title = table.title && table.title.map((item, index)=>
            <th key={index}>{item}</th>
        );


        return (
            <table>
                <tbody>
                <tr>
                    {title}
                </tr>
                {items}
                {newItems}
                </tbody>
            </table>
        )
    }
}

Table = connect(
    state =>  state.user,
    null
)(Table)


export default Table;