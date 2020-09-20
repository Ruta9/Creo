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
        imagePreview: "https://semantic-ui.com/images/wireframe/image.png",
        project: null,
        owner: null,
        team: []
    }

    componentDidMount = () => {
        this.getTeamMembers();
        this.getProjectInfo();
    }

    componentDidUpdate = () => {
        if (this.state.owner === null && this.state.team !== [] && this.state.project !== null){
            this.setState({
                owner:  this.state.team.find(t => t.id === this.state.project.owner.id)
            });
        }
    }

    getTeamMembers = async () => {
        const teamResults = await Axios.get(`/api/team/project/${this.project_id}`);
        if (teamResults.status === 200){
            teamResults.data.forEach(teamMember => {
                    teamMember.label = teamMember.firstname + ' ' + teamMember.lastname;
                    teamMember.value = teamMember.email;
                }
            );
            this.setState({
                team: teamResults.data
            });
        }
    }

    getProjectInfo = async () => {
        const projectInfo = await Axios.get(`/api/projects/general/${this.project_id}`);
        if (projectInfo.status === 200){
            this.setState({
                project: projectInfo.data,
                imagePreview: window.location.origin + "/" + projectInfo.data.imageUrl
            });
        }
    }

    newFileSelected = (e) => {
        const file = e.target.files[0];
        if (file !== undefined) {
            var reader = new FileReader();

            reader.onloadend = () => {
                this.setState({
                    imagePreview: reader.result
                });
              }
            
            reader.readAsDataURL(file);
        }
    }

    onFormSubmit = (form) => {
        console.log(form);
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
                    {/* <FormInput type="text" name="owner" data-tooltip="Note: changing the owner will revoke your admin rights but will leave you as a project's team member" data-position="bottom left" value:"Ruta Jankauskaite"/> */}
                    <br/><br/>
                    <label>Image:</label><br/>
                    <button className="ui small basic black button"
                            onClick={() => this.fileInputRef.current.click()}>
                        <i className="paperclip large icon"/>
                        Upload image
                    </button>
                    <FormInput ref={this.fileInputRef} className="file-input" type="file" id="img" name="img" accept="image/*" changeCallback={this.newFileSelected}/>
                    <label>Preview:</label>
                    <img className="ui small image" alt="project" src={this.state.imagePreview}/>
                    <button type="submit" className="right floated ui button custom-active-green">Save</button>
                </Form>
            </div>
        );
    }
}

export default withRouter(GeneralSettings);