import React from 'react';

import Button from "./elements/Button"

function ServerError({error, url}){
    return (
        <div className={"server-error"}>
            <h1>Something went wrong...</h1>
            <Button config={{name: "Start page", url: url}}  />
        </div>
    )
}

export default ServerError;