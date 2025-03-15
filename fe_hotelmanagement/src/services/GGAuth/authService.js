import axios from "../../utils/CustomAxios";
import { OAuthConfig } from "../../conf/conf";

const exchangeToken = async (code) => {
    return await axios.post(`auth/outbound/authentication?code=${code}`);
};

const activeGGAccount = async (image, email, name, username, password) => {
    const form = new FormData();
    form.append('img', image);
    form.append('email', email);
    form.append('name',name)
    form.append('username', username);
    form.append('password', password);

    return await axios.post(`auth/outbound/active-account`, form)
}


export {exchangeToken, activeGGAccount}