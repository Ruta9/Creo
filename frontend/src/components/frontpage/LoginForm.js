import React, {useState} from 'react';
import Axios from 'axios';
import {Link} from 'react-router-dom';
import OauthPopup from 'react-oauth-popup';

const LoginForm = () => {

    const [redirect, setRedirect] = useState(null);

    const onSuccessfullGoogleAuthorization = (code, params) => {
        console.log(code);
        console.log(params);
    }

    const onPopupClose = () => {
        console.log("popup closed");
    }

    const onFormSubmit = (e) => {
        e.preventDefault();
        Axios.post("/api/auth/login", {
            email: 'rutule9@gmail.com',
            password: "admin"
        }).then (
            (response) => {console.log(response)}
        );
    };

    return (

        <div className="login-form">

            <div className="custom-small-block bold">
                Log in to Creo:
            </div>

            <form className="ui form" onSubmit={(e) => onFormSubmit(e)}>
                <div className="field">
                    <input type="text" name="email" placeholder="Enter your email"/>
                </div>
                <div className="field">
                    <input type="password" name="password" placeholder="Enter your password"/>
                </div>
                <button className="custom-active-green ui fluid button" type="submit">Log in</button>
            </form>

            <div className="custom-small-block">
                OR
            </div>

            <OauthPopup
                url="/oauth2/authorization/google"
                onCode={onSuccessfullGoogleAuthorization}
                onClose={onPopupClose}>
                <button className="ui google fluid plus button">
                    <i className="google plus icon"></i>
                    Continue with Google
                </button>
            </OauthPopup>

            <div className="ui divider"></div>

            <Link to="/forgotPassword">
                Forgot your password?
            </Link>
            <br/>
            <Link to="/register">
                Don't have an account?
            </Link>

        </div>

    );
}

export default LoginForm;