import React from 'react';
import {Link} from 'react-router-dom';
import {connect} from 'react-redux';
import Axios from 'axios';

import {getCurrentUser, checkIfAuthenticated} from '../actions';
import '../css/header.css';

const logo = require('../images/Creo_logo_100_300_white_crop.png');

class Header extends React.Component {

    componentDidMount(){
        this.props.getCurrentUser();
    }

    logout = async () => {
        const results = await Axios.post("/api/auth/logout");
        if (results.status === 200)
        this.props.checkIfAuthenticated();
    }

    render() {
        return (
        <div className="ui small menu creo-header">
            <div className="creo-logo item">
                <img className="ui tiny image" alt="Creo logo" src={logo}/>
            </div>
            <Link to="/projects" className="green-item item">
                My Projects
            </Link>
            <Link to="/tickets" className="green-item item">
                My Tickets
            </Link>

            <div className="right menu">
                <div className="small item">
                    {this.props.currentUser !== null ? this.props.currentUser.firstname + ' ' + this.props.currentUser.lastname : ''}
                    <img alt="avatar" className="ui avatar image" src="https://www.pngitem.com/pimgs/m/105-1055689_user-account-person-avatar-operating-system-grey-user.png"></img>
                    {/* <i className="user black large icon"></i> */}
                </div>
                <div className="link green-item item" onClick={this.logout}>
                    Logout
                </div>
            </div>
        </div>
        );
    };

}

const mapStateToProps = (state) => {
    return {
        currentUser: state.identity
    }
}
  
export default connect(mapStateToProps, {getCurrentUser, checkIfAuthenticated})(Header);