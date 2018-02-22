import React from 'react';
import { Link } from 'react-router-dom'

function ButtonComponent ({config}){
    return <Link to={config.url} >{config.name}</Link>
}

export default ButtonComponent;