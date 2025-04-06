export const validateEmail = (email) => {
    const regex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/
    return regex.test(email)
}

export const validatePhoneNumber = (phone) => {
    const regex = /^\d{6,}$/; // ít nhất 6 chữ số
    return regex.test(phone);
}
