import React, { useEffect, useState } from 'react';
import {useParams} from 'react-router-dom';
import Axios from 'axios';

import '../../../css/rolessettings.css';

const RoleGrant = ({data, onSubmitCallback}) => {

    const {id} = useParams();
    const [roleData, setRoleData] = useState(data);
    const [selectAll, setSelectAll] = useState(false);

    useEffect(() => {
        onSubmitCallback(roleData.role, updateRoleData);
    }, [roleData]);

    const updateRoleData = () => {
        Axios.put(`/api/roles/project/${id}/team/${roleData.role}`, roleData);
    }

    const setAllRoleDataHasRoleProp = (value) => {
        const data = {...roleData};
        data.team.forEach(element => {
            element.hasRole = value;
        });
        setRoleData(data);
    }

    const setIsGlobal = (value) => {
        const data = {...roleData};
        data.isGlobal = value;
        setRoleData(data);
    }

    const setSpecificUserHasRoleProp = (id, value) => {
        const data = {...roleData};
        data.team.forEach(element => {
            if (element.teamMemberDTO.id === id) element.hasRole = value;
        });
        setRoleData(data);
    }

    const renderTeamList = () => {

        const items = roleData.team.map(({teamMemberDTO, hasRole}) => {
            return (
            <div className="item user-select" key={teamMemberDTO.id}>
                <img alt="avatar" className="ui avatar image" src="https://www.pngitem.com/pimgs/m/105-1055689_user-account-person-avatar-operating-system-grey-user.png"/>
                <div className="content">
                    <div className="header">{teamMemberDTO.firstname + ' ' + teamMemberDTO.lastname}</div>
                </div>
                <div className="right floated large content">
                    <input type="checkbox" name="hasRole" checked={hasRole} onChange={() => {
                        setSpecificUserHasRoleProp(teamMemberDTO.id, !hasRole);
                        setSelectAll(false);
                    }}/>
                </div>
            </div>);
        });

        return (
            <div className="ui large celled middle aligned list team-list">
                <div className="item select-all">
                <div className="right floated middle aligned content">
                    All
                    <input type="checkbox" name="all" checked={selectAll} onChange={() => {
                        setAllRoleDataHasRoleProp(!selectAll);
                        setSelectAll(!selectAll);
                    }}/>
                </div>
                </div>
                {items}
            </div>
        );
    }

    return (
        <div className="role-grant">
            <div className="ui form">
                <div className="ui header">
                    Role: {roleData.role}
                </div>
                {roleData.roleDescription}
                <div className="inline fields is-global-selection">
                    <div className="field" 
                    data-tooltip="All current and added in the future team members will be granted the role" 
                    data-position="top left">
                        <div className="ui radio checkbox">
                            <input type="radio" name={`isGlobal_${roleData.role}`} checked={roleData.isGlobal} onChange={() => setIsGlobal(true)}/>
                            <label>All</label>
                        </div>
                    </div>
                    <div className="field">
                        <div className="ui radio checkbox">
                            <input type="radio" name={`isGlobal_${roleData.role}`} checked={!roleData.isGlobal} onChange={() => setIsGlobal(false)}/>
                            <label>Selected</label>
                        </div>
                    </div>
                </div>
                {(roleData.isGlobal != null && !roleData.isGlobal) ? renderTeamList() : null}
            </div>
        </div>
    );
}

const RolesSettings = () => {

    const {id} = useParams();
    const [roles, setRoles] = useState(null);

    const submitCallbacks = {};

    useEffect( ()=> {
        (async () => {
            const results = await Axios.get(`/api/roles/project/${id}/team`);
            if (results.status === 200){
                setRoles(results.data);
            }
        })();
    }, []);

    const registerCallback = (role, callback) => {
        submitCallbacks[role] = callback;
    }

    const renderRoleGrantComponents = () => {
        if (roles === null) return null;

        return roles.map(r => {
            if (r.role !== "PROJECTADMIN") {
                return (
                    <React.Fragment key={r.id}>
                        <RoleGrant 
                        data={r}
                        onSubmitCallback={registerCallback}/>
                        <div className="ui section divider"/>
                    </React.Fragment>
                );
            }
        });
    }

    return (
        <div>
            {renderRoleGrantComponents()}
            <button className="right floated ui button custom-active-green" onClick={() => {
                roles.forEach(r => {
                    if (r.role !== "PROJECTADMIN") submitCallbacks[r.role]();
                })
            }}>Save</button>
        </div>
    );
}
export default RolesSettings;