import React, {useState, useEffect} from 'react';
import {DragDropContext, Droppable, Draggable} from 'react-beautiful-dnd';
import {useParams} from 'react-router-dom';
import Axios from 'axios';

import '../../../css/statussettings.css';
import {Form, FormInput} from '../../common/Form';

const StatusesConfiguration = (props) => {
    
    const {id} = useParams();
    const [selected, setSelected] = useState(null);
    const [statuses, setStatuses] = useState([]);
    const [error, setError] = useState(null);
    const [showForm, setShowForm] = useState(false);

    useEffect(()=>{
        props.reloadStatuses(() => {
            getStatuses();
        }, props.ticketType)
        getStatuses();
    }, []);

    useEffect(()=> {
        if (statuses !== []) {
            props.onSaveClick(() => {
                reorder();
                return statuses;
            }, props.ticketType);
        }
    }, [statuses])

    const getStatuses = async () => {
        const results = await Axios.get(`/api/statuses/project/${props.ticketType}/all/${id}`);
        if (results.status === 200){
            setStatuses(results.data);
        }
    }

    const renderItems = () => {
        return statuses.map((status, index) => {
            return (
                <Draggable draggableId={status.name}
                index={index} key={status.name}> 
                {
                    provided => (
                        <div onClick={() => {setSelected(status)}} 
                        className={`item link ${status === selected ? 'selected' : ''}`}
                        ref={provided.innerRef}
                        {...provided.draggableProps}
                        {...provided.dragHandleProps}>
                            {status.name}
                        </div>
                    )
                }
                </Draggable>
            );
        });
    }

    const reorder = () => {
        statuses.forEach((s, i) => {
            s.statusOrder = i + 1;
        })
    }

    const removeItemClick = () => {
        setError(null);
        if (statuses.length > 2) {
            setStatuses(statuses.filter(status => status !== selected));
            setSelected(null);
        }
        else {
            setError("There can not be less than 2 statuses available!");
        }
    }

    const addItemClick = () => {
        setError(null);
        if (statuses.length < 8){
            setShowForm(true);
        }
        else {
            setError("There can not be more than 8 statuses available!");
        }
    }

    const statusDropped = (result) => {

        if (!result.destination) {
            return;
        }
    
        let statusesAfterDrop = statuses;
        const [removed] = statusesAfterDrop.splice(result.source.index, 1);
        statusesAfterDrop.splice(result.destination.index, 0, removed);
    
        statusesAfterDrop.forEach((status, index) => {
            status.statusOrder = (index + 1);
        });
        setStatuses(statusesAfterDrop);
    }

    const validate = (form) => {
        let errors = {};
        statuses.forEach(status => {
            if (status.name === form.name) {
                errors.name = "Status with the same label is already present"
                return errors;
            }
        })
        return errors;
    }

    const onFormSubmit = (form) => {
        const newStatuses = [...statuses, {
            id: null,
            name: form.name,
            statusOrder: (statuses.length + 1),
            ticketType: props.ticketType.toUpperCase()
        }]
        setStatuses(newStatuses);
        setShowForm(false);
    }

    const renderForm = () => {
        if (showForm){
            return (
                <Form size="small" onSubmit={onFormSubmit} validate={validate}>
                    <FormInput type="text" name="name" placeholder="Status label, e.g. 'TO DO'"/>
                    <button type="submit" className="small ui button custom-active-green">Add</button>
                </Form>
            );
        }
        return null;
    }

    return (
        <div className="status-config">

            <div className="ui large header">
                {props.label}:
            </div>

            <div className="ui grid">
                <div className="three wide column status-list">
                    <DragDropContext onDragEnd={statusDropped}>
                        <Droppable droppableId={props.label}> 
                        {
                            provided => (
                                <div 
                                className="ui large celled list"
                                ref={provided.innerRef} 
                                {...provided.droppableProps}>
                                    {renderItems()}
                                    {provided.placeholder}
                                </div>
                            )
                        }
                        </Droppable>
                    </DragDropContext>
                </div>
            </div>
            {renderForm()}
            <div className="ui icon tiny buttons plus-minus-buttons">
                <div className="ui button" onClick={removeItemClick}>
                    <i className="minus icon"/>
                </div>
                <div className="ui button" onClick={addItemClick}>
                    <i className="plus icon"/>
                </div>
            </div>
            {error !== null ? 
            (<div className="ui error message visible active">
                {error}
            </div>) : null}
        </div>
    );
}

const StatusSettings = () => {

    const {id} = useParams();

    const ticketTypes = ['story', 'task'];

    let onSubmitCallbacks = {};
    let statusesReloadFunctions = {};

    const registerOnClickEvent = (callback, ticketType) => {
        onSubmitCallbacks[ticketType] = callback;
    }

    const registerReload = (reloadFunction, ticketType) => {
        statusesReloadFunctions[ticketType] = reloadFunction;
    }

    const onSaveClick = () => {
        const statuses = [];
        statuses.push(...onSubmitCallbacks[ticketTypes[0]]());
        statuses.push(...onSubmitCallbacks[ticketTypes[1]]());
        Axios.put(`/api/statuses/project/${id}`, statuses).then(() => {
            statusesReloadFunctions[ticketTypes[0]]()
            statusesReloadFunctions[ticketTypes[1]]()
        }
        );
    }

    return (

        <React.Fragment>
            <div className="ui olive message">
                <div className="ui centered grid">
                    <div className="ui row">
                        <div className="one wide column center aligned">
                            <i className="ui icon exclamation mark large"/>
                        </div>
                        <div className="fifteen wide column">
                            Drag and drop to change the order. The order of statuses you provide will be the order they will be presented in the kanban. The last status in the order should indicate that the ticket is archived - it will not be visible in the kanban or added to the tickets number on the projects list page. Maximum number of statuses is 8, minimum - 2.
                        </div>
                    </div>
                </div>
            </div>
            <div className="ui centered stackable grid create-status">
                <div className="two column row">
                    <div className="column">
                    <StatusesConfiguration
                    label="Story statuses"
                    ticketType={ticketTypes[0]}
                    onSaveClick={registerOnClickEvent}
                    reloadStatuses={registerReload}
                    />
                    </div>
                    <div className="column">
                    <StatusesConfiguration
                    label="Task statuses"
                    ticketType={ticketTypes[1]}
                    onSaveClick={registerOnClickEvent}
                    reloadStatuses={registerReload}
                    />
                    </div>
                </div>
            </div>
            <button className="right floated ui button custom-active-green" onClick={onSaveClick}>Save</button>
        </React.Fragment>
    );
}

export default StatusSettings;