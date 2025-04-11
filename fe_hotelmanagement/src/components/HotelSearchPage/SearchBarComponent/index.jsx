import React, { useState, useRef} from "react";
import './style.scss';
import {FaUser, FaSearch } from 'react-icons/fa'; // Icons từ react-icons
import TextInput from "../../common/Input/Input1";
import { FaLocationDot } from "react-icons/fa6";
import { MdTipsAndUpdates } from "react-icons/md";
import '@wojtekmaj/react-daterange-picker/dist/DateRangePicker.css';
import 'react-calendar/dist/Calendar.css';
import DateRangePickerComponent from "../../common/DateRangePicker";
import PrimaryButton from "../../common/button/btn-primary";
import Overlay from "../../common/Overlay";
import { Button, Form, InputGroup, Row, Col } from "react-bootstrap";
import { RiHotelBedFill } from "react-icons/ri"
import { FaChildren } from "react-icons/fa6";
import { MdDownloadDone } from "react-icons/md";
import { LIMIT } from "../../../utils/paging";
import LocationSearch from "./LocationSearch";


export const CapacitySearch = ({adults, setAdults, rooms, setRooms, setShow}) => {

  const Counter = ({ label, count, setCount }) => (
    <InputGroup className="align-items-center mb-3 capacity-search-item">
      <InputGroup.Text className="bg-white border-0">{label}</InputGroup.Text>
      <Button variant="outline-light" size="sm" style={{color : "black"}} onClick={() => setCount(Math.max(0, count - 1))}>
        –
      </Button>
      <Form.Control
        type="text"
        value={count}
        readOnly
        className="bg-light text-center"
        style={{ maxWidth: "50px" }}
        size="sm"
      />
      <Button variant="outline-light" style={{color : "black"}} size="sm" onClick={() => setCount(count + 1)}>
        +
      </Button>
    </InputGroup>
  );

  return (
    <div className="p-1 rounded bg-white" style={{ width: "fit-content" }}>
      <Counter label={<><FaChildren />&nbsp; Người lớn</>} count={adults} setCount={setAdults} />
      <Counter label={<><RiHotelBedFill />&nbsp; Phòng</>} count={rooms} setCount={setRooms} />

      <p className="mt-3 mb-1 fw-bold">Xin vui lòng nhập chính xác</p>
      <p style={{ fontSize: "small", marginTop: "-10px" }}>
        Vui lòng nhập đúng số người và phòng để chúng tôi tìm phòng phù hợp nhất cho bạn.
      </p>

      <PrimaryButton onClickFunc={() => {setShow(false)}} text={<><MdDownloadDone />Xong</>} className={'mt-1 w-100'} />
    </div>
  );
}


const SearchBarComponent = ({dateRange, setDateRange, adults, rooms, setAdults, setRooms, setLocation, location, fetchHotels, }) => { 

  const [show, setShow] = useState(false);
  const [target, setTarget] = useState(null);
  const ref = useRef(null);

  // xử lý sự kiện chọn ngày

  const selectDate = (date) => {
    setDateRange(date);
    console.log(date);
  }

  // cho phần chọn người lớn, trẻ em, phòng
  const capacityRef = useRef(null);
  const [capacityTarget, setCapacityTarget] = useState(null);
  const [capacityShow, setCapacityShow] = useState(false);

  const handleClickCapacity = (event) => { 
    setCapacityShow(!capacityShow);
    setCapacityTarget(event.target);
  };

  // xử lý cho sự kiện search location
  const handleLocationSearch = (event) => {
    setShow(true);
    const value = event.target.value;
    setLocation(value);

    setTarget(event.target);
  };

  // người dùng tiến hành chọn location 
  const handleSelectLocation = (location) => {
    setLocation(location)
    setShow(false);
  }

  return (
    <>
      <div className="search-bar">
        <div className="search-bar-item" ref={ref} style={{backgroundColor: 'white'}}>
            <TextInput style={{ border: 'none !important' }}  name={<><FaLocationDot /> Nhập địa chỉ</>} text={location} onChangeFunc={(e) => {handleLocationSearch(e)}} className="search-bar-input shadow-1 border-0" />

            <Overlay show={show} target={target} ref={ref} 
              children={<LocationSearch handleSelectLocation = {handleSelectLocation} textSearch = {location} />}
              header={<><MdTipsAndUpdates />&nbsp;Nhập thêm ký tự để tìm vị trí chính xác hơn</>}
            />
          
        </div>

        <div className="search-bar-item">
          <DateRangePickerComponent date={dateRange} setDate={setDateRange} />
        </div>

        <div className="search-bar-item bg-light shadow-2 p-2">
          <div ref={capacityRef} className="bg-light" onClick={(e) => {handleClickCapacity(e)}}>
            <FaUser />&nbsp;
            <span>{adults} người lớn, {rooms} phòng</span>
          </div>

          <Overlay show={capacityShow} target={capacityTarget} ref={capacityRef} 
              children={<CapacitySearch adults = {adults} setAdults = {setAdults} 
                  rooms = {rooms} setRooms = {setRooms} setShow={setCapacityShow}  />}
              header={<><MdTipsAndUpdates />&nbsp;Điền thông tin khách và phòng</>}
          />
          
        </div>

        {/*  button search */}
        <PrimaryButton type="button"  className="search-bar-button" text={'Tìm khách sạn'} icon={<FaSearch />} onClickFunc={() => {fetchHotels(0, LIMIT)}} />
        
      </div>
    </>
  );
};

export default SearchBarComponent;
