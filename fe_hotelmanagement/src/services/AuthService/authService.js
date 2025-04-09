import axios from "../../utils/CustomAxios";
import { store } from "../../redux/store";
import { doDeleteReservation } from "../../redux/action/reservationAction";
import { doDeleteUser } from "../../redux/action/updateUserAction";
import { cancelReservation } from "../ReservationService/reservationService";
import { toast } from "react-toastify";

const login = async (username, password) => {
    return await axios.post(`auth/login`, {username, password})
}

const logout = async () => {
    const data = await axios.post(`auth/logout`)
    if (data && data.code && data.code == 200) {
        await cancelReservation()
    } else if (data.response && data.response.data) {
        toast.error(data.response.data.message)
    } else {
        toast.error(data?.message)
    }
    store.dispatch(doDeleteUser()); // xóa thông tin user
    store.dispatch(doDeleteReservation()) // xóa thông tin giao dịch phòng
    toast.success("Đăng xuất thành công !")

    return true
}

export {login, logout}
