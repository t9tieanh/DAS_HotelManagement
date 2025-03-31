import React, {createContext, useState} from "react";

export const ProfileContext = createContext(); // save user info

export const ProfileProvider = ({ children }) => {
    const [profile, setProfile] = useState({
      name: "",
      email: "",
      phone: ""
    });
  
    return (
      <ProfileContext.Provider value={{ profile, setProfile }}>
        {children}
      </ProfileContext.Provider>
    );
  };