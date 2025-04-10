import React, { useState } from "react";
import './style.scss'
import ArrowButton from '../../common/button/button-arrow/index.jsx'
import Tag from "../../common/tag/index.jsx";
import { BASE_URL } from "../../../conf/baseUrl.js";
import PrimaryButton from "../../common/button/btn-primary/index.jsx";
import { FaArrowAltCircleRight } from "react-icons/fa";

import { useDispatch, useSelector } from "react-redux";
import { doCreateReservation } from "../../../redux/action/reservationAction.js";
import { toast } from "react-toastify";
import { useNavigate } from "react-router-dom";
import { createReservation } from "../../../services/ReservationService/reservationService.js";
import CustomOffCanvas from "../../common/OffCanvas/index.jsx";
import { FaLocationArrow } from "react-icons/fa";
import Card from 'react-bootstrap/Card';
import { formatCurrency } from "../../../utils/Format/CurrencyFormat.js";
import { Alert, Button, ButtonGroup, Container } from 'react-bootstrap';
import { cancelReservation } from "../../../services/ReservationService/reservationService.js";
import { doDeleteReservation } from "../../../redux/action/reservationAction.js";
import { IoArrowBackCircleSharp } from "react-icons/io5";
import { FaArrowCircleRight } from "react-icons/fa";
import { MdError } from "react-icons/md";
import Countdown from "react-countdown";

const IncompleteReservationNotice = ({ handleBooking }) => {
    const navigator = useNavigate()
    const dispatch = useDispatch()
    const reservationId = useSelector(state => state.reservation.reservationId)
    const expireDateTime = useSelector(state => state.reservation.expireDateTime)

    const handleCreateNewBooking = async() => {
        const data = await cancelReservation(reservationId)

        if (data && data.code && data.code === 200 && data.result) {
            toast.success("Hủy đặt phòng thành công !")
        } else if (data.response && data.response.data) {
            toast.error(data.response.data.message)
            return
        }
        else toast.error(data?.message)

        dispatch(doDeleteReservation()) // xóa thông tin reservation cũ 
        handleBooking() // tiến hành đặt phòng mới
    }

    return (
      <Container className="my-3 mb-2">
        <Alert variant="danger" className="d-flex justify-content-between align-items-center">
          <div>
            <strong><MdError />Lưu ý: Bạn vẫn còn giao dịch đặt phòng chưa hoàn tất.</strong>
            <br/>
            <p>Thời gian còn lại:  <Countdown date={expireDateTime} /></p> 
          </div>
          <ButtonGroup>
            <PrimaryButton text={<><IoArrowBackCircleSharp size={20} />&nbsp;Tiếp tục đặt phòng trước</>} className={'bg-light text-dark'} onClickFunc={() => {navigator('/reservation')}}>
            </PrimaryButton>
            <PrimaryButton text={<>Hủy giao dịch trước và đặt phòng này&nbsp;<FaLocationArrow size={15} /></>} onClickFunc={handleCreateNewBooking}>
            </PrimaryButton>
          </ButtonGroup>
        </Alert>
      </Container>
    );
};

const ConfirmBooking = ({show, setShow, handleBooking, room}) => {
    const fileUrl = 'files/image'

    return (
        <>
            <p className="fst-italic">Bạn có chắc chắn muốn đặt phòng này không?</p>
            <div>
                <Card className="shadow-0">
                    <Card.Img variant="top" src={`${BASE_URL}/${fileUrl}/${room?.avatar}`} />
                    <Card.Body>
                        <Card.Title><h5 className="fw-semibold">{room?.name}</h5></Card.Title>
                        <Card.Text>
                        <h6>{room?.description}</h6>
                        </Card.Text>
                    </Card.Body>
                    <div className="d-flex justify-content-between">
                        <div><PrimaryButton text={'Đặt phòng ngay'} icon={<FaLocationArrow />} className={'ml-3 mb-2'} onClickFunc={handleBooking} /></div>
                        <div><PrimaryButton text={'Hủy'} className={'bg-light text-dark'} onClickFunc={() => {setShow(false)}} /></div>
                    </div>
                </Card>
            </div>
            <div className="d-flex justify-content-center gap-3 mt-3">
            </div>
        </>
    )
}


