import { useState } from 'react';
import Button from 'react-bootstrap/Button';
import Modal from 'react-bootstrap/Modal';
import { VscDebugContinue } from "react-icons/vsc";
import { FaLocationArrow } from "react-icons/fa";

function CustomModal({show, setShow, title, content, icon, size, btnClose}) {

  const handleClose = () => setShow(false);

  return (
    <>
      <Modal size={size ? size : 'xl'}
      aria-labelledby="contained-modal-title-vcenter"
      centered show={show} onHide={handleClose}>
        <Modal.Header closeButton>
          <Modal.Title><h5 className='d-flex align-items-center gap-1'>{icon}{title}</h5></Modal.Title>
        </Modal.Header>
        <Modal.Body><span className='text-muted'>{content}</span></Modal.Body>
        <Modal.Footer>
            {btnClose ? btnClose :
              <Button onClick={handleClose} className='d-flex align-items-center gap-2' variant="primary">Khám phá thêm<FaLocationArrow /></Button>}
        </Modal.Footer>
      </Modal>
    </>
  );
}

export default CustomModal;