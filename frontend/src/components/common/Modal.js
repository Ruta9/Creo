import React from 'react';
import '../../css/modal.css';

const Modal = ({header, size, children, actions}) => {

    return (
        <div className="custom-modal">
            <div className={`ui modal custom-modal ${size} active visible transition`}>
                {header ? <div className="header">
                    {header}
                </div> : null}
                <div className="content">
                    {children}
                </div>
                {actions ? <div className="actions">
                    {actions}
                </div> : null}
            </div>
        </div>
    );
}

export default Modal;