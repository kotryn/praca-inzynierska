import React from 'react';

class Films extends React.Component{
    constructor(props) {
        super(props);
    }

    render() {
        return (
            <tr className={'article'}>
                <td className={'title'}>{this.props.film.name}</td>
                <td className={'description'}>{this.props.film.about}</td>
            </tr>
        )
    }
}

export default Films;