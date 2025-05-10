package com.dashotel.hotelmanagement.utils;

import com.dashotel.hotelmanagement.dto.common.AddressDTO;
import com.dashotel.hotelmanagement.entity.hotel.AddressEntity;
import org.simmetrics.StringMetric;
import org.simmetrics.metrics.StringMetrics;
import org.springframework.stereotype.Component;

@Component
public class AddressMatcher {

    private static final StringMetric METRIC = StringMetrics.mongeElkan();

    // Hàm chuẩn hóa địa chỉ (giữ lại số, bỏ dấu, chuẩn hóa viết tắt phổ biến)
    public String normalizeString(String input) {
        if (input == null) {
            return null;
        }

        return input
                .toLowerCase()
                .replaceAll("[đ]", "d")
                .replaceAll("quận", "q")
                .replaceAll("phường", "p")
                .replaceAll("thành phố", "tp")
                .replaceAll("[^a-z0-9 ]", " ")  // giữ lại số, bỏ ký tự đặc biệt
                .replaceAll("\\s+", " ")        // bỏ khoảng trắng thừa
                .trim();
    }

    public boolean isAddressSimilar(AddressDTO address1, AddressDTO address2) {
        if (address1 == null || address2 == null) return false;

        String district1 = normalizeString(address1.getDistrict());
        String district2 = normalizeString(address2.getDistrict());

        String commune1 = normalizeString(address1.getCommune());
        String commune2 = normalizeString(address2.getCommune());

        String city1 = normalizeString(address1.getCity());
        String city2 = normalizeString(address2.getCity());

        return (isSimilarEnough(district1, district2) || isSimilarEnough(commune1, commune2)) &&
                isSimilarEnough(city1, city2);
    }

    public boolean isAddressSimilar(AddressEntity address1, AddressDTO address2) {
        if (address1 == null || address2 == null) return false;

        String district1 = normalizeString(address1.getDistrict());
        String district2 = normalizeString(address2.getDistrict());

        String commune1 = normalizeString(address1.getCommune());
        String commune2 = normalizeString(address2.getCommune());

        String city1 = normalizeString(address1.getCity());
        String city2 = normalizeString(address2.getCity());

        return isSimilarEnough(district1, district2) ||
                isSimilarEnough(commune1, commune2) ||
                isSimilarEnough(city1, city2);
    }

    private boolean isSimilarEnough(String str1, String str2) {
        if (str1 == null || str2 == null) return false;
        float score = METRIC.compare(str1, str2);
        return score >= 0.7f; // Ngưỡng tương đồng có thể điều chỉnh
    }
}