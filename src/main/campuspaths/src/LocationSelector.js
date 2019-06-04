import React, {Component} from 'react';
import Select from '@material-ui/core/Select';
import MenuItem from '@material-ui/core/MenuItem';

class LocationSelector extends Component {
    render() {
        return (
          <div>
          <Select
          autoWidth="true"
          children={this.props.children}
          onChange={this.props.onChange}
          >
            <MenuItem value="">None</MenuItem>
            <MenuItem value="KNE">KNE</MenuItem>
            <MenuItem value="MGH">MGH</MenuItem>
          </Select>
          </div>
        )
      }
}

export default LocationSelector;