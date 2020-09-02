import React from 'react';
import {Link} from 'react-router-dom';

import '../css/header.css';
import Axios from 'axios';

const logo = require('../images/Creo_logo_100_300_white_crop.png');

class Header extends React.Component {

    state= {
        currentUser: ''
    }

    componentDidMount(){
        Axios.get('/api/users/identity').then((response) => {
            console.log(response);
            this.setState({
                currentUser: response.data
            });
        });
    }

    render() {
        return (
        <div className="ui small menu creo-header">
            <div className="creo-logo item">
                <img className="ui tiny image" alt="Creo logo" src={logo}/>
            </div>
            <Link to="/projects" className="active green-item item">
                My Projects
            </Link>
            <Link to="/tickets" className="green-item item">
                My Tickets
            </Link>

            <div className="right menu">
                <div className="small item">
                    {this.state.currentUser}
                    <i className="user black large icon"></i>
                </div>
                <Link className="green-item item">
                    Logout
                </Link>
            </div>
        </div>
        );
    };

}

export default Header;