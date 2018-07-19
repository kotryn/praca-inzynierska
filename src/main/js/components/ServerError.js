import React from 'react';

import Button from "./elements/Button"

function ServerError({error, config}){
    if(!config || config.length === 0){
        config = {
            "name": "Refresh",
            "url": "http://localhost:8080/data"
        };
    }else{
        config.name = config.name || "Refresh";
        config.url = config.url || "http://localhost:8080/data";
    }
    return (
        <div className={"server-error"}>
            <h1>Something went wrong...</h1>
            <Button config={config}  />
        </div>
    )
}

export default ServerError;