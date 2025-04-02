import { Outlet } from "react-router-dom";
import  Header  from "../../components/common/HeaderPaymentLayout/index";
import Footer from "../../components/common/Footer/index";
import './style.scss'
import { Container } from "react-bootstrap";
import PaymentContext from "../../context/PaymentContext";
import { useState } from "react";

const PaymentLayout = () => {

    const [pageState, setPageState] = useState(0)

    return (
        <>  
            <Header pageState = {pageState} />

            <Container>
                    <PaymentContext.Provider value={{ pageState, setPageState }}>
                        <Outlet />
                    </PaymentContext.Provider>
            </Container>
        
            <Footer />
        </>
    )
}

export default PaymentLayout