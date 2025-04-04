import React, { useState, useRef } from "react";
import './style.scss';
import { Form, Dropdown, DropdownButton, ListGroup } from 'react-bootstrap';
import Autosuggest from 'react-autosuggest'; // Import Autosuggest
import { FaMapMarkerAlt, FaCalendarAlt, FaUser, FaSearch } from 'react-icons/fa'; // Icons từ react-icons
import Button from 'react-bootstrap/Button';
import Overlay from 'react-bootstrap/Overlay';
import Popover from 'react-bootstrap/Popover';
import TextInput from "../../common/Input";
import { FaLocationDot } from "react-icons/fa6";
import { MdTipsAndUpdates } from "react-icons/md";
import { IoArrowForwardCircleOutline } from "react-icons/io5";


const SearchBarComponent = () => {  

  const [show, setShow] = useState(false);
  const [target, setTarget] = useState(null);
  const ref = useRef(null);

  const handleClick = (event) => {
    setLocation(event.target.value); 

    setShow(!show);
    setTarget(event.target);
  };

  const handleSelectLocation = (location) => {
    setLocation('Thủ đức')
    setShow(false);
  }

  const [location, setLocation] = useState(''); 

  return (
    <>
      <div className="search-bar">
        <div className="search-bar-item" ref={ref} style={{backgroundColor: 'white'}}>
            <TextInput  name={<><FaLocationDot /> Nhập địa chỉ</>} text={location} onChangeFunc={handleClick} className="search-bar-input" />

            <Overlay
              show={show}
              target={target}
              placement="bottom"
              container={ref}
              containerPadding={0}
            >
              <Popover id="popover-contained">
                <Popover.Header as="h5"><h6 className="fw-bold"><MdTipsAndUpdates />Nhập thêm ký tự để tìm vị trí chính xác hơn</h6></Popover.Header>
                <Popover.Body>
                  <div className="card mb-2">
                    <div className="card-body">
                      <div className="d-flex justify-content-between">
                        <div className="d-flex flex-row align-items-center">
                          <div>
                            <img
                              src="https://cdn2.tuoitre.vn/zoom/700_700/471584752817336320/2025/3/31/may-bay-vna-1743420544268421408724-152-0-1103-1816-crop-17434206246321986502898.png"
                              className="img-fluid rounded-3" alt="Shopping item" style={{width: '50px'}}/>
                          </div>
                          <div className="ms-3">
                            <h6>Thủ đức, TP Hồ Chí Minh</h6>
                            <p className="small mb-0">Việt Nam</p>
                          </div>
                        </div>
                        <div className="d-flex flex-row align-items-center">
                          <a href="#!" onClick={() => {handleSelectLocation('Thủ đức')}} ><IoArrowForwardCircleOutline size={28} /></a>
                        </div>
                      </div>
                    </div>
                  </div>

                  <div className="card mb-1">
                    <div className="card-body">
                      <div className="d-flex justify-content-between">
                        <div className="d-flex flex-row align-items-center">
                          <div>
                            <img
                              src="https://cdn2.tuoitre.vn/zoom/700_700/471584752817336320/2025/3/31/may-bay-vna-1743420544268421408724-152-0-1103-1816-crop-17434206246321986502898.png"
                              className="img-fluid rounded-3" alt="Shopping item" style={{width: '50px'}}/>
                          </div>
                          <div className="ms-3">
                            <h6>Thủ đức, TP Hồ Chí Minh</h6>
                            <p className="small mb-0">Việt Nam</p>
                          </div>
                        </div>
                        <div className="d-flex flex-row align-items-center">
                          <a href="#!" onClick={() => {handleSelectLocation('Thủ đức')}} ><IoArrowForwardCircleOutline size={28} /></a>
                        </div>
                      </div>
                    </div>
                  </div>
                </Popover.Body>
              </Popover>
            </Overlay>
        </div>

        <div className="search-bar-item">
          <FaCalendarAlt />
          <span>13 thg 3 - 14 thg 3, 1 đêm</span>
        </div>

        <div className="search-bar-item">
          <FaUser />
          <span>1 người lớn, 0 Trẻ em, 1 phòng</span>
        </div>

        <Button variant="primary" className="search-bar-button">
          <FaSearch />
          Tìm khách sạn
        </Button>
      </div>
    </>
  );
};

export default SearchBarComponent;
