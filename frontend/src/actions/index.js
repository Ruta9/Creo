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
        let value = null;
        if (response.status === 200) {
            value = response.data;
        }

        dispatch({
            type: 'AUTH_STATUS_CHECK',
            payload: value
        });
    };
};