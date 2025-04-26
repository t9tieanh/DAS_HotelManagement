package com.dashotel.hotelmanagement.enums;

import com.dashotel.hotelmanagement.exception.CustomException;
import com.dashotel.hotelmanagement.exception.ErrorCode;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public enum HotelFacilityCategory {
    PUBLIC_FACILITIES("https://s3-ap-southeast-1.amazonaws.com/cntres-assets-ap-southeast-1-250226768838-cf675839782fd369/imageResource/2016/12/23/1482486478659-e5dc2da7d82c6e7f84df2d6da0cc611b.png", "Cơ sở vật chất công cộng"),
    FOOD_AND_BEVERAGE("https://s3-ap-southeast-1.amazonaws.com/cntres-assets-ap-southeast-1-250226768838-cf675839782fd369/imageResource/2016/12/21/1482303254515-bd78d369590cba427807f5b7b3df6022.png", "Thực phẩm & Đồ uống"),
    BUSINESS_FACILITIES("https://s3-ap-southeast-1.amazonaws.com/cntres-assets-ap-southeast-1-250226768838-cf675839782fd369/imageResource/2016/12/21/1482303267558-027736faae615602d02d68900e440901.png", "Cơ sở kinh doanh"),
    GENERAL_FACILITIES("https://s3-ap-southeast-1.amazonaws.com/cntres-assets-ap-southeast-1-250226768838-cf675839782fd369/imageResource/2016/12/23/1482486531890-cbaee7be1e0c71e690dba61a3ea68ae0.png", "Cơ sở vật chất chung"),
    INTERNET("https://s3-ap-southeast-1.amazonaws.com/cntres-assets-ap-southeast-1-250226768838-cf675839782fd369/imageResource/2016/12/21/1482303184657-1997b10ede4170e61600e707c818f0cf.png", "Internet"),
    HOTEL_SERVICES("https://s3-ap-southeast-1.amazonaws.com/cntres-assets-ap-southeast-1-250226768838-cf675839782fd369/imageResource/2016/12/23/1482486478659-e5dc2da7d82c6e7f84df2d6da0cc611b.png", "Dịch vụ khách sạn"),
    TRANSPORTATION("https://s3-ap-southeast-1.amazonaws.com/cntres-assets-ap-southeast-1-250226768838-cf675839782fd369/imageResource/2016/12/21/1482303369740-ad3659ca926b2a531848e977419027a3.png", "Vận chuyển");

    private final String imageUrl;
    private final String nameVi; // Tên tiếng Việt
    private static final Map<String, HotelFacilityCategory> LOOKUP_MAP = new HashMap<>();

    static {
        for (HotelFacilityCategory category : HotelFacilityCategory.values()) {
            LOOKUP_MAP.put(category.getImageUrl(), category);
        }
    }

    HotelFacilityCategory(String imageUrl, String nameVi) {
        this.imageUrl = imageUrl;
        this.nameVi = nameVi;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getNameVi() {
        return nameVi;
    }

    @JsonValue
    public String getValue() {
        return imageUrl;
    }

    @JsonCreator
    public static HotelFacilityCategory fromJson(String value) {
        return Arrays.stream(values())
                .filter(e -> e.name().equalsIgnoreCase(value)) // parse theo Enum.name()
                .findFirst()
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
    }
}
