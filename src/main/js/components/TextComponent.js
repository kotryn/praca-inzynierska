import React from 'react';

function TextComponent({config}){
    return <div style={config.style}>{config.text}</div>
}

export default TextComponent;