import { useState } from "react";
import "./style.scss";
import authService from "../../../services/authService";
import { useNavigate } from "react-router-dom";
import {toast} from "react-toastify";
import "react-toastify/dist/ReactToastify.css";

const LoginForm = () => {

  const navigate = useNavigate();

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
    console.log("Login info:", formData);

    setLoading(true);
    setErrorMessage("");

    try {
      const result = await authService.login(formData.username, formData.password);
      console.log("Login success:", result);

      localStorage.setItem("token", result.token);
      toast.success("Đăng nhập thành công!");
      navigate("/");

    } catch (error) {
      setErrorMessage(error.message);
      toast.error("Đăng nhập thất bại!"); 
    }

    setLoading(false);
  };


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

            <div className="text-center pt-1 mb-5 pb-1">
              <button
                className="btn btn-primary btn-block fa-lg gradient-custom-2 mb-3 custom-button"
                type="button"
                onClick={handleLogin}
                disabled={loading}
              >
                Đăng nhập
              </button>
              <a className="text-muted" href="#!">Quên mật khẩu?</a>
            </div>

            <div className="d-flex align-items-center justify-content-center pb-4">
              <p className="mb-0 me-2">Bạn chưa có tài khoản?</p>
              <button type="button" className="btn btn-outline-danger create-new-button">
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