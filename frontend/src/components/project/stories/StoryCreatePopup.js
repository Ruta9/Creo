import React, {useState, useEffect} from 'react';
import {useParams} from 'react-router-dom';
import {connect} from 'react-redux';
import Axios from 'axios';

import {FormInput, Form} from '../../common/Form';
import Modal from '../../common/Modal';
import LongDropDown from '../../common/LongDropdown';
import Dropdown from '../../common/Dropdown';

const StoryCreatePopup = ({currentUser, onClose}) => {

    const {id} = useParams();

    const [statuses, setStatuses] = useState([]);
    const [team, setTeam] = useState([]);

    const [assignee, setAssignee] = useState(null);
    const [status, setStatus] = useState(null);

    const [createError, setCreateError] = useState(null);

    useEffect(() => {
        getTeam();
        getStatuses();
    }, []);

    const getStatuses = async () => {
        const results = await Axios.get(`/api/statuses/project/story/all/${id}`);
        if (results.status === 200){
            results.data.forEach(status => {
                status.label = status.name;
                status.value = status.id;
            });

            setStatuses(results.data);
            setStatus(results.data[0]);
        }
        return results.data;
    }

    const getTeam = async () => {
        const results = await Axios.get(`/api/team/project/${id}`);
        if (results.status === 200){
            results.data.forEach(teamMember => {
                teamMember.label = teamMember.firstname + ' ' + teamMember.lastname;
                teamMember.value = teamMember.id;
            });
            setTeam(results.data);
            setAssignee(results.data.find(t => t.id === currentUser.id));
        }
    }

    const validateCreateForm = (form) => {
        let errors = {};
        if (form.name === '' || form.name.trim === '') errors.name = "Project name cannot be blank.";
        return errors;
    }

    const createProject = async (form) => {
        console.log("yee");
        //remove last creation's error
        setCreateError(null);

        let projectForm = {};
        projectForm.name = form.name;
        projectForm.description = form.description;
        projectForm.assigneeId = assignee.id;
        projectForm.statusId = status.id;

        const results = await Axios.post(`/api/stories/project/${id}`, projectForm).catch(
            error => {
                setCreateError("There was an error creating this story");
                if (error.response){
                    setCreateError(error.response.data.error);
                }
            }
        );
        if (results && results.status === 201){
            onClose(true);
        }

    }

    return (
        <div className="create-story">
            <div class="ui active inverted page dimmer"></div>
            <div className="ui centered grid">
                <div className="row">
                    <div className="eight wide column" style={{minWidth: "300px"}}>
                        <Modal style={{minWidth: "300px"}} header={<React.Fragment><i className="black book icon"/>Create new story</React.Fragment>} size="small">
                            <div className="ui container">
                                {createError !== null ? (
                                    <div className="ui tiny error message">{createError}</div>
                                ) : null}
                                <Form className="ui form" onSubmit={createProject} validate={validateCreateForm}>
                                        <FormInput type="text" name="name" placeholder="Name"/>
                                        <FormInput textarea="true" type="text" rows="4" name="description" placeholder="Description"/>
                                        <label>Assignee:</label>
                                        <br/>
                                        <LongDropDown options={team} selected={assignee} onSelection={(e) => setAssignee(e)}/>
                                        <br/><br/>
                                        <label>Status:</label>
                                        <br/>
                                        <Dropdown options={statuses} selected={status} onSelection={(e) => setStatus(e)}/>
                                        <br/><br/>
                                        <button type="button" className="cancel ui button left floated" onClick={() => onClose(false)}>Cancel</button>  
                                        <button className="ok custom-active-green ui button" type="submit">Create</button> 
                                </Form>
                            </div>
                        </Modal>
                    </div>
                </div>
            </div>
        </div>
    );
}

const mapStateToProps = (state) => {
    return {
        currentUser: state.identity 
    }
}

export default connect(mapStateToProps, {})(StoryCreatePopup);