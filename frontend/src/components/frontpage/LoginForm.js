import React, {useState} from 'react';
import Axios from 'axios';
import {Link, Redirect} from 'react-router-dom';
import OauthPopup from 'react-oauth-popup';

import {Form, FormInput} from '../common/Form';

const LoginForm = () => {

    const [redirect, setRedirect] = useState(null);
    const [errorMessage, setErrorMessage] = useState('');

    const onSuccessfullAuthorization = () => {
        setRedirect(true);
    }

    const onFormSubmit = async (form) => {
        const response = await Axios.post("/api/auth/login", form).catch(error => {
            setRedirect(false);
            if (error.response){
                setErrorMessage(error.response.data.message);
            }
            else {
                setErrorMessage("Internal server error");
            }
        });
        if (response && response.status === 200){
            onSuccessfullAuthorization();
        }
    };

    const loginError = () => {
        if (errorMessage !== ''){
            return (
                <div className="tiny ui message error">
                    {`Login failed: ${errorMessage}`}
                </div>
            );
        };

        return null;
    };

    return (
        <div className="login-form">

            
            {redirect ? <Redirect to='/projects'/> : null}

            <div className="custom-small-block bold">
                Log in to Creo:
            </div>

            {loginError()}

            <Form onSubmit={onFormSubmit} validate={() => {return {}}}>
                <FormInput type="text" name="email" placeholder="Enter your email"/>
                <FormInput type="password" name="password" placeholder="Enter your password"/>
                <button className="custom-active-green ui fluid button" type="submit">Log in</button>
            </Form>

            <div className="custom-small-block">
                OR
            </div>

            <OauthPopup
                url="/oauth2/authorization/google"
                onCode={onSuccessfullAuthorization}>
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