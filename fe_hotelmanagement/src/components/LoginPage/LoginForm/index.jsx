import { useState } from "react";

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
                <h4 className="mt-1 mb-5 pb-1">We are The Lotus Team</h4>
            </div>

            <form>
                <p>Please login to your account</p>

                <div className="form-outline mb-4">
                <input
                    type="email"
                    id="form2Example11"
                    className="form-control"
                    placeholder="Phone number or email address"
                    name="email"
                    value={formData.email}
                    onChange={handleChange}
                />
                <label className="form-label" htmlFor="form2Example11">Username</label>
                </div>

                <div className="form-outline mb-4">
                <input
                    type="password"
                    id="form2Example22"
                    className="form-control"
                    name="password"
                    value={formData.password}
                    onChange={handleChange}
                />
                <label className="form-label" htmlFor="form2Example22">Password</label>
                </div>

                <div className="text-center pt-1 mb-5 pb-1">
                <button
                    className="btn btn-primary btn-block fa-lg gradient-custom-2 mb-3"
                    type="button"
                    onClick={handleLogin}
                >
                    Log in
                </button>
                <a className="text-muted" href="#!">Forgot password?</a>
                </div>

                <div className="d-flex align-items-center justify-content-center pb-4">
                <p className="mb-0 me-2">Don't have an account?</p>
                <button type="button" className="btn btn-outline-danger">
                    Create new
                </button>
                </div>
            </form>
            </div>
        </div>
      </>
    );
  }
  
  export default LoginForm;