/*
 * Copyright ©2019 Hal Perkins.  All rights reserved.  Permission is
 * hereby granted to students registered for University of Washington
 * CSE 331 for use solely during Spring Quarter 2019 for purposes of
 * the course.  No other use, copying, distribution, or modification
 * is permitted without prior written consent. Copyrights for
 * third-party components of this work must be honored.  Instructors
 * interested in reusing these course materials should contact the
 * author.
 */

/* A simple grid with a variable size */
/* Most of the assignment involves changes to this class */

import React, {Component} from 'react';
import Button from './Button'

class Grid extends Component {
  constructor(props) {
    super(props);
    this.canvasReference = React.createRef();
  }

  componentDidMount() {
    this.redraw();
  }

  componentDidUpdate() {
    this.redraw()
  }

  redraw = () => {
    let ctx = this.canvasReference.current.getContext('2d');
    ctx.clearRect(0, 0, this.props.width, this.props.height);
    var background = new Image();
    background.onload = () => {
      ctx.drawImage(background,0,0);
      let coordinates = this.getCoordinates();
      coordinates.forEach(coordinate => {
        this.drawCircle(ctx, coordinate);
      });
    }
    background.src = "image.jpg"
  };

  getCoordinates = () => {
    const gridSize = parseInt(this.props.size);
    const dist = 400/(gridSize+1);
    let array = [];
    for (let i = 1; i <= gridSize; i++) {
        for (let j = 1; j <= gridSize; j++) {
            let tempArray = [];
            tempArray.push(i*dist);
            tempArray.push(j*dist);
            array.push(tempArray);
        }
    }
    return array;
  };

  drawCircle = (ctx, coordinate) => {
    ctx.beginPath();
    ctx.arc(coordinate[0], coordinate[1], 20 / this.props.size, 0, 2 * Math.PI);
    ctx.fill();
  };

  drawLines = () => {
    let ctx = this.canvasReference.current.getContext('2d');
    const dist = 400/(parseInt(this.props.size)+1);
    let inputString = this.props.edges;
    let totalArray = inputString.split("\n");
    console.log(totalArray);
    for (let i = 0; i < totalArray.length; i++) { // for each line input
        console.log("test");
        let line = totalArray[i].split(" "); // array of two points and a color
        let point1 = line[0].split(",");
        let point2 = line[1].split(",");
        let color = line[2];
        console.log(color);
        // draw the line
        let x1 = (parseInt(point1[0])+1)*dist;
        let y1 = (parseInt(point1[1])+1)*dist;
        let x2 = (parseInt(point2[0])+1)*dist;
        let y2 = (parseInt(point2[1])+1)*dist;
        ctx.beginPath();
        ctx.moveTo(x1, y1);
        ctx.lineTo(x2, y2);
        ctx.strokeStyle = color;
        ctx.stroke();
    }
  }

  render() {
    return (
      <div id="canvas-div">
        <canvas ref={this.canvasReference} width={this.props.width} height={this.props.height} />
        <div className="center-text">Current Grid Size: {this.props.size} </div>
        <Button color="primary" onClick={this.drawLines} value="Draw" />
        <Button color="secondary" onClick={this.redraw} value="Clear" />
      </div>
    );
  }
}

export default Grid;