const RoomSection = ({ rooms, checkIn, checkOut}) => {
    const fileUrl = 'files/image'
    const isReservation = useSelector(state => state.reservation.reservationId)
    const expireDateTime = useSelector(state => state.reservation.expireDateTime)

    const isAuthentication = useSelector(state => state.user.isAuthentication)
    const navigator = useNavigate()
    const dispatch = useDispatch();

    // dùng xử lý sự kiện xác nhận đặt phòng 
    const [bookingRoomState, setBookingRoomState] = useState(false)
    const [roomSelected, setRoomSelected] = useState()

    // state trường hợp vẫn còn giao dịch đặt phòng chưa hoàn tất 
    const [isReserved, setIsReserved] = useState(
        (isReservation && expireDateTime >= new Date())
    )
    const [stateReserved, setStateReserved] = useState(false)


    // xử lý sự kiện click đặt phòng 
    const handleBookingRoom = async (room) => {
        if (isReserved) { 
            setStateReserved(true) // trường hợp vẫn còn giao dịch đặt phòng chưa hoàn tất 
        } else setBookingRoomState (true)

        setRoomSelected(room)
    }

    const handleBooking = async() => {
        const reservationDetails = [
            { roomId: roomSelected.id, quantity: 1 }
        ];

        if (!isAuthentication) {
            toast.error("Vui lòng đăng nhập để đặt phòng !")
            navigator('/login')
        }
        
        const data = await createReservation(checkIn, checkOut, reservationDetails)

        if (data && data.code && data.code === 200 && data.result) {
            dispatch(doCreateReservation(data.result))
            // lưu reservation id

            navigator('/reservation')
            toast.success("Đặt phòng thành công !")
        } else if (data.response && data.response.data) {
            toast.error(data.response.data.message)
            return
        }
        else toast.error(data?.message)
    }

    return (
        <div className="room-section-container">
            {rooms.length > 0 ? (
                rooms.map((room) => (
                    <div key={room.id} className="room-section m-2 shadow-5">
                        <div className="row g-0">
                            <div className="col-md-4">
                                <div className="room-image">
                                    <img
                                        src={`${BASE_URL}/${fileUrl}/${room.avatar}`}
                                        alt={room.name}
                                    />
                                    <ArrowButton text={'Xem chi tiết phòng'} style={{ fontSize: "15px", marginTop: "5px" }} />
                                </div>
                            </div>
                            <div className="col-md-8">
                                <div className="room-details">
                                    <h5>{room.name}</h5>
                                    <table>
                                        <tbody>
                                            <tr>
                                                <td>
                                                    <strong>{room.description}</strong><br />
                                                    <span className="text-muted">
                                                        {room.roomStatus === 1 ? "Có sẵn" : "Hết phòng"}
                                                    </span><br />
                                                    <i className="fas fa-bed"></i> {room.maxOccupation} người<br />
                                                    <span className="text-success">
                                                        {room.freeChildren > 0 ? `Miễn phí ${room.freeChildren} trẻ em` : "Không miễn phí trẻ em"}
                                                    </span>
                                                </td>
                                                <td className="text-center">
                                                    <i className="fas fa-user"></i> x{room.maxOccupation}
                                                </td>
                                                <td className="text-end">
                                                    <span className="price-new">{formatCurrency(room.price)} VND</span><br />
                                                    <small className="text-muted">Chưa bao gồm thuế và phí</small><br />
                                                    {room.roomStatus === 1 && <Tag text={'Đang có khuyến mãi'} />}
                                                </td>
                                                <td className="text-end">
                                                    <PrimaryButton text={'Đặt ngay'} onClickFunc={() => {handleBookingRoom(room)}} icon={<FaArrowAltCircleRight />} className={'select-btn'} />
                                                </td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                ))
            ) : (
                <p>Không có phòng nào.</p>
            )}
            <CustomOffCanvas show={bookingRoomState} setShow = {setBookingRoomState} 
                header={<h5 className="fw-bold">Hệ thống đặt phòng <span className="text-primary"><span className="text-warning">@H</span>otelas</span></h5>}
                children={<ConfirmBooking setShow={setBookingRoomState} handleBooking={handleBooking} room={roomSelected}
            />} />

            <CustomOffCanvas show={stateReserved} setShow = {setStateReserved} placement={'bottom'}
                header={<h5 className="fw-bold">Hệ thống đặt phòng <span className="text-primary"><span className="text-warning">@H</span>otelas</span></h5>}
                children={<IncompleteReservationNotice handleBooking = {handleBooking} />}
            />
        </div>
    );
};

export default RoomSection;
