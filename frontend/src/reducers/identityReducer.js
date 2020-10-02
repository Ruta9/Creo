export default (state = null, action) => {
    switch (action.type) {
        case 'SET_IDENTITY': 
            return action.payload;
        default:
            return state;
    }
};