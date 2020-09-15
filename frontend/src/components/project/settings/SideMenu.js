import React from 'react';

const SideMenu = (props) => {

    return (
        <div>
            <div className="ui secondary vertical fluid menu project-settings-side-menu">
                <div className={`${props.current === 'general' ? 'active' : ''} item link`} onClick={() => props.onChange('general')}>
                    General
                </div>
                <div className={`${props.current === 'statuses' ? 'active' : ''} item link`} onClick={() => props.onChange('statuses')}>
                    Ticket statuses
                </div>
                <div className={`${props.current === 'rights' ? 'active' : ''} item link`} onClick={() => props.onChange('rights')}>
                    Team rights
                </div>
            </div>
        </div>
    );
}

export default SideMenu;