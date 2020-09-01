import React from 'react';
import ProjectBlock from './ProjectBlock';
import CreateProject from './CreateProject';
import Dropdown from '../common/Dropdown';
import '../../css/projectlist.css';
import {randomColor, randomProjectPicture} from '../../utils/utils.js';

class ProjectsList extends React.Component {

    orderOptions = [
        {value: 'name', label: 'Name'},
        {value: 'newNotifications', label: 'Notifications'},
        {value: 'activeStories', label: 'Stories'},
        {value: 'activeTasks', label: 'Tasks'},
        {value: 'teamSize', label: 'Team'}
    ];

    onOrderSelect = (selection) => {
        this.setState({
            selectedOption: selection
        });
    }

    state = {
        projects: [
            {   id:1,
                name: "Personal",
                teamSize: 7,
                newNotifications: 5,
                activeStories: 20,
                activeTasks: 55,
                color: randomColor(),
                picture: randomProjectPicture() },
            {   id:2,
                name: "Traffi",
                teamSize: 5,
                newNotifications: 0,
                activeStories: 2,
                activeTasks: 8,
                color: randomColor(),
                picture: randomProjectPicture() },
            {   id:3,
                name: "Studies project",
                teamSize: 2,
                newNotifications: 1,
                activeStories: 5,
                activeTasks: 12,
                color: randomColor(),
                picture: randomProjectPicture() },
            {   id:4,
                name: "Home",
                teamSize: 1,
                newNotifications: 15,
                activeStories: 1,
                activeTasks: 3 ,
                activeTasks: 12,
                color: randomColor(),
                picture: randomProjectPicture() },
            {   id:5,
                name: "Summer",
                teamSize: 12,
                newNotifications: 0,
                activeStories: 6,
                activeTasks: 18,
                color: randomColor(),
                picture: randomProjectPicture() }
        ],
        searchKeyword: '',
        selectedOption: this.orderOptions[0]
    };

    onSearchFilterChange(e) {
       this.setState({
           searchKeyword: e.target.value
         });
    };

    sort = (a, b) =>  {
        if (typeof a[this.state.selectedOption.value] == 'string') {
            return a[this.state.selectedOption.value].localeCompare(b[this.state.selectedOption.value]);
        }
        if (typeof a[this.state.selectedOption.value] == 'number') {
            return b[this.state.selectedOption.value] - a[this.state.selectedOption.value];
        }
    };

    render() {

        const projects = this.state.projects
        .filter(
            project => this.state.searchKeyword === '' || 
            project.name.toLowerCase().includes(this.state.searchKeyword.toLowerCase()))
        .sort (this.sort)
        .map(project => {
            return (
                <div className="column" key={project.id}>
                    <ProjectBlock project={project}/>
                </div>
            );
        });

        return (
            <div className="projects-list">
                {/* <CreateProject/> */}
                <div className="ui container">
                    <div className="ui stackable grid">
                        <div className="three column row">

                            <div className="right aligned column">
                                <div className="small ui icon input">
                                    <input type="text" placeholder="Search..." onChange={(e) => this.onSearchFilterChange(e)} />
                                    <i className="search grey icon"></i>
                                </div>
                            </div>

                            <div className="column">
                                Order by: 
                                <Dropdown
                                    options={this.orderOptions}
                                    selected={this.state.selectedOption}
                                    onSelection={this.onOrderSelect}/>
                            </div>

                            <div className="middle aligned column">
                               <button className="ui custom-active-green button">
                                   Create new
                               </button>
                            </div>

                        </div>
                        <div className="row">
                            <div className="ui four column stackable grid container projects-list">
                                {projects}
                            </div>
                        </div>
                    </div>
                </div>
            </div>

        );
    }
}

export default ProjectsList;
