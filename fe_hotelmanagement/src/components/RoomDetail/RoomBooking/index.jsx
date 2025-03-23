import { useState } from "react";

const RoomBooking = () => {
    const [checkIn, setCheckIn] = useState("");
    const [checkOut, setCheckOut] = useState("");
    const [guests, setGuests] = useState(3);
    const [rooms, setRooms] = useState(1);

    
    const handleSubmit = (e) => {
        e.preventDefault();
        console.log("Booking Details:", { checkIn, checkOut, guests, rooms });
        alert(`Booking confirmed! ${rooms} room(s) for ${guests} guests from ${checkIn} to ${checkOut}`);
    };

    return (
        <div className="room-booking">
            <h3>Your Reservation</h3>
            <form onSubmit={handleSubmit}>
                {/* Check-in */}
                <div className="check-date">
                    <label htmlFor="date-in">Check In:</label>
                    <input
                        type="date"
                        id="date-in"
                        value={checkIn}
                        onChange={(e) => setCheckIn(e.target.value)}
                    />
                </div>

                {/* Check-out */}
                <div className="check-date">
                    <label htmlFor="date-out">Check Out:</label>
                    <input
                        type="date"
                        id="date-out"
                        value={checkOut}
                        onChange={(e) => setCheckOut(e.target.value)}
                    />
                </div>

                {/* Guests */}
                <div className="select-option">
                    <label htmlFor="guest">Guests:</label>
                    <select id="guest" value={guests} onChange={(e) => setGuests(Number(e.target.value))}>
                        <option value="1">1 Adult</option>
                        <option value="2">2 Adults</option>
                        <option value="3">3 Adults</option>
                        <option value="4">4 Adults</option>
                    </select>
                </div>

                {/* Rooms */}
                <div className="select-option">
                    <label htmlFor="room">Room:</label>
                    <select id="room" value={rooms} onChange={(e) => setRooms(Number(e.target.value))}>
                        <option value="1">1 Room</option>
                        <option value="2">2 Rooms</option>
                        <option value="3">3 Rooms</option>
                    </select>
                </div>

                {/* Submit button */}
                <button type="submit">Check Availability</button>
            </form>
        </div>
    );
};

export default RoomBooking;
