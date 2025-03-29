import { Outlet } from "react-router-dom";
import  Header  from "../../components/common/Header/index";
import Footer from "../../components/common/Footer/index";
import './style.scss'

const HomeLayout = () => {
  return (
    <div className="home-layout">
      <Header />
      <div style={{backgroundColor : "rgb(253, 251, 251)"}}>
        <Outlet />
      </div>
      <Footer />
    </div>
  );
}

export default HomeLayout;