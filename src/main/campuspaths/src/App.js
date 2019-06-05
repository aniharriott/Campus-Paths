import React, {Component} from 'react';
import "./App.css";
import MainContainer from "./MainContainer";

class App extends Component {
  render() {
    return (
      <div className="center-text">
        UW Campus Path Finder
        <MainContainer/>
      </div>
    );
  }
}

export default App;
