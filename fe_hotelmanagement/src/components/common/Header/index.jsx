import { useNavigate } from "react-router-dom";
import userAvatar from "../../../assets/img/user.png";
import logo from "../../../assets/img/logo1.png";
import { useSelector } from "react-redux";
import { useDispatch } from "react-redux";
import { doDeleteUser } from "../../../redux/action/updateUserAction";
import { logout } from "../../../services/authService";
import './style.scss'
import { toast } from "react-toastify";
import { BASE_URL } from "../../../conf/baseUrl";

const Header = () => {

    const fileUrl = 'files/image'

    const dispatch = useDispatch()
    const isAuthentication = useSelector(state => state.user.isAuthentication)
    const account = useSelector(state => state.user.account)

    const imageUrl = account && account.imageUrl ? `${BASE_URL}/${fileUrl}/${account.imageUrl}` : userAvatar;

    const navigate = useNavigate();

    const handleLogOut = async () => {
        const data = await logout();

        dispatch(doDeleteUser())

        if (data && data.code == 200) {
            navigate('/login')
            toast.success("Đăng xuất thành công !")
        }
    }

    return <>
        <header className="header-section">
            <div className="top-nav">
                <div className="container">
                    <div className="row">
                        <div className="col-lg-4">
                            <ul className="tn-left">
                                <li><i className="fa fa-phone"></i> (+84) 3456789</li>
                                <li><i className="fa fa-envelope"></i> hotelas@gmail.com</li>
                            </ul>
                        </div>
                        <div className="col-lg-8">
                            <div className="tn-right">
                                <div className="top-social">
                                    <a href="#"><i className="fa fa-facebook"></i></a>
                                    <a href="#"><i className="fa fa-twitter"></i></a>
                                    <a href="#"><i className="fa fa-instagram"></i></a>
                                </div>
                                {isAuthentication && (
                                    <>
                                        <a href="#" className="bk-btn">Xin chào {account?.username}</a>
                                        <button href="#" onClick={handleLogOut} className="bk-btn btn-logout ml-1">Đăng xuất</button>

                                        <div className="user-profile">
                                            <img src={imageUrl} alt="User Avatar" />
                                            <div className="flag-dropdown">
                                                <ul>
                                                    <li><a href="/profile">Hồ sơ</a></li>
                                                    <li><a href="#">Cài đặt</a></li>
                                                    <li><a href="#">Đăng xuất</a></li>
                                                </ul>
                                            </div>
                                        </div>
                                    </>
                                )}
                                {!isAuthentication && <a href="#" onClick={() => { navigate('/login') }} className="bk-btn">Đăng nhập</a>}

                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div className="menu-item">
                <div className="container">
                    <div className="row">
                        <div className="col-lg-2">
                            <div className="logo">
                                <a href="#" onClick={() => { navigate('/') }}>
                                    <img src={logo} alt="" />
                                </a>
                            </div>
                        </div>
                        <div className="col-lg-10">
                            <div className="nav-menu">
                                <nav className="mainmenu">
                                    <ul>
                                        <li onClick={() => { navigate('/') }} className="active"><a>Trang chủ</a></li>
                                        <li><a href="./about-us.html">Về chúng tôi</a></li>
                                        <li><a href="./pages.html">Pages</a>
                                            <ul className="dropdown">
                                                <li><a href="./room-details.html">Room Details</a></li>
                                                <li><a href="./blog-details.html">Blog Details</a></li>
                                                <li><a href="#">Family Room</a></li>
                                                <li><a href="#">Premium Room</a></li>
                                            </ul>
                                        </li>
                                        <li><a href="./blog.html">Bài viết</a></li>
                                        <li><a href="./contact.html">Liên hệ</a></li>
                                    </ul>
                                </nav>
                                <div className="nav-right search-switch">
                                    <i className="icon_search"></i>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </header>
    </>
}

export default Header;