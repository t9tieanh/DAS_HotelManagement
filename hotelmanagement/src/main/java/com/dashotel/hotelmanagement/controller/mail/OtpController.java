package com.dashotel.hotelmanagement.controller.mail;

import com.dashotel.hotelmanagement.dto.response.ApiResponse;
import com.dashotel.hotelmanagement.service.impl.other.EmailService;
import com.dashotel.hotelmanagement.service.impl.other.OtpService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/otp")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OtpController {
    OtpService otpService;
    EmailService emailService;

    @PostMapping("/send")
    public ApiResponse<String> sendOtp(@RequestParam String email) {

        if(!emailService.isValidEmail(email)) {
            return ApiResponse.<String>builder()
                    .code(HttpStatus.BAD_REQUEST.value())
                    .result("Email không hợp lệ!.")
                    .build();
        }

        String key = "OTP: " + email;

        if(!otpService.canSendOtpAgain(key)) {
            return ApiResponse.<String>builder()
                    .code(HttpStatus.BAD_REQUEST.value())
                    .result("OTP vẫn còn hiệu lực hoặc chưa hết thời gian.")
                    .build();
        }

        String otp = otpService.generateOTP(key);

        String subject = "🏨 DAS_Hotels | Xác Nhận Mã OTP Của Bạn! 🔐";

        String text = "🚀 Mã OTP Của Bạn Đã Được Tạo!\n\n" +
                "Xin chào,\n\n" +
                "Để tiếp tục sử dụng dịch vụ tại DAS_Hotels, vui lòng sử dụng mã OTP bên dưới:\n\n" +
                "Mã OTP: " + otp + "\n\n" +
                "⏳ Mã này có hiệu lực trong 5 phút. Vui lòng không chia sẻ với bất kỳ ai.\n\n" +
                "Nếu bạn không yêu cầu mã này, vui lòng bỏ qua email này.\n\n" +
                "Chúc bạn có trải nghiệm tuyệt vời tại DAS_Hotels! 🌟\n\n" +
                "Trân trọng,\n" +
                "Đội ngũ hỗ trợ DAS_Hotels";

        emailService.sendEmail(email, subject, text);

        return ApiResponse.<String>builder()
                .code(HttpStatus.OK.value())
                .result("OTP đã được gửi qua email thành công!")
                .build();
    }

    @PostMapping("/verify")
    public ApiResponse<String> verifyOtp(@RequestParam String email,
                                       @RequestParam String otpInput) {
        String key = "OTP: " + email;
        boolean isValid = otpService.validateOtp(key, otpInput);
        if (isValid) {
            return ApiResponse.<String>builder()
                    .code(HttpStatus.OK.value())
                    .result("Xác thực OTP thành công!")
                    .build();
        }
        return ApiResponse.<String>builder()
                .code(HttpStatus.BAD_REQUEST.value())
                .result("OTP không hợp lệ hoặc đã hết hạn!")
                .build();
    }

    @GetMapping("/get-ttl")
    public ApiResponse<String> getTTL(@RequestParam String email) {
        String key = "OTP: " + email;
        long ttl = otpService.getOtpTTL(key);
        return ApiResponse.<String>builder()
                .code(HttpStatus.OK.value())
                .result(Long.toString(ttl))
                .build();
    }
}
