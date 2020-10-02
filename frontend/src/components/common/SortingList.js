import React, {useState, useEffect} from 'react';

const SortingList = ({value, fields, onChange}) => {

    const [sortingOrder, setSortingOrder] = useState({});
    const [direction, setDirection] = useState("ASC");

    useEffect(() => {
        setSortingOrder(generateSortingOrder());
    }, []);

    useEffect(() => {

        if (value.fields !== undefined) {
            let sort = sortingOrder;

            for(let i = 0; i < value.fields.length; i ++){
                sort[value.fields[i]] = (i+1)
            }

            setSortingOrder(sort);
            setDirection(value.direction);
        }

    }, [value]);

    const generateSortingOrder = () => {
        let sort = {};
        fields.forEach(field => {
            sort[field.value] = null;
        });

        return sort;
    }

    const fieldClicked = (field) => {
        let sort = {...sortingOrder};
        if (sort[field.value] !== null){
            let order = sort[field.value];
            sort[field.value] = null;
            fields.forEach(f => {
                if (sort[f.value] > order) sort[f.value] = sort[f.value] - 1;
            })
        }
        else {
            let order = 1;
            fields.forEach(f => {
                if (sort[f.value] != null) order = order + 1;
            })
            sort[field.value] = order;
        }
        setSortingOrder(sort);
        returnSortingOrder(sort);
    }

    const returnSortingOrder = (sort) => {
        let sortArray = [];
        fields.forEach(field => {
            if (sort[field.value] != null){
                sortArray[sort[field.value]] = field.value
            }
        });
        let sortQuery = {};
        sortQuery.direction = direction;
        sortQuery.fields = sortArray.filter(el => el !== undefined);
        onChange(sortQuery);
    }

    const generateItems = () => {

        if (Object.keys(sortingOrder).length === 0) return null;
        let sortedFields = [...fields];
        sortedFields = sortedFields.sort((a, b) => {
            if (sortingOrder[a.value] === null && sortingOrder[b.value] === null) return 0;
            else if (sortingOrder[a.value] === null) return 1;
            else if (sortingOrder[b.value] === null) return -1;
            else return sortingOrder[a.value]  - sortingOrder[b.value];
        });
        
        return sortedFields.map(field => {
            return (
                <div className="item" key={field.value}>
                    <div className="content">
                        {field.label}
                    </div>
                    <div className="right floated content">
                        <input style={
                            {maxHeight:"20px", 
                            maxWidth:"20px", 
                            cursor:"pointer", 
                            padding:"5px", 
                            backgroundColor: sortingOrder[field.value] == null ? "white" : "#51b937",
                            color: sortingOrder[field.value] == null ? "white" : "white",
                            fontWeight: 900
                        }
                        } name={field.value} type="text" readOnly value={sortingOrder[field.value] === null ? '' : sortingOrder[field.value]} onClick={(e) => fieldClicked(field)}/>
                    </div>
                </div>
            );
        })
    }

    return (
        <div className="checkbox-list">
            <div className="ui small form">
                <div className="ui small celled middle aligned list">
                    <div className="item select-all">
                        <div 
                        style={{backgroundColor: "white", cursor:"pointer", paddingRight: "3px"}}
                        className="right floated middle aligned content" 
                        onClick={() => direction === "ASC" ? setDirection("DESC") : setDirection("ASC")}>
                            {direction === "ASC" ? <i className="ui long arrow alternate up icon"/> :
                                <i className="ui long arrow alternate down icon"/>} 
                            {direction}
                        </div>
                    </div>
                    {generateItems()}
                </div>
        </div>
    </div>
    );
}

export default SortingList;