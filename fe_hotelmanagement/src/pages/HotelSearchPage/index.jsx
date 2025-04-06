import React, { useState, useEffect } from "react";
import "./style.scss";
import SearchBar from "../../components/HotelSearchPage/SearchBarComponent";
import FilterBar from "../../components/HotelSearchPage/FilterBarComponent";
import HotelComponent from "../../components/HotelSearchPage/HotelComponent";
import HeaderComponent from "../../components/HotelSearchPage/HeaderComponent"
import { LIMIT } from "../../utils/paging"
import { useLocation } from "react-router-dom";
import { findRoomInHotel } from "../../services/HotelService/findHotelService";


const HotelSearchPage = () => {

  const [hotelCount, setHotelCount] = useState(0)

  const [location, setLocation] = useState(useLocation());
  const searchParams = new URLSearchParams(location.search);

  const getSearchParam = (param, defaultValue) => {
    const value = searchParams.get(param);
    return value ? value : defaultValue;
  };

  const getSearchDate = (param, defaultValue) => {
    const value = searchParams.get(param);
    return value ? new Date(value) : defaultValue;
  };

  const [dateRange, setDateRange] = useState([
    getSearchDate('checkIn', new Date()),
    getSearchDate('checkOut', new Date()),
  ]);


  const [locationValue, setLocationValue] = useState(getSearchParam("location", "Thành phố Hồ Chí Minh"));
  const checkIn = dateRange[0];
  const checkOut = dateRange[1];
  const [numAdults, setNumAdults] = useState(Number(getSearchParam("numAdults", 1)));
  const [numRooms, setNumRooms] = useState(Number(getSearchParam("numRooms", 1)));

  // paging 
  const [currentPage, setCurrentPage] = useState(0)
  const [pageCount, setPageCount] = useState(0)

  const [hotels, setHotels] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  const fetchHotels = async (page, limit) => {
    try {

      const data = await findRoomInHotel(checkIn.toISOString().split('T')[0], checkOut.toISOString().split('T')[0],
        numAdults, numRooms, page, limit);
      if (data && data.code && data.code === 200 && data.result) {
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
    fetchHotels(0, LIMIT);
  }, [checkIn, checkOut, numAdults, numRooms]);

  return (
    <div className="booking-page">

      <SearchBar adults={numAdults} rooms={numRooms} dateRange={dateRange} setDateRange={setDateRange}
        setAdults={setNumAdults} setRooms={setNumRooms}
        setLocation={setLocationValue} location={locationValue} fetchHotels={fetchHotels} />

      <HeaderComponent count={hotelCount} />

      <div className="main-content">
        <FilterBar />
        <HotelComponent setHotelCount={setHotelCount} loading={loading} error={error}
          hotels={hotels} fetchHotels={fetchHotels}
          pageCount={pageCount} currentPage={currentPage}
          adults={numAdults} rooms={numRooms} dateRange={dateRange} setDateRange={setDateRange}
          setAdults={setNumAdults} setRooms={setNumRooms}
        />
      </div>
    </div>
  );
};

export default HotelSearchPage;