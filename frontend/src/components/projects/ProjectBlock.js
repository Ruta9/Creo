import React from 'react';
import {Link} from 'react-router-dom';
import '../../css/projectblock.css';

const ProjectBlock = ({project}) => {

    return (
        <div className="project-block">
            <Link to={`/project/${project.id}/kanban`} className={`ui raised ${project.color} card`}>
                <div className="content">
                    <div className="right floated">
                        {project.unreadNotifications !== 0 ? 
                        <div className="notification-bell" data-tooltip="Notifications">
                            <i className="bell large icon"></i> {project.unreadNotifications} 
                        </div> : null}
                    </div>
                    <div className="header">
                        {project.name}
                    </div>
                </div>

                <div className="center aligned content">
                    <img alt="project" className="ui small image" src={project.imageUrl}/>
                </div>

                <div className="content">
                    <div className="summary">
                        <div className="ui centered grid">
                            <div className="three column row">
                                <div className="column" data-tooltip="Team size">
                                    <i className="black user large icon" ></i>
                                    {project.teamSize}
                                </div>
                                <div className="column" data-tooltip="Active stories">
                                    <i className="black book large icon"></i>
                                    {project.activeStories}
                                </div>
                                <div className="column" data-tooltip="Active tasks">
                                <i className="black clipboard outline large icon"></i>
                                    {project.activeTasks}
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </Link>
        </div>
    );
}

export default ProjectBlock;