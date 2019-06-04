import React, {Component} from 'react';
import "./App.css";
import Map from "./Map";
import LocationSelector from "./LocationSelector";

class App extends Component {
  render() {
    return (
      <div className="center-text">
        UW Campus Path Finder
        <Map/>
      </div>
    );
  }
}

export default App;
