import React from 'react';
import Modal from '../common/Modal';

class CreateProject extends React.Component {

    render() {

        const actions = (
            <div>
                <button className="cancel ui button left floated" type="submit">Cancel</button>  
                <button className="ok custom-active-green ui button" type="submit">Create</button> 
            </div>         
        );
        return (
            <div className="create-project">
                <Modal header="Create new project" size="mini" actions={actions}>
                    <div className="ui container">
                        <form className="ui form">
                            <div className="ui field">
                                <input type="text" name="name" placeholder="Name"/>
                            </div>
                            <div className="ui field">
                                <textarea type="text" rows="4" name="description" placeholder="Description"/>
                            </div>
                        </form>
                    </div>
                </Modal>
            </div>
        );
    }
}

export default CreateProject;