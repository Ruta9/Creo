import React from 'react';
import Modal from '../common/Modal';
import {Form, FormInput} from '../common/Form';
import Axios from 'axios';

class CreateProject extends React.Component {

    state = {
        showErrorMessage: false,
        errorMessage: ''
    }

    validate = (form) => {
        const errors = {};
        if (form.name === '' || form.name.trim === '') errors.name="Name can not be blank";
        return errors;
    }

    createProject = async (form) => {
        const response = await Axios.post('/api/projects', form).catch(
            error => {
                this.setState({showErrorMessage: true});
                if (error.response){
                    this.setState({errorMessage: error.response.data.error});
                }
            }
        );
        if (response && response.status === 201) {
            this.props.onClose(true);
        }
    }

    render() {

        return (
            <div className="create-project">
                <Modal header="Create new project" size="mini">
                    <div className="ui container">
                        {this.state.showErrorMessage ? (
                            <div className="ui tiny error message">{this.state.errorMessage}</div>
                        ) : null}
                        <Form className="ui form" onSubmit={this.createProject} validate={this.validate}>
                                <FormInput type="text" name="name" placeholder="Name"/>
                                <FormInput textarea="true" type="text" rows="4" name="description" placeholder="Description"/>
                                <button className="cancel ui button left floated" onClick={() => this.props.onClose(false)}>Cancel</button>  
                                <button className="ok custom-active-green ui button" type="submit">Create</button> 
                        </Form>
                    </div>
                </Modal>
            </div>
        );
    }
}

export default CreateProject;