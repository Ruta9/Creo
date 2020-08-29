import React from 'react';
const logo = require('../images/Creo_logo_100_300.png');

class Header extends React.Component {

    state= {
        currentUser: 'Ruta Jankauskaite'
    }

    componentDidMount(){
        
    }

    render() {
        return (
        <div className="ui large menu green creo-header">
            <div className="creo-logo">
                <img className="ui small image" alt="Creo logo" src={logo}/>
            </div>
            <a className="active item">
                My Projects
            </a>
            <a className="item">
                My Tickets
            </a>

            <div className="right menu">
                <div className="small item">
                    {this.state.currentUser}
                    <i className="user black large icon"></i>
                </div>
                <a className="item">
                    Logout
                </a>
            </div>
        </div>
        );
    };

}

export default Header;