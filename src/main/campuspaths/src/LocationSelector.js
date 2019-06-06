import React, {Component} from 'react';
import Select from '@material-ui/core/Select';
import MenuItem from '@material-ui/core/MenuItem';

class LocationSelector extends Component {
    constructor(props) {
        super(props);
        this.state = {
            value: ""
        }

    }

    getBuildings = () => {
        return this.props.buildings.map(building => (
            <MenuItem> {building} </MenuItem>
        ));
    }

    onChange = (event) => {
        this.setState({
        value: event.target.value,
        name: event.target.name
        });
    }

    render() {
        return (
          <div>
          {this.getBuildings()}
          <Select
          children={this.props.children}
          value={this.props.value}
          onChange={this.onChange}
          >
            {this.getBuildings()}
            <MenuItem value="None">None</MenuItem>
            <MenuItem value="KNE">KNE</MenuItem>
            <MenuItem value="MGH">MGH</MenuItem>
          </Select>
          </div>
        )
      }
}

export default LocationSelector;