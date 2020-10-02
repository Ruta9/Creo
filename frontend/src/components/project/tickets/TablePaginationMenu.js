import React, {useState, useEffect} from 'react';
import '../../../css/ticketspaginationmenu.css';

const TablePaginationMenu = ({currentPage, numberOfPages, onPageSelect}) => {

    const renderItems = () => {
        const elements = [];
        if (numberOfPages <= 10) {
            for (let i = 1; i <= numberOfPages; i++) {
                elements.push(i);
            }
        }
        else {
            if (currentPage - 5 < 0) {
                for (let i = 1; i <= 5; i++) {
                    elements.push(i);
                }
                elements.push(-1);
                elements.push(numberOfPages);
            }

            else if (currentPage + 5 > numberOfPages){
                elements.push(1);
                elements.push(-1);
                for (let i = numberOfPages - 5; i <= numberOfPages; i++) {
                    elements.push(i);
                }
            }

            else {
                elements.push(1);
                elements.push(-1);
                for (let i = currentPage - 2; i <= currentPage + 2; i++) {
                    elements.push(i);
                }
                elements.push(-1);
                elements.push(numberOfPages);
            }
        }

        return elements.map(e => {
            return (
                <a key={e}
                className={`item ${e === currentPage ? 'active' : ''} ${e === -1 ? 'disabled' : ''}`}
                onClick={(event) => e !== -1 ? onPageSelect(e-1) : null}>
                    {e === -1 ? '...' : e}
                </a>
            );
        });
    }

    return (
        <div className="ui pagination secondary menu tickets-pagination-menu">
            {renderItems()}
        </div>
    )
}

export default TablePaginationMenu;