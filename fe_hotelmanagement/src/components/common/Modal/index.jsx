import { useState } from 'react';
import Button from 'react-bootstrap/Button';
import Modal from 'react-bootstrap/Modal';

function CustomModal({show, setShow, title, content, icon}) {

  const handleClose = () => setShow(false);

  return (
    <>
      <Modal size="lg"
      aria-labelledby="contained-modal-title-vcenter"
      centered show={show} onHide={handleClose}>
        <Modal.Header closeButton>
          <Modal.Title><h5 className='d-flex align-items-center gap-1'>{icon}{title}</h5></Modal.Title>
        </Modal.Header>
        <Modal.Body><span className='text-muted'>{content}</span></Modal.Body>
        <Modal.Footer>
            <Button onClick={handleClose} variant="primary">Khám phá thêm</Button>
        </Modal.Footer>
      </Modal>
    </>
  );
}

export default CustomModal;