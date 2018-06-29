import React from 'react';

class Dropdown extends React.Component{
    constructor(props){
        super(props);
        this.state = {
            isOpen: false
        }
    }

    toggleMenu(){
        this.setState({isOpen: !this.state.isOpen});
        this.setHeight();
    }

    setHeight(){
        let items = document.getElementById('e'+this.props.index);
        //console.log(items.parentNode.parentNode.parentNode)
        if(!this.state.isOpen){
            let wrapper = document.getElementById('w'+this.props.index);
            items.style.height = wrapper.clientHeight + "px";

            this.checkParent(items.parentNode.parentNode.parentNode, wrapper.clientHeight)



        }else{
            let wrapper = document.getElementById('w'+this.props.index);
            items.style.height = "0px";
            this.checkParent(items.parentNode.parentNode.parentNode, -wrapper.clientHeight)
        }
    }

    checkParent(element, oldHeight){
        if(element.className === "dropdown-items"){
            let height = element.style.height;
            height = +height.slice(0, height.length-2);
            element.style.height = height + oldHeight + "px";
            this.checkParent(element.parentNode.parentNode.parentNode, oldHeight)
        }
    }

    render() {
        const {component, config, index} = this.props;
        const {name} = config;

        return (
                <div className={"dropdown"}>
                    <div className="dropdown-name" onClick={() => this.toggleMenu()}>
                        {name}<i className={this.state.isOpen ? "fas fa-caret-up" : "fas fa-caret-down"}></i>

                    </div>
                    <div className={"dropdown-items"} id={"e"+index}>
                        <div className="wrapper" id={"w"+index}>
                            {component}
                        </div>
                    </div>
                </div>
        );
    }
}

export default Dropdown;