import React from 'react'
import {CartesianGrid, Legend, Line, LineChart, ResponsiveContainer, Tooltip, XAxis, YAxis} from 'recharts'
import {getRandomColor} from "../../getRandomColor"

class Graph extends React.Component{
    render() {
        const {data, name} = this.props.config

        let graphData = []
        const LineComponent = data.map((element, index) => {
            let i = 0
            for (let k in element) {
                if (element.hasOwnProperty(k)) {
                    let obj = {}
                    obj[name[index]] = element[k]
                    let newObj = Object.assign(obj, {name: k})
                    graphData[i] = Object.assign(newObj,  graphData[i++])
                }
            }
            return (<Line type="monotone" dataKey={name[index]} stroke={getRandomColor()} key={index} />)
        })

        return (
            <div className={'graph'}>
                <ResponsiveContainer className={'graph-container'} >
                    <LineChart width={730} height={400} data={graphData}
                               margin={{ top: 5, right: 30, left: 20, bottom: 5 }}>
                        <CartesianGrid strokeDasharray="3 3" />
                        <XAxis dataKey="name" />
                        <YAxis/>
                        <Tooltip />
                        <Legend />
                        {LineComponent}
                    </LineChart>
                </ResponsiveContainer>
            </div>
        )
    }
}

export default Graph