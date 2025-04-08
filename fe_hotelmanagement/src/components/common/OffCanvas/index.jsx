import { useState } from 'react';
import Button from 'react-bootstrap/Button';
import Offcanvas from 'react-bootstrap/Offcanvas';

const CustomOffCanvas = ({show, setShow, result, children, header, placement}) => {
    
    const handleClose = () => setShow(false);
    
    return <>
        <Offcanvas show={show} onHide={handleClose} placement={placement} >
            <Offcanvas.Header closeButton>
            <hr/>
            <Offcanvas.Title className='fw-bold'>{header}</Offcanvas.Title>
            </Offcanvas.Header>
            <hr style={{width: "100%", border: "1px solid #ccc", margin : "0px"}}></hr>
            <Offcanvas.Body>
                {children}
            </Offcanvas.Body>
        </Offcanvas>
    </>
}

export default CustomOffCanvas