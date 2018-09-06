import React from 'react'

class Table extends React.Component{
    render() {
        const {data} = this.props.config

        let width = 100 / data.length + "%"

         const name = data.map((element, index)=>(
            <th key={index}>
                {element[0]}
            </th>
        ))

        var min = Number.MAX_SAFE_INTEGER

        var test = []
        data.map((element, index)=>{
            test.push([])
            min = element.length < min ? element.length : min
            element.map((e, i)=>{
                if(i !== 0){
                    test[index].push(<td key={i}>{e}</td>)
                }
            })
        })

        let tableData = []
        let tempTableData = []
        for(let i in test){
            for (let j in test[i]){
                let temp = []
                for(let n in test){
                    temp.push(test[n][j])
                }
                tempTableData.push(<tr style={{minWidth: width}}>{temp}</tr>)
            }
        }

        for(let k = 0; k < min-1; k++){
            tableData.push(tempTableData[k])
        }

        return (
            <div className={"table m-table"}>
                <table>
                    <tr style={{minWidth: width}} className={'table-name'}>
                        {name}
                    </tr>
                    {tableData}
                </table>
            </div>
        )
    }
}

export default Table