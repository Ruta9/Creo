import React from 'react';

const ProjectBlock = (props) => {

    return (
        <div className="ui raised card">
            <div className="content">
                <div className="center aligned header">
                    {props.project.name}
                </div>
            </div>
            <div className="centered content">
                <img className="ui small image" src={require('../images/projectIcons/apple.png')}/>
            </div>
            <div class="content">
                <div class="summary">
                    <div className="ui centered grid">
                        <div className="one column row">
                            <div className="center aligned column">
                                <i className="green user small large icon"></i>
                                {props.project.teamSize}
                            </div>
                        </div>
                        <div className="two column row">
                            <div className="left floated right aligned column">
                                <i className="green book small large icon"></i>
                                {props.project.activeStories}
                            </div>
                            <div className="right floated column">
                            <i className="green clipboard outline small large icon"></i>
                                {props.project.activeTasks}
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default ProjectBlock;