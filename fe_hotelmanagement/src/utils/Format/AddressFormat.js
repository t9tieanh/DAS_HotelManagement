export const convertAddressToString = (addressObj) => {
    return `${addressObj.concrete}, ${addressObj.commune}, ${addressObj.district}, ${addressObj.city}`;
};