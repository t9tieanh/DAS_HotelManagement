import axios from "axios";
import { store } from "../redux/store";

const instance = axios.create({
    baseURL: "http://localhost:8080",
    withCredentials: true,
});

const excludedUrls = ["/auth/login", "/auth/register", "/auth/refresh-token"];


instance.interceptors.request.use(
    (config) => {

        const accessToken = store.getState()?.user?.account?.accessToken;
        const shouldAttachToken = !excludedUrls.some((url) => config.url.includes(url));

        if (shouldAttachToken && accessToken) {
            config.headers["Authorization"] = `Bearer ${accessToken}`;
        }

        return config;
    },
    (error) => Promise.reject(error)
);


instance.interceptors.response.use(
    (response) => {
        return response?.data;
    },
    async (error) => {
        console.log("lỗi từ axios : " ,error)
        return error;
    }
);

export default instance;
