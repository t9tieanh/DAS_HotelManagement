export const UPDATE_TOKEN = 'UPDATE_TOKEN';

export const doUpdateToken = (data) => {
    return {
        type: UPDATE_TOKEN,
        payload: data
    };
};

export const UPDATE_ACCESS_TOKEN = 'UPDATE_ACCESS_TOKEN';
export const doUpdateAccessToken = (data) => {
    return {
        type: UPDATE_ACCESS_TOKEN,
        payload: data
    };
};