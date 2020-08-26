import React from 'react';

const Header = () => {
    return (
        <div className="ui secondary menu creo header">
            <div className="item">
                <a href="https://www.google.com">
                    <img className="ui small image" alt="Creo logo" src={require('../images/Creo_logo_100_300.png')}/>
                </a>
            </div>
            <div className="right menu">
                <button className="ui item large button active custom">
                    Log in
                </button>
                <button className="ui item large button">
                    Register
                </button>
            </div>
        </div>
    );
};

export default Header;