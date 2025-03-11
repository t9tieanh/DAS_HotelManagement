import { Outlet } from "react-router-dom";
import  Header  from "../../components/common/Header/index";
import Footer from "../../components/common/Footer/index";

const HomeLayout = () => {
  return (
    <div>
      <Header />
        <Outlet />
      <Footer />
    </div>
  );
}

export default HomeLayout;