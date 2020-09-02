import React, {useEffect, useState} from 'react';
import {Redirect} from 'react-router-dom';

const PageNotFoundError = () =>
{
    const [redirect, setRedirect] = useState(false);

    useEffect(() => {
        const timerId = setTimeout(() => setRedirect(true), 1000);

        return (
            () => {
                clearTimeout(timerId);
            }
        );
    }, []);

    return (
        <div className="error-404">
            <img className="ui centered huge image" alt="page not found error" src={require("../../images/page_not_found.png")}/>;
            <div className="ui center aligned huge header">
                Sorry! Page not found.
            </div>
            <div className="ui center aligned large header">
                Redirecting...
            </div>
            {redirect ? <Redirect to="/"/> : null}
        </div>
    );
};

export default PageNotFoundError;