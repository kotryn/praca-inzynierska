import React from 'react';

function Title ({config}){
    switch (config.tag){
        case "h1":
            return <h1>{config.text}</h1>
        case "h2":
            return <h2>{config.text}</h2>
        case "h3":
            return <h3 style={{textAlign: "left"}}>{config.text}</h3>
        case "h4":
            return <h4 style={{textAlign: "left"}}>{config.text}</h4>
        case "h5":
            return <h5>{config.text}</h5>
        case "h6":
            return <h6>{config.text}</h6>
        default:
            return <h1>{config.text}</h1>
    }
}

export default Title;