import React from 'react';

class Table extends React.Component{

    constructor(props) {
        super(props);
        console.log(props);
    }

    render() {
        const {data} = this.props.config;

         const title = data.map((element, index)=>(
            <div className={"table-name"} key={index}>
                {element[0]}
            </div>
        ));


        const items = data.map((element, index)=>{
            const obj = element.map((e, i)=>{
                if(i !== 0){
                    return <div className={"table-element"} key={i}>{e}</div>
                }
            });
            return<div className={"table-column"} key={index}>{obj}</div>
        });

        /*const newItems = query.map((e, i)=>{
            const obj = Object.values(e).map((el, ind)=>{
                return <td>{el}</td>
            });
            return<tr>{obj}</tr>
        });*/

        return (
            <div className={"table"}>
                <div className={"table-title"}>
                    {title}
                </div>
                <div className={"table-data"}>
                    {items}
                </div>
            </div>
        );
        //return <div>WORKING...</div>
    }
}

export default Table;