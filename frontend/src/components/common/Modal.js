import React from 'react';
import '../../css/modal.css';

const Modal = ({header, size, children, actions, style}) => {

    return (
        <div className="custom-modal">
            <div className={`ui modal custom-modal ${size} active visible transition`} style={style}>
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