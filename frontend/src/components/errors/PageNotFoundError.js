import React from 'react';

const PageNotFoundError = () =>
{
    return (
        <div className="error-404">
            <img className="ui centered huge image" src={require("../../images/page_not_found.png")}/>;
            <div className="ui center aligned huge header">
                Sorry! Page not found.
            </div>
        </div>
    );
};

export default PageNotFoundError;