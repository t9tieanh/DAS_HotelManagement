import { UPDATE_USER, DELETE_USER } from "../action/updateUserAction";
import { UPDATE_TOKEN, UPDATE_ACCESS_TOKEN } from "../action/updateTokenAction";

const INITIAL_STATE = {
    account : {
        accessToken: "",
        refreshToken : "",
        username: "",
        imageUrl: "",
    },
    isAuthentication : false
};

const userReducer = (state = INITIAL_STATE, action) => {
    console.log(action.payload)
    switch (action.type) {
        case UPDATE_USER:
            return {
                ...state, account : {
                    accessToken: action.payload.accessToken,
                    refreshToken : action.payload.refreshToken,
                    username : action.payload.username, 
                    imageUrl : action.payload.imageUrl
                }, 
                isAuthentication : true
            };

            case DELETE_USER:
                return {
                    ...state,
                    account: { 
                        accessToken: "",
                        refreshToken: "",
                        username: "",
                        imageUrl : undefined
                    },
                    isAuthentication: false
                };

            case UPDATE_TOKEN:
                console.log("token mới đây : ",action.payload); 
                return {
                    ...state, 
                    account: {
                        ...state.account, // Giữ nguyên các thuộc tính khác của account
                        accessToken: action.payload.accessToken,
                        refreshToken: action.payload.refreshToken,
                    }, 
                }

                case UPDATE_ACCESS_TOKEN:
                    console.log("token mới đây : ",action.payload); 
                    return {
                        ...state, 
                        account: {
                            ...state.account, // Giữ nguyên các thuộc tính khác của account
                            accessToken: action.payload
                        }, 
                    }
        
        default: return state;
    }
};

export default userReducer;