import React from 'react';
import {Link} from 'react-router-dom';
import {Form, FormInput } from '../common/Form';
import Axios from 'axios';

import Modal from '../common/Modal';

class RegisterForm extends React.Component {

    state = {
        registrationSuccessful: null,
        errorMessage: ''
    }

    onFormSubmit = async (form) => {
        const response = await Axios.post('/api/users', form).catch (
            error => {
                this.setState({registrationSuccessful: false});
                if (error.response){
                    this.setState({errorMessage: error.response.data.error});
                }
            }
        );
        if (response && response.status === 201) this.setState({registrationSuccessful: true});
    };

    successModal = () => {
        return (
            <Modal size="mini"
            actions={
                <Link to="./login">
                    <button className="ok custom-active-green ui button" type="submit">
                        Ok!
                    </button>
                </Link> 
            }>
                Your account was created successfully!
            </Modal>
        );
    };

    registrationError = () => {
        if (this.state.registrationSuccessful !== null && !this.state.registrationSuccessful){
            return (
                <div className="tiny ui message error">
                    There were some issues with your registration
                    {this.state.errorMessage !== '' ? <ul>
                        <li>{this.state.errorMessage}</li>
                    </ul> : null}
                </div>
            );
        };

        return null;
    };

    validate = (form) => {
        const errors = {};
        if (form.firstname === '' || form.firstname.trim === '') errors.firstname = "First name can not be empty";
        if (form.lastname === '' || form.lastname.trim === '') errors.lastname = "Last name can not be empty";
        if (form.email === '' || !(/^[A-Za-z0-9+_.-]+@(.+)$/.test(form.email))) errors.email = "Invalid email";
        if (form.password === '' || form.password.length < 8) errors.password = "Password should be at least 8 characters long";
        if (form.password !== form.repeatPassword) errors.repeatPassword = "Passwords must match";
        return errors;
    };

    render() {

        if (this.state.registrationSuccessful) return (<div>{this.successModal()}</div>);
        else return (
            <div className="register-form">

                {this.registrationError()}

                <div className="custom-small-block bold">
                    Register to Creo:
                </div>

                <Form className="ui form" validate={this.validate} onSubmit={this.onFormSubmit}>
                    <FormInput type="text" name="firstname" placeholder="First name"/>
                    <FormInput type="text" name="lastname" placeholder="Last name"/>
                    <FormInput type="text" name="email" placeholder="Email"/>
                    <FormInput type="password" name="password" placeholder="Password"/>
                    <FormInput type="password" name="repeatPassword" placeholder="Repeat your password"/>
                    <button className="custom-active-green ui fluid button" type="submit">Register</button>
                </Form>

                <div className="ui divider"></div>

                <Link to="/login">
                    Already have an account?
                </Link>

            </div>
        );
    }
};

export default RegisterForm;