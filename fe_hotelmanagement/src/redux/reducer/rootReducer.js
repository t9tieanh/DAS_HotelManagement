import { combineReducers } from 'redux';
import userReducer from './userReducer';
import reservationReducer from './reservationReducer';

const rootReducer = combineReducers({
    user : userReducer,
    reservation : reservationReducer
});

export default rootReducer;