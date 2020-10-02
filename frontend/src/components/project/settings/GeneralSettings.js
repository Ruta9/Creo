import React from 'react';
import {withRouter } from 'react-router-dom';
import Axios from 'axios';

import {Form, FormInput} from '../../common/Form';
import LongDropdown from '../../common/LongDropdown';
import Loader from '../../common/Loader';
import '../../../css/generalsettings.css';

class GeneralSettings extends React.Component {

    constructor(props) {
        super(props);
        this.fileInputRef = React.createRef();
        this.project_id = this.props.match.params.id;
    }

    state = {
        file: null,
        imagePreview: null,
        project: null,
        owner: null,
        team: []
    }

    componentDidMount = async () => {
        const team = await this.getTeamMembers();
        const project =  await this.getProjectInfo();
        this.setState({
            owner:  team.find(t => t.id === project.owner.id)
        });
    }

    getTeamMembers = async () => {
        const teamResults = await Axios.get(`/api/team/project/${this.project_id}`);
        if (teamResults.status === 200){
            teamResults.data.forEach(teamMember => {
                    teamMember.label = teamMember.firstname + ' ' + teamMember.lastname;
                    teamMember.value = teamMember.id;
                }
            );
            this.setState({
                team: teamResults.data
            });
            return teamResults.data;
        }
        return null;
    }

    getProjectInfo = async () => {
        const projectInfo = await Axios.get(`/api/projects/general/${this.project_id}`);
        if (projectInfo.status === 200){
            this.setState({
                project: projectInfo.data,
                imagePreview: window.location.origin + `/uploads/project/image/${this.project_id}`
            });
            return projectInfo.data;
        }
        return null;
    }

    newFileSelected = (e) => {
        const file = e.target.files[0];
        if (file !== undefined) {
            var reader = new FileReader();

            reader.onloadend = () => {
                this.setState({
                    imagePreview: reader.result,
                    file: file
                });
              }
            
            reader.readAsDataURL(file);
        }
    }

    onFormSubmit = (form) => {
        let project = {...this.state.project};
        project.name = form.name;
        project.description = form.description;

        if (this.state.imagePreview === null) project.imageUrl = null;
        let owner = {...this.state.owner};
        delete owner.label;
        delete owner.value;
        project.owner = owner;

        const projectForm = new FormData();
        projectForm.append('file', this.state.file);
        projectForm.append('form', new Blob ([JSON.stringify(project)], {type: "application/json"}));
        Axios.put('/api/projects', projectForm, {
            headers: {
                "Content-Type": undefined
            }
        });
    }

    render() {

        if (this.state.project === null || this.state.team === [] || this.state.owner === null) return (<Loader/>);

        return (
            <div className="general-settings">
                <Form validate={(form)=>{return {}}} onSubmit={this.onFormSubmit}>
                    <label>Name:</label>
                    <FormInput type="text" name="name" value={this.state.project !== null ? this.state.project.name : ''}/>
                    <label>Description:</label>
                    <FormInput rows="2" type="text" textarea="true" name="description" value={this.state.project !== null ? this.state.project.description : ''}/>
                    <label>Owner:</label><br/>
                    <LongDropdown options={this.state.team} selected={this.state.owner} onSelection={(option) => this.setState({owner: option})}/>
                    <br/><br/>
                    <label>Image:</label><br/>
                    <button className="ui small basic black button" type="button"
                            onClick={() => this.fileInputRef.current.click()}>
                        <i className="paperclip large icon"/>
                        Upload image
                    </button>
                    <FormInput ref={this.fileInputRef} className="file-input" type="file" id="img" name="img" accept="image/*" changeCallback={this.newFileSelected}/>
                    {this.state.imagePreview !== null ? 
                    <React.Fragment>
                        <label>Preview:</label>
                        <img className="ui small image" alt="project" src={this.state.imagePreview}/>
                        <button className="ui small basic button" type="button"
                            onClick={() => this.setState({
                                imagePreview: null,
                                file: null
                            })}>
                                <i className="x icon"/>
                                Remove image
                        </button>
                        <span 
                        data-tooltip="If you save without an image, one of the default images will be generated for you!" 
                        data-position="top left">
                            <i className="question circle outline icon"/>
                        </span>
                    </React.Fragment> : null}
                    <button type="submit" className="right floated ui button custom-active-green">Save</button>
                </Form>
            </div>
        );
    }
}

export default withRouter(GeneralSettings);