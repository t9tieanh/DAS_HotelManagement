export const CREATE_RESERVATION = 'ADD_RESERVATION';

export const doCreateReservation = (data) => {
    return {
        type: CREATE_RESERVATION,
        payload: data
    };
};

export const DELETE_RESERVATION = 'DELETE_RESERVATION';
export const doDeleteReservation = () => {
    return {
        type: DELETE_RESERVATION,
    };
};

export const UPDATE_EXPIRE_DATE_TIME = 'UPDATE_EXPIRE_DATE_TIME';
export const doUpdateExpireDateTime = (data) => {
    return {
        type: UPDATE_EXPIRE_DATE_TIME,
        payload: data
    };
};