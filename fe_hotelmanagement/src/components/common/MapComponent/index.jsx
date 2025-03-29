import React, { useEffect, useState } from "react";
import axios from "axios";

const MapIframe = ({locationStr}) => {
  const [mapUrl, setMapUrl] = useState(
    "https://www.google.com/maps?q=16.0659,108.2022&hl=vi&z=14&output=embed"
  ); 

  useEffect (
    () => {
        fetchCoordinates(locationStr)
    },[]
  )

  const fetchCoordinates = async (place) => {
    try {
      const response = await axios.get("https://nominatim.openstreetmap.org/search", {
        params: { q: place, format: "json" },
      });

      if (response.data.length > 0) {
        const latitude = response.data[0].lat;
        const longitude = response.data[0].lon;
        const newMapUrl = `https://www.google.com/maps?q=${latitude},${longitude}&hl=vi&z=14&output=embed`;

        setMapUrl(newMapUrl);
      } else {
        console.error("Không tìm thấy địa điểm!");
      }
    } catch (error) {
      console.error("Lỗi khi lấy tọa độ:", error);
    }
  };

  return (
    <div>
      <iframe
        src={mapUrl}
        width="100%"
        height="100%"
        style={{ border: "0", marginTop: "10px" }}
        allowFullScreen
        loading="lazy"
        referrerPolicy="no-referrer-when-downgrade"
        title="Google Map"
      ></iframe>
    </div>
  );
};

export default MapIframe;
