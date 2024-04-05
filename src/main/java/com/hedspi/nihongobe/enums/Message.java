package com.hedspi.nihongobe.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Message {
  WRONG_CREDENTIALS("Sai tên đăng nhập hoặc mật khẩu"),
  ACCOUNT_DEACTIVATE("Tài khoản đã bị vô hiệu hoá"),
  NO_PERMISSION("Bạn không có quyền này"),
  EMAIL_CREATED("Email này đã được sử dụng"),
  USER_NOT_FOUND("Không tìm thấy người dùng"),
  LESSON_NOT_FOUND("Không tìm thấy bài học"),
  OTP_EXPIRED_OR_INCORRECT("Mã OTP đã hết hạn hoặc không chính xác"),
  SOMETHING_GONE_WRONG("Có vấn đề bất ngờ xảy ra"),
  DELETE_SUCCESSFULLY("Xoá thành công"),
  PASSWORD_NOT_EMPTY("Mật khẩu không được rỗng"),
  OLD_PASSWORD_IS_INCORRECT("Mật khẩu cũ không chính xác");

  private final String value;
}
