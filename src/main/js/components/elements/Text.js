import React from 'react';

function Text({config}){
    if(config.type === "text-navbar"){
        return <div className={"text text-navbar"}>{config.text}</div>
    }
    return <div className={"text"}>{config.text}</div>
}

export default Text;