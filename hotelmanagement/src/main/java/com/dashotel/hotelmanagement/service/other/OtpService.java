package com.dashotel.hotelmanagement.service.other;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class OtpService {
    RedisTemplate<String, String> redisTemplate;
    long OTP_TIMEOUT = 5;
    long OTP_SEND_LIMIT_TIME = 1;
    int OTP_SEND_LIMIT = 3;

    public String generateOTP(String key) {
        String otp = String.valueOf(new Random().nextInt(900000) + 100000);
        redisTemplate.opsForValue().set(key, otp, OTP_TIMEOUT, TimeUnit.MINUTES);
        return otp;
    }

    public boolean validateOtp(String key, String inputOtp) {
        String otp = redisTemplate.opsForValue().get(key);
        if(otp != null && otp.equals(inputOtp)) {
            redisTemplate.delete(key);
            return true;
        }
        return false;
    }

    public boolean canSendOtpAgain(String key) {
        String sendCountKey = "OTP:count:" + key; // Ktra so lan gui
        String sendCountValue = (String) redisTemplate.opsForValue().get(sendCountKey);
        Integer sendCount = sendCountValue != null ? Integer.valueOf(sendCountValue) : 0;

        if(sendCount >= OTP_SEND_LIMIT) {
            return false;
        }

        redisTemplate.opsForValue().increment(sendCountKey, 1); // Tang so lan len 1
        redisTemplate.expire(sendCountKey, OTP_SEND_LIMIT_TIME, TimeUnit.HOURS);

        return true;
    }

    public long getOtpTTL(String key) {
        long totalSecond = redisTemplate.getExpire(key, TimeUnit.SECONDS);

        return totalSecond;
    }
}
