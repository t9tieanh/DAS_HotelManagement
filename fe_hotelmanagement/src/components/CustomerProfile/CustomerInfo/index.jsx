import React from "react";
import './style.scss';

const CustomerInfo = () => {
    return (
        <>
            <main className="customer-profile__main">

                {/* Tab điều hướng */}
                <div className="tabs">
                    <button>Thông tin tài khoản</button>
                    <button>Mật khẩu &amp; Bảo mật</button>
                </div>

                {/* Khu vực Dữ liệu cá nhân */}
                <section>
                    <div>
                        <label htmlFor="fullName">Tên đầy đủ</label>
                        <input id="fullName" type="text" value="Dē Shuāng Ruǎn" />
                    </div>

                    <div>
                        <label for="email">Email</label>
                        <input id="email" type="email" value="ndsang404@gmail.com" required />
                    </div>

                    <div>
                        <label for="phone">Số điện thoại</label>
                        <input id="phone" type="tel" value="0946353126" required />
                    </div>
                    <button className="edit-btn">Chỉnh sửa thông tin</button>

                </section>
            </main>
        </>
    )
}

export default CustomerInfo;