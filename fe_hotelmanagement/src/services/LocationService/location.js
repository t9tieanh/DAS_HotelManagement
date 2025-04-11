import axios from "../../utils/CustomAxios";

export const getLocations = async () => {
  return await axios.get('/get-districts');
};