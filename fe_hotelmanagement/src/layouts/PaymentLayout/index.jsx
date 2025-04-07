import { Outlet, useLocation } from "react-router-dom";
import Header from "../../components/common/HeaderPaymentLayout/index";
import Footer from "../../components/common/Footer/index";
import './style.scss'
import { Container } from "react-bootstrap";
import PaymentContext from "../../context/PaymentContext";
import { useState } from "react";
import { useSelector } from "react-redux";

const PaymentLayout = () => {

    const location = useLocation();

    const room = location.state || null;

    console.log("roomData", room)

    const [pageState, setPageState] = useState(0)
    const expireDateTime = useSelector(state => state.reservation.expireDateTime)

    return (
        <>
            <Header expireDateTime={expireDateTime} pageState={pageState} />

            <Container>
                <PaymentContext.Provider value={{ pageState, setPageState, room }}>
                    <Outlet />
                </PaymentContext.Provider>
            </Container>

            <Footer />
        </>
    )
}

export default PaymentLayout