import React, { useEffect, useState } from "react";
import './style.scss';
import { useLocation, useNavigate } from "react-router-dom";
import { findRoomInHotel } from "../../../services/HotelService/findHotelService";
import Paginate from "../../common/paging";
import {LIMIT} from "../../../utils/paging"
import HotelCard from "../HotelCard/index.jsx"

const HotelComponent = ({setHotelCount}) => {
    const fileUrl = 'files/image'
    const location = useLocation();
    const searchParams = new URLSearchParams(location.search);
    const navigator = useNavigate()

    const checkIn = searchParams.get("checkIn");
    const checkOut = searchParams.get("checkOut");
    const numAdults = searchParams.get("numAdults");
    const numRooms = searchParams.get("numRooms");

    // paging 
    const [currentPage, setCurrentPage] = useState(0)
    const [pageCount, setPageCount] = useState(0)

    const [hotels, setHotels] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    const fetchHotels = async (page, limit) => {
        try {
            const data = await findRoomInHotel(checkIn, checkOut, numAdults, numRooms, page, limit);
            if (data && data.code && data.code === 200 && data.result) {

                console.log(data)

                setPageCount(data.result.totalPages)
                setHotelCount(data.result.totalElements)
                setHotels(data.result.content)
            }


        } catch (error) {
            setError("Có lỗi xảy ra khi tải dữ liệu khách sạn.");
        } finally {
            setLoading(false);
        }
    }

    useEffect(() => {
        fetchHotels(0,LIMIT);
    }, [checkIn, checkOut, numAdults, numRooms]);

    if (loading) {
        return <p>Đang tải danh sách khách sạn...</p>;
    }

    if (error) {
        return <p>{error}</p>;
    }

    return (
        <>
            <section className="hotel-list">
                {hotels && hotels.length > 0 ? (
                    hotels.map((hotel) => (
                        <HotelCard hotel={hotel} />
                    ))
                ) : (
                    <p>Không tìm thấy khách sạn phù hợp.</p>
                )}

                <div className="d-flex align-items-center justify-content-center"><Paginate fetchFunction = {fetchHotels}  itemsPerPage = {LIMIT} pageCount = {pageCount} currentPage = {currentPage} /></div>
                
            </section>
        </>
    );
};

export default HotelComponent;
