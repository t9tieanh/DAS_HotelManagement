import { MapContainer } from "react-leaflet"
import CustomCard from "../../../common/Card"
import './style.scss'
import MapIframe from "../../../common/MapComponent"
import { Container, Row, Col, Card } from "react-bootstrap";
import { FaSnowflake, FaUtensils, FaConciergeBell, FaParking, FaWifi, FaBuilding } from "react-icons/fa";
import CustomModal from "../../../common/Modal";
import { useState } from "react";
import { FaServicestack } from "react-icons/fa6";
import _ from "lodash";
import { GoDotFill } from "react-icons/go";
import Image from 'react-bootstrap/Image';
import HotelName from "../../NameComponent";


const HotelLocationDetailBody = ({facilities}) => {

    const [facilitiesUpdated, setFacilitiesUpdated] = useState(_.groupBy(facilities, "category"))

    return <>
      <Container className="shadow-0">

      <div style={{ padding : "10px"}}>
          <Row>
            <div className = "background-img">
                <h1>Các tiện nghi có sẵn của khách sạn</h1>
            </div>

            {Object?.entries(facilitiesUpdated)?.map(([icon,facilitys]) => (
                <Col md={6} sm={6} xs={12} className="mb-4">
                    <Card className="card-facilities-detail shadow-0">
                    <Card.Header className="d-flex align-items-center gap-1">
                        <img importance="low" loading="lazy" decoding="async" width="24" height="24" src={icon}></img>
                        <Card.Title><h6 className="fw-bold">{facilitys[0]?.categoryName}</h6></Card.Title>
                    </Card.Header>
                    <Card.Body>
                        <ul className="list-unstyled p-2">
                        {facilitys?.map((item, idx) => (
                            <li key={idx} className="mb-2 fw-normal">
                            <h6><GoDotFill /> {item.name}</h6>
                            </li>
                        ))}
                        </ul>
                    </Card.Body>
                    </Card>
                </Col>
            ))}
          </Row>
      </div>
      </Container>
  </>
}

const HotelLocationBody = ({facilities}) => {

      return <>
        <Container>
        <div style={{ padding : "10px"}}>
            <Row>
            {facilities?.map((item,index) => (
                <Col key={index} md={4} className="mb-4">
                <Card className="shadow-0">
                    <Card.Body>
                        <img importance="low" loading="lazy" decoding="async" width="24" height="24"  src={item.category}></img>
                        <span className="ms-2">{item.name}</span>    
                    </Card.Body>
                </Card>
                </Col>
            ))}
            </Row>
        </div>
        </Container>
    </>
}

const HotelFacility = ({facilities}) => {

    const [show, setShow] = useState(false)
    
    const handleShowDescription = () => {
        setShow(true)
    }

    return (
        <CustomCard 
            name="Các tiện ích của khách sạn" 
            subTitle="Danh sách các tiện ích chính mà khách sạn cung cấp" 
            buttonFunc={facilities?.length > 6 ? handleShowDescription : undefined} 
            className="col-8 facility-component"
        >
            <HotelLocationBody facilities={facilities?.slice(0, 6)} />
            
            <CustomModal show={show} setShow={setShow} icon={<FaServicestack />} 
                title={`Các tiện ích của khách sạn này`} 
                subtitle={`Tìm hiểu thêm thông tin chi tiết về các tiện ích mà khách sạn này cung cấp!`}
                content={<HotelLocationDetailBody facilities={facilities} />} 
            />
        </CustomCard>
    );
};

export default HotelFacility