import React from 'react';

const TicketsTableRow = ({story}) => {

    return (
        <tr> 
        {/* {story.newNotifications !== 0 ? <div class="ui tiny red floating label">
                {story.newNotifications}
            </div> : null } */}
            <td className="center aligned"><i className="ui icon caret down"/></td>
            <td className="link">{story.name}</td>
            <td>{story.shortenedDescription}</td>
            <td>{story.status}</td>
            <td>{story.assignee}</td>
            <td>{story.lastUpdatedDate}</td>
            <td>{story.createdDate}</td>
        </tr>
    );
}

export default TicketsTableRow;