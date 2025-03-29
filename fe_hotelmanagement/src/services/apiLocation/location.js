import axios from "axios";

export const getCoordinates = async (location) => {
  try {
    const response = await axios.get("https://nominatim.openstreetmap.org/search", {
      params: {
        q: location,
        format: "json",
      },
    });

    if (response.data.length > 0) {
      const latitude = response.data[0].lat;
      const longitude = response.data[0].lon;

      return { latitude, longitude };
    } else {
      throw new Error("Không tìm thấy tọa độ!");
    }
  } catch (error) {
    console.error("Lỗi khi lấy tọa độ:", error);
    return null;
  }
};