import React, {useState} from 'react';

import CustomCheckbox from './CustomCheckbox';
import '../../css/checkboxlist.css';

const CheckboxList = ({list, selected, onSelectionChange, keyProperty, labelProperty}) => {

    const [selectAll, setSelectAll] = useState(false);

    const selectAllOrNone = (selectAll) => {
        selectAll ? onSelectionChange(list) : onSelectionChange([]);
    }

    const isSelected = (item) => {
        if (selected.find(data => data[keyProperty] === item[keyProperty]) !== undefined) return true;
        else return false;
    }

    const selectionChanged = (item, itemSelected) => {
        let selectedItems = [...selected];
        if (itemSelected) selectedItems.push(item);
        else selectedItems = selectedItems.filter(i => i[keyProperty] !== item[keyProperty]);
        return selectedItems;
    }


    const renderItems = () => {
        return list.map(data => {
            return (
            <div className="item" key={data[keyProperty]}>
                <div className="content">
                    {data[labelProperty]}
                </div>
                <div className="right floated content">
                    <CustomCheckbox isSelected={isSelected(data)}>
                            <input type="checkbox" name="isSelected" checked={isSelected(data)} onChange={(e) => {
                                onSelectionChange(selectionChanged(data, e.target.checked));
                                setSelectAll(false);
                            }}/>
                    </CustomCheckbox>
                </div>
            </div>
            )
        })
    }


    return (
        <div className="checkbox-list">
            <div className="ui form">
                <div className="ui small celled middle aligned list">
                    <div className="item select-all">
                        <div className="right floated middle aligned content">
                            All
                            <CustomCheckbox isSelected={selectAll}>
                                <input type="checkbox" name="all" checked={selectAll} onChange={() => {
                                    selectAllOrNone(!selectAll);
                                    setSelectAll(!selectAll);
                                }}/>
                            </CustomCheckbox>

                        </div>
                    </div>
                    {renderItems()}
                </div>
            </div>
        </div>
    );
}

export default CheckboxList;