import React, {Component} from 'react';
import "./Map.css";
import LocationSelector from "./LocationSelector";
import InputBox from "./InputBox";
import Button from '@material-ui/core/Button';
import * as fetch from "node-fetch";

class Map extends Component {

  // NOTE:
  // This component is a suggestion for you to use, if you would like to.
  // It has some skeleton code that helps set up some of the more difficult parts
  // of getting <canvas> elements to display nicely.
  //
  // If you don't want to use this component, you're free to delete it.

  constructor(props) {
    super(props);
    this.state = {
          start: "KNE",
          end: "MGH"
        }
    this.backgroundImage = new Image();
    this.canvasReference = React.createRef();
    this.backgroundImage.onload = () => {
      this.drawBackgroundImage();
    };
    this.backgroundImage.src = "campus_map.jpg";
  }

  drawBackgroundImage () {
    let canvas = this.canvasReference.current;
    let ctx = canvas.getContext("2d");
    //
    if (this.backgroundImage.complete) { // This means the image has been loaded.
      canvas.width = this.backgroundImage.width;
      canvas.height = this.backgroundImage.height;
      ctx.drawImage(this.backgroundImage, 0, 0);
    }
  }

  handleClick = () => {
    this.drawPath();
  }

  drawPath = () => {

  }

  render() {
    // TODO: You should put a <canvas> inside the <div>. It has a className
    // that's set up to center the canvas for you. See Map.css for more details.
    // Make sure you set up the React references for the canvas correctly so you
    // can get the canvas object and call methods on it.
    return (
        <div className="canvasHolder">
            <InputBox label="Start" />
            <InputBox label="End" />
            <Button color="primary" onClick={this.props.onClick}>
                Draw Path
            </Button>
            <Button color="secondary" onClick={this.props.onClick}>
                Clear Path
            </Button>
            <div className="canvas">
                <canvas ref={this.canvasReference}/>
            </div>
        </div>
    )
  }
}

export default Map;