import React, { useState, useEffect } from 'react';
import './styles.scss'; // Import file SCSS đã tách riêng

const toastDetails = {
    success: {
        icon: 'fa-circle-check',
        text: 'Success: This is a success toast.'
    },
    error: {
        icon: 'fa-circle-xmark',
        text: 'Error: This is an error toast.'
    },
    warning: {
        icon: 'fa-triangle-exclamation',
        text: 'Warning: This is a warning toast.'
    },
    info: {
        icon: 'fa-circle-info',
        text: 'Info: This is an information toast.'
    }
};

const ToastNotification = ({ notification }) => {
    const [toasts, setToasts] = useState([]);

    const createToast = (type) => {
        const id = Date.now();
        const newToast = { id, type, hide: false };
        setToasts((prev) => [...prev, newToast]);

        setTimeout(() => removeToast(id), 5000);
    };

    const removeToast = (id) => {
        setToasts((prev) =>
            prev.map((toast) =>
                toast.id === id ? { ...toast, hide: true } : toast
            )
        );
        setTimeout(() => {
            setToasts((prev) => prev.filter((toast) => toast.id !== id));
        }, 500);
    };

    useEffect(() => {
        if (notification && notification.type && toastDetails[notification.type]) {
            createToast(notification.type);
        }
    }, [notification]);

    return (
        <div className="toast-container">
            <ul className="notifications">
                {toasts.map((toast) => (
                    <li
                        key={toast.id}
                        className={`toast ${toast.type} ${toast.hide ? 'hide' : ''}`}
                    >
                        <div className="column">
                            <i className={`fa-solid ${toastDetails[toast.type].icon}`}></i>
                            <span>{toastDetails[toast.type].text}</span>
                        </div>
                        <i
                            className="fa-solid fa-xmark"
                            onClick={() => removeToast(toast.id)}
                        ></i>
                    </li>
                ))}
            </ul>
        </div>
    );
};

export default ToastNotification;
