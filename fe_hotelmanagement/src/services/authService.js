import axios from "../utils/CustomAxios";

const login = async (username, password) => {
    return await axios.post(`auth/login`, {username, password})
}

export {login}
