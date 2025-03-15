import axios from "axios";

const API_BASE_URL = "http://localhost:8080";

const authService = {
    login: async (username, password) => {
        try {
            const response = await axios.post(`${API_BASE_URL}/auth/login`, {
                username,
                password,
            })

            return response.data;
        } catch (error) {
            console.error("Login failed", error);
            throw error.response?.data || "Có lỗi xảy ra!";
        }
    },
};

export default authService;