import { useNavigate } from "react-router-dom";
import userAvatar from "../../../assets/img/user.png";
import logo from "../../../assets/img/logo1.png";
import { useSelector } from "react-redux";
import { useDispatch } from "react-redux";
import { doDeleteUser } from "../../../redux/action/updateUserAction";
import { logout } from "../../../services/AuthService/authService";
import './style.scss'
import { IoLogOutSharp } from "react-icons/io5";
import { BASE_URL } from "../../../conf/baseUrl";

const Header = () => {

    const fileUrl = 'files/image'

    const dispatch = useDispatch()
    const isAuthentication = useSelector(state => state.user.isAuthentication)
    const account = useSelector(state => state.user.account)

    const imageUrl = account && account.imageUrl ? `${BASE_URL}/${fileUrl}/${account.imageUrl}` : userAvatar;
    const navigate = useNavigate();

    const handleLogOut = async () => {
        await logout();

        // dispatch(doDeleteUser())

        navigate('/login')
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
                                        <button href="#" onClick={handleLogOut} className="bk-btn btn-logout ml-1"><IoLogOutSharp size = {"20"}/>Đăng xuất</button>

                                        <div className="user-profile">
                                            <img
                                                src={imageUrl}
                                                referrerpolicy="no-referrer"
                                                alt="User Avatar" />
                                            <div className="flag-dropdown">
                                                <ul>
                                                    <li><a href="/profile">Hồ sơ</a></li>
                                                    <li><a href="#">Cài đặt</a></li>
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
                                        <li onClick={() => { navigate('/about-us') }}><a> Về chúng tôi</a></li>
                                        <li onClick={() => { navigate('/about-dev') }} ><a>Về nhà phát triển</a></li>
                                    </ul>
                                </nav>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </header>
    </>
}

export default Header;