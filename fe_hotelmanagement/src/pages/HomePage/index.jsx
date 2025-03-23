import BookingForm from "../../components/HomePage/bookingform/index";
import AboutUs from "../../components/HomePage/aboutUs";
import ServiceSection from "../../components/HomePage/serviceSection";
import HomeRoom from "../../components/HomePage/homeRoom";
import BlogSection from "../../components/HomePage/blogs";

const HomePage = () => {

    return (
        <>
            <BookingForm />
            <AboutUs />
            <HomeRoom/>
            <ServiceSection/>
            <BlogSection/>
        </>
    );
};

export default HomePage;
