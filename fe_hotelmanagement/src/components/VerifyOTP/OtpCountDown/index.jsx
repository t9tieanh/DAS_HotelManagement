import React, { useState, useEffect } from "react";
import { getTTL } from "../../../services/RegisterService/registerService";

const OtpCountDown = ({ email }) => {
    const [countdown, setCountDown] = useState(0);

    const fetchTTL = async () => {
        try {
            const ttlResponse = await getTTL(email);
            const ttlSeconds = parseInt(ttlResponse.result, 10) || 0;
            console.log(ttlSeconds);
            setCountDown(ttlSeconds)
        } catch (error) {
            console.error("Lỗi lấy TTL:", error);
            setCountDown(0);
        }
    };

    useEffect(() => {
        if (!email) return;
        fetchTTL();
    }, [email]);

    useEffect(() => {
        if (countdown <= 0) return;

        const timer = setTimeout(() => {
            setCountDown((prev) => prev - 1);
        }, 1000);

        return () => clearTimeout(timer);
    }, [countdown]);

    // Hàm định dạng số giây thành "mm : ss"
    const formatTime = (time) => {
        const minutes = Math.floor(time / 60);
        const seconds = time % 60;
        return `${minutes.toString().padStart(2, "0")} : ${seconds.toString().padStart(2, "0")}`;
    };

    return (
        <div className="time-count-down">
            Thời gian còn lại: {formatTime(countdown)}
        </div>
    );
};

export default OtpCountDown;
