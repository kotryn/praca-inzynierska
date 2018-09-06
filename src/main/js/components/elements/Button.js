import React from 'react'
import {connect} from 'react-redux'
import axios from 'axios'

import {fetching, getPageDataInfo, setError} from '../../actions/data'

class Button extends React.Component{

    constructor(props) {
        super(props)
        this.next = this.next.bind(this)
    }

    next(event) {
        event.preventDefault()
        const {url} = this.props.config
        this.props.fetching()

        axios.post(url, '')
            .then(response => {
                if(response.status === 200){
                    this.props.getPageDataInfo()
                }else{
                    this.props.setError("server error")
                }
            })
            .catch(error => {console.log(error); this.props.setError("server error")})
    }

    render() {
        const {name, type} = this.props.config
        if(type === 'button-home'){
            return (
                <button className={"btn dark-blue home"} onClick={this.next}>
                    <i className="fas fa-home"></i>
                </button>
            )}

        return (
                <button className={"btn dark-blue"} onClick={this.next}>{name}</button>
        )
    }
}

Button = connect(
    null,
    { getPageDataInfo, setError, fetching }
)(Button)

export default Button