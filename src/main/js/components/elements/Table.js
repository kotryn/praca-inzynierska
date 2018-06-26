import React from 'react';

class Table extends React.Component{
    render() {
        const {data} = this.props.config;

        let width = 100 / data.length + "%";

         const title = data.map((element, index)=>(
            <th key={index}>
                {element[0]}
            </th>
        ));

        var test = [];
        data.map((element, index)=>{
            test.push([]);
            element.map((e, i)=>{
                if(i !== 0){
                    test[index].push(<td key={i}>{e}</td>);
                }
            });
        });

        let tableData = [];
        let tempTableData = [];
        for(let i in test){
            for (let j in test[i]){
                let temp = [];
                for(let n in test){
                    temp.push(test[n][j]);
                }
                tempTableData.push(<tr style={{minWidth: width}}>{temp}</tr>)
            }
        }

        for(let k in test[0]){
            tableData.push(tempTableData[k]);
        }

        return (
            <div className={"table m-table"}>
                <table>
                    <tr style={{minWidth: width}} className={'table-title'}>
                        {title}
                    </tr>
                    {tableData}
                </table>
            </div>
        );
    }
}

export default Table;