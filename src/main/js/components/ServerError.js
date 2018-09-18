import React from 'react'

import Button from "./elements/Button"

function ServerError({error}){
    const config = {
        "name": "Refresh",
        "url": "http://kotryn.localtunnel.me/data"
    }
    return (
        <div className={"server-error"}>
            <h1>Something went wrong...</h1>
            <Button config={config}  />
        </div>
    )
}

export default ServerError