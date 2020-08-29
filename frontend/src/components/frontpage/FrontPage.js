import React from 'react';
import '../../css/frontpage.css'

const FrontPage = (props) => {

    return (
        <div className="front-page">
        <div className="ui centered grid">
            <div className="ui five column row">
                <div className="column center aligned login-form-block">
                    <img alt="Creo logo" src={require("../../images/Creo_logo_100_300.png")}/>
                    
                    {props.children}

                </div>
            </div>
        </div>
    </div>
    );
}

export default FrontPage;