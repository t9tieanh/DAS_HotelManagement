import { useState } from "react";
import './style.scss';
import LoginForm from "../../components/LoginPage/LoginForm";
import InfoComponent from "../../components/LoginPage/InfoComponent";

const LoginPage = () => {

  return (
    <section className="login-page-component h-100 gradient-form container py-5 h-100">
      <div className="container d-flex justify-content-center">
        <div className="row d-flex justify-content-center align-items-center h-100 card rounded-3 text-black custom-width">
          <div className="row g-0">
            <LoginForm />
            <InfoComponent />
          </div>
        </div>
      </div>
    </section>
  );
};

export default LoginPage;
