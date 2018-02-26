import React from 'react';
import { Link } from 'react-router-dom'

function ButtonComponent ({name, url, fnc}){
    return <Link to={url} onClick={fnc}>{name}</Link>
}

export default ButtonComponent;