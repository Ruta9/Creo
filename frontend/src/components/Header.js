import React from 'react';
import {Link} from 'react-router-dom';
import {connect} from 'react-redux';

import {getCurrentUser} from '../actions';
import '../css/header.css';

const logo = require('../images/Creo_logo_100_300_white_crop.png');

class Header extends React.Component {

    componentDidMount(){
        this.props.getCurrentUser();
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
                <Link className="green-item item">
                    Logout
                </Link>
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
  
export default connect(mapStateToProps, {getCurrentUser})(Header);