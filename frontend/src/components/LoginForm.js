import React from 'react';
import Axios from 'axios';

const LoginForm = () => {

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
        <div>
            <div className="ui centered grid">
                <div className="ui five column row">
                    <div className="column">
                        <img alt="Creo logo" src={require("../images/Creo_logo_100_300.png")}/>
                        <div className="custom small block bold">
                            Log in to Creo:
                        </div>

                        <form className="ui form" onSubmit={(e) => onFormSubmit(e)}>
                            <div className="field">
                                <input type="text" name="email" placeholder="Enter your email"/>
                            </div>
                            <div className="field">
                                <input type="password" name="password" placeholder="Enter your password"/>
                            </div>
                            <button className="ui active fluid custom button" type="submit">Log in</button>
                        </form>

                        <div className="custom small block">
                            OR
                        </div>
                        <button className="ui google fluid plus button" onClick={() => window.location.href="oauth2/authorize/google"}>
                            <i className="google plus icon"></i>
                            Continue with Google
                        </button>

                        <div className="ui divider"></div>
                    </div>
                </div>

                <div className="ui nine column row">
                    <div className="column">
                        <a href="https://www.google.com">
                            Forgot your password?
                        </a>
                    </div>
                    <div className="column">
                        <a href="https://www.google.com">
                            Don't have an account?
                        </a>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default LoginForm;