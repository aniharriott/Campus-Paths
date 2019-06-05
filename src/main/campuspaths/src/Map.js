import React, {Component} from 'react';
import "./Map.css";
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
          start: "",
          end: "",
          path: ""
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
    let responsePromise = fetch("http://localhost:4567/findPath/" + this.state.start + "/" + this.state.end);
    let responseTextPromise = responsePromise.then((res) => {return res.json()});
    responseTextPromise.then(
        (responseText) => {
            this.setState({
                path: responseText.path[0].start.x
            });
            for (let i = 0; i < responseText.path.length; i++) {
                let x1 = responseText.path[i].start.x;
                let y1 = responseText.path[i].start.y;
                let x2 = responseText.path[i].end.x;
                let y2 = responseText.path[i].end.y;
                this.drawLine(x1, y1, x2, y2);
            }
        },
        (error) => {
            alert(error);
        }
    );
  }

  drawLine = (x1, y1, x2, y2) => {
    let ctx = this.canvasReference.current.getContext('2d');
    ctx.beginPath();
    ctx.moveTo(x1, y1);
    ctx.lineTo(x2, y2);
    ctx.lineWidth = 10;
    ctx.strokeStyle = "red";
    ctx.stroke();
  }

  changeStart = (event) => {
    this.setState({start: event.target.value});
  }

  changeEnd = (event) => {
    this.setState({end: event.target.value});
  }

  render() {
    return (
        <div className="canvasHolder">
            {this.state.path}
            <InputBox label="Start" value={this.state.start} onChange={this.changeStart} />
            <InputBox label="End" value={this.state.end} onChange={this.changeEnd} />
            <Button color="primary" onClick={this.handleClick}>
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