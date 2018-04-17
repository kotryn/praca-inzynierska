import React from 'react';
import { connect } from 'react-redux'
import axios from 'axios';

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
            .get(this.props.config.url)
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
        const {config, query} = this.props;

        const title = config.title.map((element, index)=>(
            <th key={index}>
                {element}
            </th>
        ));

        const items = data.map((e, i)=>{
            const obj = Object.values(e).map((el, ind)=>{
                if(Object.keys(e).indexOf('id') !== ind){
                    return <td>{el}</td>
                }
            });
            return<tr>{obj}</tr>
        });

        const newItems = query.map((e, i)=>{
            const obj = Object.values(e).map((el, ind)=>{
                return <td>{el}</td>
            });
            return<tr>{obj}</tr>
        });

        return (
            <table >
                <tbody>
                <tr>
                    {title}
                </tr>
                {items}
                {newItems}
                </tbody>
            </table>
        );
    }
}

Table = connect(
    state =>  state.inputFormData,
    null
)(Table)


export default Table;