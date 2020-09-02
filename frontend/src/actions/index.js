import Axios from "axios"

export const signIn = () => {
    return {
        type: "SIGN_IN"
    };
};

export const signOut = () => {
    return {
        type: "SIGN_OUT"
    };
};

export const checkIfAuthenticated = () => {
    return async (dispatch) => {
        const response = await Axios.get('/api/users/isAuthenticated');

        console.log(response);
        dispatch({
            type: 'AUTH_STATUS_CHECK',
            payload: response.data
        });
    };
};