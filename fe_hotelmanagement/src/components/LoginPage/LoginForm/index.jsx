import { useState } from "react";
import "./style.scss";
import { login } from '../../../services/authService'
import { useNavigate } from "react-router-dom";
import { toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import { OAuthConfig } from "../../../conf/conf";
import { useDispatch } from "react-redux";
import { doUpdateUser } from "../../../redux/action/updateUserAction";

const LoginForm = () => {

  const navigate = useNavigate();
  const dispatch = useDispatch();

  const [formData, setFormData] = useState({
    username: "",
    password: "",
  });

  const [loading, setLoading] = useState(false);
  const [errorMessage, setErrorMessage] = useState("");

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleLogin = async (e) => {
    e.preventDefault();

    // setLoading(true);
    setErrorMessage("");

    try {

      const data = await login(formData.username, formData.password) // gọi api

      if (data && data.code && data.code == 200 && data.result) {
        dispatch(doUpdateUser(data.result))
        toast.success("Đăng nhập thành công !");
        navigate('/')
      } else if (data.response && data.response.data) {
        console.log(data)
        toast.error(data.response.data.message)
        return
      }

      else toast.error(data?.message)

    } catch (error) {
      setErrorMessage(error.message);
      toast.error("Đăng nhập thất bại!");
    }

    setLoading(false);
  };

  // login with gg 
  const handleLoginWithGoogle = () => {
    try {
      const callbackUrl = OAuthConfig.redirectUri;
      const authUrl = OAuthConfig.authUri;
      const googleClientId = OAuthConfig.clientId;

      const targetUrl = `${authUrl}?redirect_uri=${encodeURIComponent(
        callbackUrl
      )}&response_type=code&client_id=${googleClientId}&scope=openid%20email%20profile`;

      console.log(targetUrl);

      window.location.href = targetUrl;
    } catch (error) {
      console.error("OAuth error:", error);
    }
  }


  return (
    <>
      <div className="col-lg-6 loginform-container">
        <div className="card-body p-md-5 mx-md-4">
          <div className="text-center">
            <img
              src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-login-form/lotus.webp"
              style={{ width: "185px" }}
              alt="logo"
            />
            <h4 className="mt-1 mb-5 pb-1">Chào mừng đến với DAS Hotel</h4>
          </div>

          <form>
            <p>Vui lòng nhập tài khoản của bạn</p>

            {errorMessage && <p className="text-danger">{errorMessage}</p>}

            <div className="form-outline mb-4 custom-input">
              <input
                type="text"
                id="form2Example11"
                className="form-control"
                name="username"
                value={formData.username}
                onChange={handleChange}
              />
              <label className="form-label" htmlFor="form2Example11">Tên đăng nhập</label>
            </div>

            <div className="form-outline mb-4 custom-input">
              <input
                type="password"
                id="form2Example22"
                className="form-control"
                name="password"
                value={formData.password}
                onChange={handleChange}
              />
              <label className="form-label" htmlFor="form2Example22">Mật khẩu</label>
            </div>

            <div className="text-center pt-1 mb-3 pb-1">
              <button
                className="btn btn-primary btn-block fa-lg mb-3 custom-button"
                type="button"
                onClick={(e) => { handleLogin(e) }}
                disabled={loading}
              >
                Đăng nhập
              </button>
              <div className="d-flex align-items-center justify-content-between">
                <div className="form-check">
                  <input className="form-check-input" type="checkbox" id="rememberMe" />
                  <label className="form-check-label" htmlFor="rememberMe">
                    Ghi nhớ tài khoản
                  </label>
                </div>
                <a className="text-muted" href="#!">Quên mật khẩu?</a>
              </div>
            </div>

            <div className="d-flex flex-row align-items-center justify-content-center justify-content-lg-start mt-1 mb-3">
              <p className="fw-normal mb-0 me-3">Hoặc đăng nhập với</p>
              <a
                href="#"
                onClick={handleLoginWithGoogle}
                className="btn btn-floating mx-1"
                style={{
                  backgroundColor: "#DB4437", // Màu Google
                  color: "white",
                  borderColor: "#DB4437"
                }}
              >
                <i className="fab fa-google"></i>
              </a>


              <a
                href="#"
                className="btn btn-floating mx-1"
                style={{
                  backgroundColor: "#1877F2", 
                  color: "white",
                  borderColor: "#1877F2"
                }}
              >
                <i className="fab fa-facebook-f"></i>
              </a>
            </div>

            <div className="d-flex align-items-center justify-content-center pb-4">
              <p className="mb-0 me-2">Bạn chưa có tài khoản?</p>
              <button
                type="button"
                className="btn btn-outline-danger register-button"
                onClick={() => navigate('/register')}>
                Đăng ký <span className="arrow-icon">→</span>
              </button>
            </div>
          </form>
        </div>
      </div>
    </>
  );
}

export default LoginForm;