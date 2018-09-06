import React from 'react'

function Title ({config}){
    switch (config.tag){
        case "h1":
            return <h1>{config.text}</h1>
        case "h2":
            return <h2>{config.text}</h2>
        case "h3":
            return <h3>{config.text}</h3>
        case "h4":
            return <h4>{config.text}</h4>
        case "h5":
            return <h5>{config.text}</h5>
        case "h6":
            return <h6>{config.text}</h6>
        case "h1-left":
            return <h1 className={"left"}>{config.text}</h1>
        case "h2-left":
            return <h2 className={"left"}>{config.text}</h2>
        case "h3-left":
            return <h3 className={"left"}>{config.text}</h3>
        case "h4-left":
            return <h4 className={"left"}>{config.text}</h4>
        case "h5-left":
            return <h5 className={"left"}>{config.text}</h5>
        case "h6-left":
            return <h6 className={"left"}>{config.text}</h6>
        default:
            return <h1 className={"left"}>{config.text}</h1>
    }
}

export default Title