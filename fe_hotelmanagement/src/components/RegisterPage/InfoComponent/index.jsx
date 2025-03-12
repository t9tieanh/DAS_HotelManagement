import React from "react";

const InfoComponent = () => {
    return (
        <div className="card-body p-4 p-md-5">
            <h3 className="mb-4 pb-2 pb-md-0 mb-md-5 px-md-2 text-center">Thông tin đăng ký</h3>

            <form className="px-md-2">
                <div className="form-outline mb-4">
                    <input type="text" id="form3Example1q" className="form-control" />
                    <label className="form-label" htmlFor="form3Example1q">Tên</label>
                </div>

                <div className="form-outline mb-4">
                    <input type="email" id="form3Example2q" className="form-control" />
                    <label className="form-label" htmlFor="form3Example2q">Email</label>
                </div>

                <div className="form-outline mb-4">
                    <input type="text" id="form3Example3q" className="form-control" />
                    <label className="form-label" htmlFor="form3Example3q">Số điện thoại</label>
                </div>

                <div className="form-outline mb-4">
                    <input type="text" id="form3Example4q" className="form-control" />
                    <label className="form-label" htmlFor="form3Example4q">Tên đăng nhập</label>
                </div>

                <div className="form-outline mb-4">
                    <input type="password" id="form3Example5q" className="form-control" />
                    <label className="form-label" htmlFor="form3Example5q">Mật khẩu</label>
                </div>

                <div className="form-outline mb-4">
                    <input type="password" id="form3Example6q" className="form-control" />
                    <label className="form-label" htmlFor="form3Example6q">Nhập lại mật khẩu</label>
                </div>

                <div className="text-center">
                    <button type="submit" className="btn btn-success btn-lg mb-1">Đăng ký</button>
                </div>
            </form>
        </div>
    );
}

export default InfoComponent;