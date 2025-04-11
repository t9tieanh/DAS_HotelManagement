import React, { useEffect, useState } from "react";
import { Modal, Button, Container, Row, Col } from "react-bootstrap";
import Tab from 'react-bootstrap/Tab';
import Tabs from 'react-bootstrap/Tabs';
import { FaCamera } from "react-icons/fa6";
import { getHotelImages } from "../../../../services/HotelService/hotelService";
import './style.scss'
import { BASE_URL } from "../../../../conf/baseUrl";


const TabDetail = ({keyParent,hotelId,imageType}) => {

    const [imgs, setImgs] = useState([])
    const [hasFetched, setHasFetched] = useState(false)
    const fileUrl = 'files/image'
    const BASE_URLL = BASE_URL

    const handleImageOnClick = (e) => {
        window.open(e.target.src, "_blank");
    }

    const fetchHotelImages = async () => {
        const data = await getHotelImages(hotelId,imageType)

        if (data && data.code && data.code === 200 && data.result && data.result.imgs) {
          setImgs(data.result.imgs)
          setHasFetched(true)
        }
    }

    useEffect(
        () => {
            if (keyParent === imageType && !hasFetched && hotelId) {
              fetchHotelImages();
            }
        }
        , [keyParent, hotelId, imageType, hasFetched]
    )

    

    return (
        <>
        <Container className="image-gallery-container">
            <Row>
              {imgs?.map((img, index) => (
                <Col key={index} xs={12} md={6} lg={3} className="mb-3">
                  <img onClick={(e) => {handleImageOnClick(e)}} src={`${BASE_URLL}/${fileUrl}/${img}`} alt={`Gallery ${index}`} className="img-fluid rounded" />
                </Col>
              ))}
            </Row>
          </Container>
        </>
    )
}


const ImageGallery = ({show, setShow, imgCategorys, hotelId}) => {
  const handleClose = () => setShow(false);
  const handleShow = () => setShow(true);
  const [key, setKey] = useState();

  const handleTabSelect = (selectedKey) => {
    setKey(selectedKey);
    console.log('Tab hiện tại:', selectedKey); 
  };

  useEffect(() => {
    if (imgCategorys && imgCategorys.length > 0) {
      const firstTab = `${imgCategorys[0].imageType}`;
      setKey(firstTab);
    }
  }, [imgCategorys]);

  return (
    <div>
      <Modal show={show} onHide={handleClose} size="xl">
        <Modal.Header closeButton>
          <Modal.Title>
          <div className="d-flex flex-column">
            <h5 className="d-flex align-items-center gap-1">
              <FaCamera /> Thư Viện Ảnh
            </h5>
            <p className="mb-0 text-muted" style={{ fontSize: '14px' }}>
              Khám phá kho lưu trữ ảnh của khách sạn này!
            </p>
          </div>
          </Modal.Title>
        </Modal.Header>
        <Modal.Body>
            <Tabs
            id="controlled-tab-example"
            activeKey={key}
            onSelect={handleTabSelect}
            className="mb-3"
            >
              {imgCategorys?.map(({ imageType, count }) => (
                <Tab eventKey={imageType} title={`${imageType} (${count})`} key={imageType}>
                  <TabDetail keyParent={key} imageType={imageType} hotelId = {hotelId} count={count} />
                </Tab>
              ))}
        </Tabs>
        </Modal.Body>
      </Modal>
    </div>
  );
};

export default ImageGallery;
