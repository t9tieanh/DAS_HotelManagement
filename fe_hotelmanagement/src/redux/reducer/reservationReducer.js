import { DELETE_RESERVATION } from "../action/reservationAction";
import { CREATE_RESERVATION } from "../action/reservationAction";
import { UPDATE_EXPIRE_DATE_TIME } from "../action/reservationAction";

const INITIAL_STATE = {
    reservationId : undefined,
    expireDateTime : undefined,
};

const reservationReducer = (state = INITIAL_STATE, action) => {
    switch (action.type) {
        case CREATE_RESERVATION:
            const expireDate = new Date(action.payload.expireDateTime.split('.')[0]);  

            return {
                ...state, reservationId : action.payload.reservationId,
                expireDateTime : expireDate,
            };
        case DELETE_RESERVATION:
            return {
                ...state, reservationId : undefined,
                expireDateTime : undefined,
            };

        case UPDATE_EXPIRE_DATE_TIME:
            const expireDate2 = new Date(action.payload.split('.')[0]); 

            return {
                ...state, 
                expireDateTime : expireDate2,
            };

        default: return state;
    }
};

export default reservationReducer;