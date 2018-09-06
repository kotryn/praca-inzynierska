import React from 'react'

class List extends React.Component{
    render() {
        const {items} = this.props.config
        const component = items.map((e, i) => (
            <li key={i} className={"list"}>{e}</li>
        ))

        return (
            <ul>
                {component}
            </ul>
        )
    }
}

export default List