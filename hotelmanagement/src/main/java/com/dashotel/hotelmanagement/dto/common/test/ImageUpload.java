package com.dashotel.hotelmanagement.dto.common.test;

import lombok.Builder;

@Builder
public class ImageUpload {

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    private String avatar;
}

