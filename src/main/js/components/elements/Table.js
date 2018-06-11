import React from 'react';

class Table extends React.Component{
    render() {
        const {data} = this.props.config;

        let width = 100 / data.length + "%";

         const title = data.map((element, index)=>(
            <div className={"table-name"} style={{width: width}} key={index}>
                {element[0]}
            </div>
        ));


        const items = data.map((element, index)=>{
            const obj = element.map((e, i)=>{
                if(i !== 0){
                    return <div className={"table-element"} key={i}>{e}</div>
                }
            });
            return<div className={"table-column"} style={{width: width}} key={index}>{obj}</div>
        });

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
    }
}

export default Table;