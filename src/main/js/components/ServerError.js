import React from 'react';

import Button from "./elements/Button"

function ServerError({error}){
    const config = {
        "name": "Refresh",
        "url": "http://Lenovo-320:8081//data"
    };
    return (
        <div className={"server-error"}>
            <h1>Something went wrong...</h1>
            <Button config={config}  />
        </div>
    )
}

export default ServerError;