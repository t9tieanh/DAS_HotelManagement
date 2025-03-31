import React, { useState, useEffect, useContext } from "react";
import { getProfile, updateProfile } from "../../../services/CustomerProfile/profileService";
import './style.scss';
import {toast} from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import { ProfileContext } from "../../../context/ProfileContext";



const CustomerInfo = () => {

    const {profile, setProfile} = useContext(ProfileContext)

    const [editing, setEditing] = useState(false);  // mark button edit
    const [tempProfile, setTempProfile] = useState(profile); // profile copy
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        const fetchProfile = async () => {
            try {
                const data = await getProfile();
                setProfile(data);
                setTempProfile(data);
            } catch (error) {
                toast.error("Lỗi khi tải thông tin người dùng");
            } finally {
                setLoading(false);
            }
        };
        fetchProfile();
    }, []);

    if (loading) return <div>Đang tải...</div>;
    if (error) return <div>{error}</div>;

    const handleChange = (e) => {
        const { id, value } = e.target;
        setTempProfile((prev) => ({ ...prev, [id]: value }));
    };

    const handleSave = async () => {
        try {
            await updateProfile(tempProfile);
            setProfile({...tempProfile});
            setEditing(false);
            toast.success("Cập nhật thông tin thành công")
        } catch (error) {
            toast.error("Lỗi khi cập nhật thông tin");
        }
    };

    const handleCancel = () => {
        setTempProfile({...profile});
        setEditing(false);
    };

    return (
        <main className="customer-profile__main">
            <div className="tabs">
                <button>Thông tin tài khoản</button>
                <button>Mật khẩu &amp; Bảo mật</button>
            </div>

            <section>
                <div>
                    <label htmlFor="name">Tên đầy đủ</label>
                    <input
                        id="name"
                        type="text"
                        value={tempProfile.name}
                        onChange={handleChange}
                        readOnly={!editing}
                        style={{ color: editing ? "black" : "gray" }}
                    />
                </div>

                <div>
                    <label htmlFor="email">Email</label>
                    <input
                        id="email"
                        type="email"
                        value={tempProfile.email}
                        onChange={handleChange}
                        readOnly={!editing}
                        style={{ color: editing ? "black" : "gray" }}
                        required
                    />
                </div>

                <div>
                    <label htmlFor="phone">Số điện thoại</label>
                    <input
                        id="phone"
                        type="tel"
                        value={tempProfile.phone}
                        onChange={handleChange}
                        readOnly={!editing}
                        style={{ color: editing ? "black" : "gray" }}
                        required
                    />
                </div>

                {editing ? (
                    <div className="button-group">
                        <button className="save-btn" onClick={handleSave}>Lưu</button>
                        <button className="cancel-btn" onClick={handleCancel}>Hủy</button>
                    </div>
                ) : (
                    <button className="edit-btn" onClick={() => setEditing(true)}>Chỉnh sửa thông tin</button>
                )}
            </section>
        </main>
    );
};

export default CustomerInfo;
