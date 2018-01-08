import React from 'react';

class Book extends React.Component{
    constructor(props) {
        super(props);
    }

    render() {
        return (
            <table>
                <tbody>
                <tr>
                    <th>Book name</th>
                    <th>Date</th>
                </tr>
                <td>
                    First book
                </td>
                <td>
                    2012
                </td>
                </tbody>
            </table>
        )
    }
}

export default Book;