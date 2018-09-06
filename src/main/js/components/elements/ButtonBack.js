import React from 'react'
import {connect} from 'react-redux'
import axios from 'axios'

import {fetching, getPageDataInfo, setError} from '../../actions/data'

class ButtonBack extends React.Component{

    constructor(props) {
        super(props)
        this.back = this.back.bind(this)
    }

    back(event) {
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
        const {name} = this.props.config

        return (
            <button className={'btn grey'} onClick={this.back}>{name}</button>
        )
    }
}

ButtonBack = connect(
    null,
    { getPageDataInfo, setError, fetching }
)(ButtonBack)

export default ButtonBack