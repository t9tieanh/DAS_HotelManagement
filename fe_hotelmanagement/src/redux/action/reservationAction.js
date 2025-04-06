export const ADD_RESERVATION = 'ADD_RESERVATION';

export const doAddReservation = (data) => {
    return {
        type: ADD_RESERVATION,
        payload: data
    };
};

export const DELETE_RESERVATION = 'DELETE_RESERVATION';
export const doDeleteReservation = () => {
    return {
        type: DELETE_RESERVATION,
    };
};