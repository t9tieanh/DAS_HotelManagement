import { useState } from 'react';
import Button from 'react-bootstrap/Button';
import Modal from 'react-bootstrap/Modal';

function CustomModal({show, setShow, title, content}) {

  const handleClose = () => setShow(false);

  return (
    <>
      <Modal size="lg"
      aria-labelledby="contained-modal-title-vcenter"
      centered show={show} onHide={handleClose}>
        <Modal.Header closeButton>
          <Modal.Title><h5>{title}</h5></Modal.Title>
        </Modal.Header>
        <Modal.Body><h6 className='text-muted'>{content}</h6></Modal.Body>
        <Modal.Footer>
            <Button onClick={handleClose} variant="primary">Khám phá thêm</Button>
        </Modal.Footer>
      </Modal>
    </>
  );
}

export default CustomModal;