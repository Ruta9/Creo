import React, {useState, useEffect} from 'react';
import {useParams} from 'react-router-dom';
import Axios from 'axios';

const TicketsTableRow = ({story}) => {

    const {id} = useParams();
    const [isExpanded, setIsExpanded] = useState(false);
    const [taskList, setTaskList] = useState(null);

    useEffect(() => {
        if (taskList === null && isExpanded){
            getTasksForStory();
        }
    }, [isExpanded]);

    const getTasksForStory = async () => {
        const results = await Axios.get(`/api/tasks/project/${id}/story/${story.id}`);
        if (results.status === 200){
            setTaskList(results.data);
        }
    }

    const renderTasks = () => {
        return taskList.map((task, i) => {
            return (
                <tr className={`task row ${i === 0 ? "first" : ""}`} key={task.id}> 
                    <td className="center aligned blank-table-data"></td>
                    <td className="link">{task.name}</td>
                    <td>{task.shortenedDescription}</td>
                    <td>{task.status}</td>
                    <td>{task.assignee}</td>
                    <td>{task.lastUpdatedDate}</td>
                    <td>{task.createdDate}</td>
                </tr>
            );
        });
    }

    return (
        <React.Fragment>
            <tr onClick={() => setIsExpanded(!isExpanded)}> 
                <td className="center aligned"><i className={`ui icon caret ${isExpanded ? "right" : "down"}`}/></td>
                <td className="link">{story.name}</td>
                <td>{story.shortenedDescription}</td>
                <td>{story.status}</td>
                <td>{story.assignee}</td>
                <td>{story.lastUpdatedDate}</td>
                <td>{story.createdDate}</td>
            </tr>
            {isExpanded && taskList !== null ? renderTasks() : null}
        </React.Fragment>
    );
}

export default TicketsTableRow;