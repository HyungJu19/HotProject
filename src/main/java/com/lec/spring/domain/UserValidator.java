package com.lec.spring.domain;

import com.lec.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.regex.Pattern;

@Component
public class UserValidator implements Validator {

    @Autowired
    UserService userService;// 회원가입시 입력한 username 이 이미 가입한 username 인지 확인하려면 DB 접근 필요


    @Override
    public boolean supports(Class<?> clazz) {
        System.out.println("supports(" + clazz.getName() + ")");
        // ↓ 검증할 객체의 클래스 타입인지 확인
        boolean result = User.class.isAssignableFrom(clazz);
        System.out.println(result);
        return result;
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        String username = user.getUsername();
        if (username == null || username.trim().isEmpty()) {
            errors.rejectValue("username", "아이디는 필수입니다"); // rejectValue(field, errorcode)
        } else if (userService.isExist(username)) {
            // 이미 등록된 중복된 아이디(username) 이 들어오면
            errors.rejectValue("username", "이미 존재하는 아이디입니다");
        }
//        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "이름은 필수입니다");

        String nickname = user.getNickname();
        if (nickname == null || nickname.trim().isEmpty()) {
            errors.rejectValue("nickname", "닉네임은 필수입니다"); // rejectValue(field, errorcode)
        } else if (userService.isExistNick(nickname)) {
            errors.rejectValue("nickname", "이미 존재하는 닉네임입니다");
        }


        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "비밀번호는 필수입니다");


        String phonenumber = user.getPhonenumber();
        if (phonenumber == null || phonenumber.trim().isEmpty()) {
            errors.rejectValue("phonenumber", "핸드폰번호는 필수입니다"); // rejectValue(field, errorcode)
        } else if (userService.isExistPhoneNum(phonenumber)) {
            errors.rejectValue("phonenumber", "이미 존재하는 핸드폰번호입니다");
        }

        String phoneNumRegex = "^01(?:0|1|[6-9])[.-]?(\\d{3}|\\d{4})[.-]?(\\d{4})$";

        if (!Pattern.matches(phoneNumRegex, phonenumber)) {
            errors.rejectValue("phonenumber", "형식에 맞지 않는 핸드폰번호입니다");
        }


        String email = user.getEmail();
        if (email == null || email.trim().isEmpty()) {
            errors.rejectValue("email", "이메일은 필수입니다"); // rejectValue(field, errorcode)
        } else if (userService.isExistEmail(email)) {
            errors.rejectValue("email", "이미 존재하는 이메일입니다");
        }

        String emailRegex = "^([a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6})*$";

        if (!Pattern.matches(emailRegex, email)) {
            errors.rejectValue("email", "형식에 맞지 않는 이메일입니다");
        }


        // 입력 password , re_password 가 동일한지 비교
        if (!user.getPassword().equals(user.getRe_password())) {
            errors.rejectValue("re_password", "비밀번호와 비밀번호확인 입력값은 같아야 합니다.");

        }
    }
}
