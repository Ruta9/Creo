import React, {useEffect, useState} from 'react';
import {Route, Switch, useRouteMatch, Redirect, useParams} from 'react-router-dom';
import Axios from 'axios';

import ProjectMenu from './ProjectMenu';
import Settings from './settings/Settings';
import Tickets from './tickets/Tickets';

const ProjectPage = () => {

    const [isAdmin, setIsAdmin] = useState(null);
    const {id} = useParams();

    useEffect(() => {
        (async () => {
            const results = await Axios.get(`/api/security/projects/adminRole/${id}`);
            if (results.status === 200){
                setIsAdmin(results.data);
            }
            else setIsAdmin(false);
        })();

    }, []);

    let {path, url} = useRouteMatch();

    return (
        <React.Fragment>
            <ProjectMenu isAdmin={isAdmin}/>
            <Switch>
                <Route exact path={`${path}/settings`}>
                    {isAdmin ? <Settings/> : <Redirect to={`${url}/kanban`} />}
                </Route>
                <Route exact path={`${path}/team`}>
                    <div>Team</div>
                </Route>
                <Route exact path={`${path}/kanban`}>
                    <div>Kanban</div>
                </Route>
                <Route exact path={`${path}/tickets`}>
                    <Tickets/>
                </Route>
                <Route>
                    <Redirect to={`${url}/kanban`} />
                </Route>
            </Switch>
        </React.Fragment>
    );
}

export default ProjectPage;