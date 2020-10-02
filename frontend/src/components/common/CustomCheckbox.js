import React from 'react';

import '../../css/customcheckbox.css';

const CustomCheckbox = (props) => {
    
    return (
        <label className="custom-checkbox">
            <span className="checkbox_input">
            {props.children}
            <span className="checkbox_control" style={props.isSelected ? {} : {backgroundColor: "white"}}>
                <svg style={props.isSelected ? {} : {visibility: "none"}} xmlns='http://www.w3.org/2000/svg' viewBox="0 0 24 24" aria-hidden="true" focusable="false">
                    <path fill="none" stroke="white" strokeWidth="5" d="M3.25 11.91l7.37 6.37L21.79 2.59" />
                </svg>
            </span>
            </span>
        </label>
    );
}

export default CustomCheckbox;