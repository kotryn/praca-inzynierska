import React from 'react';

function TitleComponent ({config}){
    return <h1 style={config.style}>{config.text}</h1>
}

export default TitleComponent;