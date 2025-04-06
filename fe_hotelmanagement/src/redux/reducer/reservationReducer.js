import { DELETE_RESERVATION } from "../action/reservationAction";
import { ADD_RESERVATION } from "../action/reservationAction";

const INITIAL_STATE = {
    reservation : undefined
};

const reservationReducer = (state = INITIAL_STATE, action) => {
    switch (action.type) {
        case ADD_RESERVATION:
            return {
                ...state, reservation : action.payload,
            };
        case DELETE_RESERVATION:
            return {
                ...state, reservation : undefined,
            };
        default: return state;
    }
};

export default reservationReducer;