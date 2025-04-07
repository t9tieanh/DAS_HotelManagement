export const formatDate = (dateString) => {
    if (!dateString) return '';
  
    const date = new Date(dateString);
    const weekday = new Intl.DateTimeFormat('vi-VN', { weekday: 'short' }).format(date);
    const day = date.getDate();
    const month = date.getMonth() + 1;
  
    return `${weekday}, ${day} tháng ${month}`;
  };

export const calculateNightsAndDays = (checkInDate, checkOutDate) => {
  const checkIn = new Date(checkInDate);
  const checkOut = new Date(checkOutDate);

  const diffTime = checkOut.getTime() - checkIn.getTime();

  const diffDays = Math.ceil(diffTime / (1000 * 60 * 60 * 24));
  
  if (diffDays <= 0) return { days: 0, nights: 0 };

  return {
    days: diffDays,
    nights: diffDays - 1,
  };
};
  