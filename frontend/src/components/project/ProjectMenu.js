import React, {useEffect, useState} from 'react';
import {Link, useRouteMatch, useParams } from 'react-router-dom';
import Axios from 'axios';

import '../../css/projectmenu.css';

const ProjectMenu = (props) => {

    const {id} = useParams();
    let {path, url} = useRouteMatch();
    const [name, setName] = useState('');
    const [activeItem, setActiveItem] = useState('kanban');

    
    useEffect (() => {
        (async () => {
            const response = await Axios.get(`/api/projects/name/${id}`);
            setName(response.data);
        })();
    }, []);

    return (
        <div className="project-menu">
            <div className="ui huge header project-name">
                {name}
            </div>
            <div className="ui secondary pointing menu">
                {props.isAdmin ? <Link 
                className={`${activeItem === 'settings' ? 'active' : ''} item`} 
                to={`${url}/settings`} 
                onClick={() => setActiveItem('settings')}>
                    Settings
                </Link> : null}
                <Link 
                className={`${activeItem === 'team' ? 'active' : ''} item`} 
                to={`${url}/team`} 
                onClick={() => setActiveItem('team')}>
                    Team
                </Link>
                <Link 
                className={`${activeItem === 'kanban' ? 'active' : ''} item`} 
                to={`${url}/kanban`} 
                onClick={() => setActiveItem('tkanban')}>
                    Kanban
                </Link>
                <Link 
                className={`${activeItem === 'tickets' ? 'active' : ''} item`} 
                to={`${url}/tickets`} 
                onClick={() => setActiveItem('tickets')}>
                    Tickets
                </Link>
            </div>
        </div>
    );
} 

export default ProjectMenu;