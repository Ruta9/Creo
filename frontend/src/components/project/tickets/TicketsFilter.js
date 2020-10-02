import React, {useState, useEffect} from 'react';
import {useParams} from 'react-router-dom';
import Axios from 'axios';

import {Form, FormInput} from '../../common/Form';
import '../../../css/ticketsfilter.css';
import LongDropdown from '../../common/LongDropdown';
import SortingList from '../../common/SortingList';
import CheckboxList from '../../common/CheckboxList';

const TicketsFilter = ({submitCallback}) => {

    const fields = [
        {value: "name", label: "Name"},
        {value: "shortenedDescription", label: "Description"},
        {value: "assignee", label: "Assignee"},
        {value: "creator", label: "Creator"},
        {value: "status", label: "Status"},
        {value: "lastUpdatedDate_date", label: "Last updated date"},
        {value: "createdDate_date", label: "Created date"}
    ]

    const [team, setTeam] = useState([]);
    const [statuses, setStatuses] = useState([]);
    const [assignee, setAssignee] = useState(null);
    const [owner, setOwner] = useState(null);
    const [selectedStatuses, setSelectedStatuses] = useState([]);

    const [filter, setFilter] = useState(null);
    const [sort, setSort] = useState({});

    const {id} = useParams();

    useEffect(()=> {
        init();
    }, []);

    const getStatuses = async () => {
        const results = await Axios.get(`/api/statuses/project/story/all/${id}`);
        if (results.status === 200){
            setStatuses(results.data);
        }
        return results.data;
    }

    const getTeam = async () => {
        const results = await Axios.get(`/api/team/project/${id}`);
        if (results.status === 200){
            results.data.forEach(teamMember => {
                teamMember.label = teamMember.firstname + ' ' + teamMember.lastname;
                teamMember.value = teamMember.id;
            });
            setTeam([{label: 'All', value: null, id: null}, ...results.data]);
        }
        return results.data;
    }

    const init = async () => {
        const statuses = await getStatuses();
        const team = await getTeam();
        let filterQuery = localStorage.getItem(`projectTicketsFilterQuery_${id}`);
        if (filterQuery == "undefined" || filterQuery === null ||  filterQuery === undefined) {
            filterQuery = generateDefaultFilterQuery(statuses);
            localStorage.setItem(`projectTicketsFilterQuery_${id}`, JSON.stringify(filterQuery));
        }
        else {
            filterQuery = JSON.parse(filterQuery);
        }

        setFilter(filterQuery.filter);
        setSort(filterQuery.sort);

        //Send it to the parent to send a get to the back-end
        submitCallback(filterQuery);

        //Set filtering and sorting fields
        parsefilterQuery(filterQuery, team, statuses);
    }

    const generateDefaultFilterQuery = (statuses) => {
        const sort = {
            direction: "DESC",
            fields: ["lastUpdatedDate"]
        };
        const filter = {
            statuses: statuses.filter((status, i) => i !== statuses.length-1).map(status => status.id)
        }
        const query = {
            filter: filter,
            sort: sort
        }
        return query;
    }

    const parsefilterQuery = (filterQuery, team, statuses) => {
        if (filterQuery.filter.assigneeId !== undefined) setAssignee(team.filter(t => t.id === filterQuery.filter.assigneeId));
        if (filterQuery.filter.ownerId !== undefined) setOwner(team.filter(t => t.id === filterQuery.filter.ownerId));
        if (filterQuery.filter.statuses !== undefined) setSelectedStatuses(statuses.filter(s => filterQuery.filter.statuses.includes(s.id)));
    }

    const getDefaultFieldValue = (field) => {
        if (filter !== null && filter[field] !== undefined){
            return filter[field];
        }
        else return null;
    }

    const generateFilterAndSortQuery = (form) => {
        let filter = {...form};
        if (assignee !== null) filter.assigneeId = assignee.id;
        if (owner !== null) filter.ownerId = owner.id;
        if (selectedStatuses !== []) filter.statuses = selectedStatuses.map(status => status.id);
        const query = {
            filter: filter,
            sort: sort
        };
        setFilter(filter);
        localStorage.setItem(`projectTicketsFilterQuery_${id}`, JSON.stringify(query));
        return query;
    }

    return (
        <div className="ui basic segment tickets-filter">
            <Form size="small" validate={(form)=>{return {}}} onSubmit={(form) => submitCallback(generateFilterAndSortQuery(form))}>
                <span><i className="ui filter icon"/> Filters</span>
                <div className="ui divider"/>
                <i className="ui question circle outline icon"/>
                <span style={{color: "dimgray", fontSize:"8pt"}}><i>Write * at the start of the search phrase to simulate "contains" function</i></span>
                
                <FormInput value={getDefaultFieldValue("name")} name="name" type="text" placeholder="Name"/>
                <FormInput value={getDefaultFieldValue("description")} name="description" textarea="true" rows="1" placeholder="Description"/>
                <br/>
                <label>Assignee:</label><br/>
                <LongDropdown
                options={team}
                selected={assignee}
                onSelection={(a) => setAssignee(a)}/>
                <br/>

                <label>Owner:</label><br/>
                <LongDropdown
                options={team}
                selected={owner}
                onSelection={(a) => setOwner(a)}/>
                <br/><br/>

                <label>Statuses:</label><br/>
                <CheckboxList
                    list={statuses}
                    selected={selectedStatuses}
                    onSelectionChange={(selection) => setSelectedStatuses(selection)}
                    keyProperty="id"
                    labelProperty="name"/>
                <br/>

                <label>Updated date:</label>
                <FormInput value={getDefaultFieldValue("lastUpdatedDate")} name="lastUpdatedDate" type="date"/>
                <label>Created date:</label>
                <FormInput value={getDefaultFieldValue("createdDate")} name="createdDate" type="date"/>
                <br/>
                <span>
                    <i className="ui long arrow alternate down icon"/> 
                    <i className="ui long arrow alternate up icon"/> 
                    Sorting
                    </span>
                <div className="ui divider"/>
                <SortingList value={sort} fields={fields} onChange={(sort) => setSort(sort)}/>
                <br/>
                <div className="ui divider"/>
                <button className="ui small button" type="submit">Filter & Sort</button>
            </Form>
                             
        </div>
    );
}
  
export default TicketsFilter;