import React from 'react';

function Text({config}){
    if(config.type === "text-header"){
        return <div className={"text text-header"}>{config.text}</div>
    }
    return <div className={"text"}>{config.text}</div>
}

export default Text;