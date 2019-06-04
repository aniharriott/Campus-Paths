import TextField from '@material-ui/core/TextField';
import React, {Component} from 'react';

class InputBox extends Component {
  render() {
    return (
      <div className="center-text">
        <TextField
          id={this.props.id}
          label={this.props.label}
          value={this.props.value}
          onChange={this.props.onChange}
          className={this.props.className}
          margin="normal"
        />
      </div>
    );
  }
}

export default InputBox;