import React from 'react';
import {Form, FormInput} from '../../common/Form';

class GeneralSettings extends React.Component {

    render() {

        return (
            <Form validate={(form)=>{return {}}}>
                <label>Name:</label>
                <FormInput type="text" name="name" value="name"/>
                <label>Description:</label>
                <FormInput rows="2" type="text" textarea="true" name="description" value="name"/>
                <label>Owner:</label>
                <FormInput type="text" name="owner" data-tooltip="Note: changing the owner will revoke your admin rights but will leave you as a project's team member" data-position="bottom left" value="Ruta Jankauskaite"/>
                <label>Image:</label><br/>
                <button className="ui small basic black button">
                    <i className="paperclip large icon"/>
                    Upload image
                </button>
                <FormInput className="file-input" type="file" id="img" name="img" accept="image/*"/>
                <label>Preview:</label>
                <img class="ui small image" alt="project" src="https://semantic-ui.com/images/wireframe/image.png"/>
                <button type="submit" className="right floated ui button custom-active-green">Save</button>
            </Form>
        );
    }
}

export default GeneralSettings;