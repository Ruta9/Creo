import React, {useState, useEffect} from 'react';

import TicketsTableRow from './TicketsTableRow';
import '../../../css/ticketstable.css';

const TicketsTable = ({tickets}) => {

    const [stories, setStories] = useState([]);

    useEffect(() => {
        setStories(tickets);
    });

    const renderTableRows = () => {
        return stories.map(story => {
            return <TicketsTableRow key={story.id} story={story}/>
        })
    }

    return (
        <div className="tickets-table ui centered grid">
            <div className="center aligned column">
                <table className="ui very basic table">
                    <thead>
                        <tr className="link">
                            <th></th>
                            <th>Name</th>
                            <th>Description</th>
                            <th>Status</th>
                            <th>Assignee</th>
                            <th>Last Updated</th>
                            <th>Created Date</th>
                        </tr>
                    </thead>
                    <tbody>
                        {renderTableRows()}
                    </tbody>
                </table>
            </div>
        </div>
    );
}

export default TicketsTable;