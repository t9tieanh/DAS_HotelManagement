import axios from "axios";
import { store } from "../redux/store";

const instance = axios.create({
    baseURL: "http://localhost:8080",
    withCredentials: true,
});

const excludedUrls = [
    "/auth/login",
    "/auth/sign-up",
    "/auth/refresh-token",
    "/auth/outbound/authentication"
];

instance.interceptors.request.use(
    (config) => {
        const accessToken = store.getState()?.user?.account?.accessToken;

        // Lấy phần URL trước dấu "?" để tránh bị ảnh hưởng bởi query params
        const cleanUrl = new URL(config.url, window.location.origin).pathname;

        // Kiểm tra nếu URL có trong danh sách loại trừ
        const shouldAttachToken = !excludedUrls.includes(cleanUrl);

        if (shouldAttachToken && accessToken) {
            console.log("Đính kèm Authorization header");
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
        console.log("lỗi từ axios : ", error)
        return error;
    }
);

export default instance;
