import { useState } from "react";
import "./style.scss"; // Import the CSS file

const LoginForm = () => {

  const [formData, setFormData] = useState({
    email: "",
    password: "",
  });

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleLogin = () => {
    console.log("Login info:", formData);
    // Xử lý đăng nhập ở đây
  };


  return (
    <>
      <div className="col-lg-6">
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
          <p>Vui lòng đăng nhập vào tài khoản của bạn</p>

            <div className="form-outline mb-4 custom-input-container">
              <input
                type="email"
                id="form2Example11"
                className="form-control custom-input"
                placeholder="Số điện thoại hoặc địa chỉ email"
                name="email"
                value={formData.email}
                onChange={handleChange}
              />
            </div>

            <div className="form-outline mb-4 custom-input-container">
              <input
                type="password"
                id="form2Example22"
                className="form-control custom-input"
                placeholder="Mật khẩu"
                name="password"
                value={formData.password}
                onChange={handleChange}
              />
            </div>

            <div className="text-center pt-1 mb-5 pb-1">
              <button
                className="btn btn-primary btn-block fa-lg gradient-custom-2 mb-3 custom-button"
                type="button"
                onClick={handleLogin}
              >
                Đăng nhập
                </button>
                <a className="text-muted" href="#!">Quên mật khẩu?</a>
                </div>

            <div className="d-flex align-items-center justify-content-center pb-4">
            <p className="mb-0 me-2">Bạn chưa có tài khoản?</p>
            <button type="button" className="btn btn-outline-danger create-new-button">
            Đăng ký<span className="arrow-icon">→</span>
            </button>
            </div>
          </form>
        </div>
      </div>
    </>
  );
}

export default LoginForm;