import React, {useState, useEffect}  from 'react';
import Axios from 'axios';
import {useParams} from 'react-router-dom';

import TicketsFilter from './TicketsFilter';
import TicketsTable from './TicketsTable';
import TablePaginationMenu from './TablePaginationMenu';
import '../../../css/tickets.css';
import StoryCreatePopup from '../stories/StoryCreatePopup';

const Tickets = (props) => {

    const {id} = useParams();
    const [filterCollapsed, setFilterCollapsed] = useState(true);
    const [filterQuery, setFilterQuery] = useState(null);
    const [ticketsPage, setTicketsPage] = useState(null);
    const [page, setPage] = useState(null);

    const [isStoryCreator, setIsStoryCreator] = useState(false);
    const [showProjectCreateForm, setShowProjectCreateForm] = useState(false);

    useEffect(() => {
        checkIfUserIsStoryCreator();
        let savedPage = localStorage.getItem(`projectTicketsPage_${id}`);
        if (savedPage !== null) setPage(savedPage);
        else {
            setPage(0);
        }
    }, []);

    useEffect(() => {
        setFilterCollapsed(true);
        if (page !== null && filterQuery !== null){
            fetchTickets();
        }
    }, [page, filterQuery]);

    const checkIfUserIsStoryCreator = async () => {
        const results = await Axios.get(`/api/security/projects/storiesCreator/${id}`);
        if (results.status === 200){
            setIsStoryCreator(results.data);
        }
    }

    const fetchTickets = async () => {
        const results = await Axios.post(`/api/stories/project/${id}/${page}`, filterQuery)
        if (results.status === 200){
            setTicketsPage(results.data);
            localStorage.setItem(`projectTicketsPage_${id}`, results.data.currentPageNumber-1);
        }
    }

    return (
        <div>
            {showProjectCreateForm ? <StoryCreatePopup onClose={(close) => {setShowProjectCreateForm(false); if (close) setPage(0); }}/> : null}
            <div className="ui basic segment tickets-table-menu">
                <span style={{cursor: "pointer"}} onClick={() => setFilterCollapsed(!filterCollapsed)}><i className={`${filterCollapsed ? "caret right" : "caret down"} icon`}/> Show Filter & Sort</span>
                {isStoryCreator ? <button className="ui button custom-active-green" onClick={() => setShowProjectCreateForm(true)}>Create New Story</button> : null}
            </div>
                <div className="ui stackable grid">
                    <div className={`${filterCollapsed ? '' : "four column"} row`}>
                        <div className="column" style={
                            {flex: "0 0 270px",
                            display: filterCollapsed ? "none" : ""
                            }}>
                            <TicketsFilter submitCallback={(filterQuery) => setFilterQuery(filterQuery)}/>
                        </div>
                        <div className="column" style={{flex: 1}}>
                            {ticketsPage !== null ? 
                            (<React.Fragment>
                                <TicketsTable tickets={ticketsPage.tickets}/>
                            <div style={{"float":"right", "marginTop":"5px", "marginRight": "20px"}}>Showing {ticketsPage.firstPageRecord} - {ticketsPage.lastPageRecord} out of {ticketsPage.totalNumberOfRecords}</div>
                            </React.Fragment>)
                             : null}
                            
                        </div>
                    </div>
                    <div className="centered row">
                        {ticketsPage !== null ? <TablePaginationMenu
                                currentPage={ticketsPage.currentPageNumber}
                                numberOfPages={ticketsPage.totalNumberOfPages}
                                onPageSelect={(pageNumber) => setPage(pageNumber)}/> : null}
                    </div>
                </div>
        </div>
    );
}

export default Tickets;