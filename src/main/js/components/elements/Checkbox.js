import React from 'react';

class Checkbox extends React.Component{

    constructor(props) {
        super(props);
        this.state = {
            isCheck: false
        }
        this.handleInputChange = this.handleInputChange.bind(this);
    }

    handleInputChange(event) {
        const target = event.target;
        const value = target.type === 'checkbox' ? target.checked : target.value;
        const name = target.name;

        this.setState({
            [name]: value
        });
    }

    render() {
        const {values, names} = this.props.config;

        const component = values.map((element, index) => (
            <div key={index}>
                <label>
                    <input
                        name={element}
                        type="checkbox"
                        checked={this.state.isCheck}
                        onChange={this.handleInputChange} />
                        {names[index]}
                </label>
            </div>
        ));

        return (
            <form className={"form-checkbox"}>
                {component}
            </form>
        );
    }
}

export default Checkbox;