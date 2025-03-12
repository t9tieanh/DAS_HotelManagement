import React from 'react';
import './style.scss';

const InfoComponent = () => {
  return (
    <>
      <div className="col-lg-6 d-flex align-items-center gradient-custom-2 info-component">
        <div className="text-white px-3 py-4 p-md-5 mx-md-4">
          <h4 className="mb-4 text-black">Trải nghiệm dịch vụ khách sạn tuyệt vời</h4>
          <p className="small mb-0 text-black">
            Đặt phòng nhanh chóng, tiện lợi và an toàn. Chúng tôi cam kết mang đến dịch vụ chất lượng, không gian thoải mái và trải nghiệm đáng nhớ. Hãy tận hưởng kỳ nghỉ tuyệt vời với sự chăm sóc chu đáo từ đội ngũ của chúng tôi!
          </p>

        </div>
      </div>
    </>
  );
}

export default InfoComponent;