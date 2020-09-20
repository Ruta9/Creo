import React from 'react';
import ProjectBlock from './ProjectBlock';
import CreateProject from './CreateProject';
import Dropdown from '../common/Dropdown';
import '../../css/projectlist.css';
import {randomColor} from '../../utils/utils.js';
import Axios from 'axios';

class ProjectsList extends React.Component {

    orderOptions = [
        {value: 'name', label: 'Name'},
        {value: 'unreadNotifications', label: 'Notifications'},
        {value: 'activeStories', label: 'Stories'},
        {value: 'activeTasks', label: 'Tasks'},
        {value: 'teamSize', label: 'Team'}
    ];

    state = {
        projects: [],
        searchKeyword: '',
        selectedOption: this.orderOptions[0],
        showProjectCreateForm: false
    };

    componentDidMount = async () => {
        const response = await Axios.get('/api/projects/userProjects');
        if (response.status === 200){
        const data = response.data;
            data.forEach(d => {
                d.color = randomColor();
            });
            this.setState({
                projects: response.data
            });
        }
    };

    onOrderSelect = (selection) => {
        this.setState({
            selectedOption: selection
        });
    }

    onCreateFormClose = (created) => {
        if (created) {
            this.componentDidMount();
        }
        this.setState({
            showProjectCreateForm: false
        });
    }

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
                            {this.state.showProjectCreateForm ? <CreateProject onClose={this.onCreateFormClose}/> : null}
                                Order by: 
                                <Dropdown
                                    options={this.orderOptions}
                                    selected={this.state.selectedOption}
                                    onSelection={this.onOrderSelect}/>
                            </div>

                            <div className="middle aligned column">
                               <button className="ui custom-active-green button" onClick={() => this.setState({showProjectCreateForm: true})}>
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
