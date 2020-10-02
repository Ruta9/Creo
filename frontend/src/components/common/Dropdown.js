import React, {useState, useRef, useEffect} from 'react';
import '../../css/dropdown.css';

const Dropdown = ({options, selected, onSelection}) => {

    const [isOpen, setIsOpen] = useState(false);
    const ref = useRef();

    useEffect( () => {

        const onBodyClick = (e) => {
            if (ref.current.contains(e.target)) {
                return;
            };
            setIsOpen(false);
        }

        document.body.addEventListener('click', onBodyClick);

        return () => {
            document.body.removeEventListener('click', onBodyClick);
        };

    }, []);

    const renderedOptions = options.map(option => {
        return (
        <div    className="item" 
                key={option.value}
                onClick={() => onSelection(option)}>{option.label}</div>)
    });

    return (
        <div ref={ref} className={`ui selection dropdown ${isOpen ? 'visible active' : ''}`} onClick={() => setIsOpen(!isOpen)}>
            <i className="dropdown icon"></i>
            <div className="text">{selected !== null ? selected.label : ''}</div>
            <div className={`menu ${isOpen ? 'visible transition' : ''}`}>
                {renderedOptions}
            </div>
        </div>
    );
}

export default Dropdown;