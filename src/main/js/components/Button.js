import React from 'react';
import { Link } from 'react-router-dom'

function Button ({button}){
    return <Link to={button.url}>{button.title}</Link>
}

export default Button;