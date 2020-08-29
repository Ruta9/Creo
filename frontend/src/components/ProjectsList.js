import React from 'react';
import ProjectBlock from './ProjectBlock';

class ProjectsList extends React.Component {

    state = {
        projects: [
            {   id:1,
                name: "Personal",
                teamSize: 7,
                activeStories: 20,
                activeTasks: 55 },
            {   id:2,
                name: "Traffi",
                teamSize: 5,
                activeStories: 2,
                activeTasks: 8 },
            {   id:3,
                name: "Studies project",
                teamSize: 2,
                activeStories: 5,
                activeTasks: 12 },
            {   id:4,
                name: "Home",
                teamSize: 1,
                activeStories: 1,
                activeTasks: 3 ,
                activeTasks: 12 },
            {   id:5,
                name: "Summer",
                teamSize: 12,
                activeStories: 6,
                activeTasks: 18 }
        ]
    };

    render() {

        const projectBlocks = this.state.projects.map(project => {
            return (
                <div className="column" key={project.id}>
                    <ProjectBlock project={project}/>
                </div>
            );
        });

        return (
            <div>
                <div className="ui container">
                    <div className="ui four column stackable grid container projects-list">
                        {projectBlocks}
                    </div>
                </div>
            </div>

        );
    }
}

export default ProjectsList;
