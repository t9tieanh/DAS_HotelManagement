import { Navigate } from "react-router-dom";
import { useSelector } from "react-redux";
import { toast } from "react-toastify";

const PrivateBookingRoute = ({ children }) => {
  const isAuthentication = useSelector(state => state.user.isAuthentication)
  const isReservation = useSelector(state => state.reservation.reservation)

  // chưa login
  if (!isAuthentication) {
    return <Navigate to="/login" replace />;
  }

  // chưa có giao dịch đặt phòng
  if (!isReservation) {
    toast.error("Bạn chưa chọn phòng nào để tiến hành đặt!")
    return <Navigate to="/" replace />;
  }

  return children;
};

export default PrivateBookingRoute;