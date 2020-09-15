import React, {useState, useEffect} from 'react';
import {useParams} from 'react-router-dom';
import Axios from 'axios';

import Loader from '../common/Loader';

const ProjectSecurity = (props) => {

    const {id} = useParams();
    const [canAccess, setCanAccess] = useState(null);

    useEffect(()=>{

        (async () => {
            const response = await Axios.get(`/api/security/projects/${id}`);
            if (response.status === 200) setCanAccess(response.data);
            else setCanAccess(false);
        })();
    }, []);

    const renderIfSecure = () => {
        if (canAccess === null) {
            return <Loader/>
        }
        else if (canAccess === true){
            return props.children;
        }
        else 
            return (
                <div className="ui medium message error">
                    Project not found or you do not have the required access rights to see it
                </div>
            );
    }

    return renderIfSecure();
}

export default ProjectSecurity;