import React from 'react';

class Book extends React.Component{
    constructor(props) {
        super(props);
    }

    render() {
        return (
            <tr>
                <td>{this.props.books.name}</td>
                <td>{this.props.books.date}</td>
            </tr>
        )
    }
}

export default Book;