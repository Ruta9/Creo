import React from 'react';
import {Link} from 'react-router-dom';

class RegisterForm extends React.Component {

    onFormSubmit = (e) => {
        e.preventDefault();
        console.log("registered!");
    };

    render() {

        return (
            <div className="register-form">

            <div className="custom-small-block bold">
                Register to Creo:
            </div>

            <form className="ui form" onSubmit={(e) => this.onFormSubmit(e)}>
                <div className="field">
                    <input type="text" name="firstName" placeholder="First name"/>
                </div>
                <div className="field">
                    <input type="text" name="lastName" placeholder="Last name"/>
                </div>
                <div className="field">
                    <input type="text" name="email" placeholder="Email"/>
                </div>
                <div className="field">
                    <input type="password" name="password" placeholder="Password"/>
                </div>
                <div className="field">
                    <input type="password" name="repeatPassword" placeholder="Repeat your password"/>
                </div>
                <button className="custom-active-green ui fluid button" type="submit">Register</button>
            </form>

            <div className="ui divider"></div>

            <Link to="/login">
                Already have an account?
            </Link>

        </div>
        );
    }
}

export default RegisterForm;