export default (state = false, action) => {
    switch (action.type) {
        case 'SIGN_IN': 
            return true;
        case 'SIGN_OUT':
            return false;
        case 'AUTH_STATUS_CHECK':
            return action.payload;
        default:
            return state;
    }
};