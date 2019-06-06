import React, {Component} from 'react';
import InputBox from "./InputBox";
import "./App.css";
import Map from "./Map";

class MainContainer extends Component {
    constructor(props) {
        super(props);
        this.state = {
            start: "",
            end: "",
            shortBuildings: [],
            longBuildings: []
        }
        this.getBuildings();
      }

    getBuildings = () => {
        let responsePromise = fetch("http://localhost:4567/findBuildings");
        let responseTextPromise = responsePromise.then((res) => {return res.json()});
        responseTextPromise.then(
            (responseText) => {
                this.setState({
                    shortBuildings: Object.keys(responseText),
                    longBuildings: Object.values(responseText)
                });
            },
            (error) => {
                alert(error);
            }
        );
    }

    printBuildings = () => {
        let string = [];
        for (let i = 0; i < this.state.shortBuildings.length; i++){
            string.push(this.state.shortBuildings[i] + " = " + this.state.longBuildings[i]);
        }
        return string.map(building => (
            <p>{building}</p>
        ));
    }

    changeStart = (event) => {
        this.setState({start: event.target.value.toUpperCase()});
    }

    changeEnd = (event) => {
        this.setState({end: event.target.value.toUpperCase()});
    }

    handleClear = () => {
        this.setState({
            start: "",
            end: ""
        });
    }

    render() {
        return (
          <div className="center-text">
          <p>Building Key Options:</p>
          <p>Key = Building</p>
          {this.printBuildings()}
          <InputBox label="Start" value={this.state.start} onChange={this.changeStart} />
          <InputBox label="End" value={this.state.end} onChange={this.changeEnd} />
          <Map start={this.state.start} end={this.state.end} onChange={this.handleClear}/>
          </div>
        );
      }
}

export default MainContainer;