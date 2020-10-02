import React, {useState, useRef, useEffect} from 'react';
import '../../css/dropdown.css';

const LongDropdown = ({options, selected, onSelection}) => {

    const [isOpen, setIsOpen] = useState(false);
    const [searchValue, setSearchValue] = useState('');
    const dropdownRef = useRef();
    const inputRef = useRef();

    useEffect( () => {
        const onBodyClick = (e) => {
            if (dropdownRef.current.contains(e.target)) {
                return;
            };
            setIsOpen(false);
            setSearchValue('');
        }

        document.body.addEventListener('click', onBodyClick);

        return () => {
            document.body.removeEventListener('click', onBodyClick);
        };

    }, []);

    useEffect(()=>{
        if (isOpen) inputRef.current.focus();
        else inputRef.current.blur();
    }, [isOpen])

    const renderedOptions = () => {
        if (options === null) return null;
        const items = options.filter(option => option.label.toLowerCase().includes(searchValue)).map(option => {
            return (
            <div    className="item" 
                    key={option.value}
                    onClick={() => {
                        setSearchValue('');
                        onSelection(option);
                    }}>{option.label}</div>
            )
            });
        return items;
    }

    const searchValueChanged = (e) => {
        setSearchValue(e.target.value);
    }

    return (
        <div key={selected} ref={dropdownRef} className={`ui search selection long dropdown ${isOpen ? 'visible active' : ''}`} onClick={() => setIsOpen(!isOpen)}>
            <i className="dropdown icon"></i>
            <input ref={inputRef} className="search" autoComplete="off" tabIndex="0" value={searchValue} onChange={searchValueChanged}/>
            <div className="text">{(selected !== undefined && selected !== null && !isOpen) ? selected.label : ''}</div>
            <div className={`menu ${isOpen ? 'visible transition' : ''}`}>
                {renderedOptions()}
            </div>
        </div>
    );
}

export default LongDropdown;