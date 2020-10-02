import {combineReducers} from 'redux';
import authReducer from './authReducer';
import identityReducer from './identityReducer';


export default combineReducers({
    isAuthenticated: authReducer,
    identity: identityReducer
});