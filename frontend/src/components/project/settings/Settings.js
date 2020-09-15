import React from 'react';

import SideMenu from './SideMenu';
import GeneralSettings from './GeneralSettings';
import StatusSettings from './StatusSettings';
import RolesSettings from './RolesSettings';
import '../../../css/settings.css';

class Settings extends React.Component {

    state = {
        currentMenu: 'general'
    }

    menuSelectionChanged = (selected) => {
        this.setState({
            currentMenu: selected
        });
    };

    renderSpecificSettings = () => {
        switch (this.state.currentMenu){
            case 'general':
                return <GeneralSettings/>
            case 'statuses':
                return <StatusSettings/>
            case 'rights':
                return <RolesSettings/>
            default:
                return null
        }
    }

    render(){
        return (
            <div className="ui grid settings-page">
                <div className="ui row">
                    <div className="three wide column">
                        <SideMenu current={this.state.currentMenu} onChange={this.menuSelectionChanged}/>
                    </div>
                    <div className="twelve wide column">
                        <div className="ui segment general-settings">
                            {this.renderSpecificSettings()}
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

export default Settings;