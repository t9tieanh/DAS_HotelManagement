export const UPDATE_USER = 'UPDATE_USER';
export const DELETE_USER = 'DELETE_USER';

export const doUpdateUser = (data) => {
    return {
        type: UPDATE_USER,
        payload: data
    };
};

export const doDeleteUser = () => {
    return {
        type: DELETE_USER
    };
};
