import React, { useState, useRef, useEffect } from "react";
import { IoArrowForwardCircleOutline } from "react-icons/io5";
import '@wojtekmaj/react-daterange-picker/dist/DateRangePicker.css';
import 'react-calendar/dist/Calendar.css';
import { debounce } from 'lodash';
import { useCallback } from 'react'
import searchItemlogo from '../../../../assets/img/search-item.webp'
import './style.scss'
import { toast } from "react-toastify";
import { getLocations } from "../../../../services/LocationService/location";

const LocationSearch = ({ handleSelectLocation, textSearch }) => {
    const [resultSearch, setResultSearch] = useState([]);
    const [locationsData, setLocationsData] = useState([]);
  
    useEffect(() => {
      const fetchLocations = async () => {
        const data = await getLocations();
        if (data && data.code === 200 && data.result) {
          setLocationsData(data.result);
        } else if (data.response && data.response.data) {
          toast.error(data.response.data.message);
        } else {
          toast.error(data?.message);
        }
      };
  
      fetchLocations();
    }, []);
  
    // Debounced filter function (chỉ khởi tạo một lần)
    const debouncedFilter = useCallback(
      debounce((value, data) => {
        const result = data.filter(district =>
          district.toLowerCase().includes(value.toLowerCase())
        );
        setResultSearch(result); 
      }, 300),
      []
    );
  
    useEffect(() => {
      if (textSearch && locationsData.length > 0) {
        debouncedFilter(textSearch, locationsData);
      } else {
        setResultSearch([]);
      }
    }, [textSearch, locationsData, debouncedFilter]);
  
    return (
      <div className="location-search" style={{ maxHeight: "300px", overflowY: "auto" }}>
        {resultSearch.length > 0 ? (
          resultSearch.map((location, index) => (
            <div className="card mb-1 shadow-0" key={index}>
              <div className="card-body">
                <div className="d-flex justify-content-between">
                  <div className="d-flex flex-row align-items-center">
                    <div>
                      <img
                        src={searchItemlogo}
                        className="img-fluid rounded-3"
                        alt="location"
                        style={{ width: "50px" }}
                      />
                    </div>
                    <div className="ms-3">
                      <h6>{location}</h6>
                      <p className="small mb-0">Việt Nam</p>
                    </div>
                  </div>
                  <div className="d-flex flex-row align-items-center">
                    <a href="#!" onClick={() => handleSelectLocation(location)}>
                      <IoArrowForwardCircleOutline size={28} />
                    </a>
                  </div>
                </div>
              </div>
            </div>
          ))
        ) : (
          <div className="text-center text-muted p-2">Không tìm thấy địa chỉ nào phù hợp!</div>
        )}
      </div>
    );
};

export default LocationSearch;
  