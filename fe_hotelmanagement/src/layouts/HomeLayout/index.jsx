import { Outlet } from "react-router-dom";
import  Header  from "../../components/common/Header/index";
import Footer from "../../components/common/Footer/index";
import './style.scss'

const HomeLayout = () => {
  return (
    <div className="home-layout">
      <Header />
        <Outlet />
      <Footer />
    </div>
  );
}

export default HomeLayout;