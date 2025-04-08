import axios from "../../utils/CustomAxios";

export const getProfile = async () => {
    try {
        const response = await axios.get(`/profile`)
        console.log("Profile data: ", response)
        return response.result
    }
    catch (error) {
        console.log("Error fetching profile data: ", error)
        throw error
    }
};

export const getProfile1 = async () => {
    return await axios.get(`/profile`)
};

export const updateProfile = async (profile) => {
    const formData = new FormData();
    let hasData = false;

    if (profile.name !== undefined) {
        formData.append("name", profile.name);
        hasData = true;
    }
    if (profile.phone !== undefined) {
        formData.append("phone", profile.phone);
        hasData = true;
    }
    if (profile.email !== undefined) {
        formData.append("email", profile.email);
        hasData = true;
    }

    if (!hasData) {
        console.log("Không có dữ liệu thay đổi, không gửi API.");
        return null;
    }

    return await axios.post(`/edit-profile`, formData);
};
