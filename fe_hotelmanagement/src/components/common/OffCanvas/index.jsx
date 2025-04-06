import { useState } from 'react';
import Button from 'react-bootstrap/Button';
import Offcanvas from 'react-bootstrap/Offcanvas';

const CustomOffCanvas = ({show, setShow, result, children, header}) => {
    
    const handleClose = () => setShow(false);
    
    return <>
        <Offcanvas show={show} onHide={handleClose}>
            <Offcanvas.Header closeButton>
            <Offcanvas.Title className='fw-bold'>{header}</Offcanvas.Title>
            </Offcanvas.Header>
            <Offcanvas.Body>
                {children}
            </Offcanvas.Body>
        </Offcanvas>
    </>
}

export default CustomOffCanvas