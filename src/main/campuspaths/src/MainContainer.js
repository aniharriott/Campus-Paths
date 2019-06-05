import React, {Component} from 'react';
import InputBox from "./InputBox";
import "./App.css";
import Map from "./Map";

class MainContainer extends Component {
    constructor(props) {
        super(props);
        this.state = {
            start: "",
            end: ""
        }
      }

    changeStart = (event) => {
        this.setState({start: event.target.value});
    }

    changeEnd = (event) => {
        this.setState({end: event.target.value});
    }

    render() {
        return (
          <div className="center-text">
          <InputBox label="Start" value={this.state.start} onChange={this.changeStart} />
          <InputBox label="End" value={this.state.end} onChange={this.changeEnd} />
          <Map start={this.state.start} end={this.state.end} />
          </div>
        );
      }
}

export default MainContainer;