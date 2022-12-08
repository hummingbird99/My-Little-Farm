package com.example.choi.dto;


import lombok.*;
import org.springframework.security.core.userdetails.User;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;


@Getter
@Setter
@NoArgsConstructor
public class UserInfoDto implements Serializable {


    @NotBlank(message = "이메일은 필수 입력사항입니다.")
    @Email(message = "이메일 형식을 맞춰주세요.")
    private String email;

    @NotBlank(message = "이름은 필수입력사항입니다.")
    private String name;

    //@Pattern(regexp="(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,20}", message = "비밀번호는 영문 대,소문자와 숫자, 특수기호가 적어도 1개 이상씩 포함된 8자 ~ 20자의 비밀번호여야 합니다.")
    @Size(min = 8, max = 20, message = "비밀번호는 8자 이상 20자 이하로 입력해주세요.")
    @NotBlank(message = "비밀번호는 필수 입력사항입니다.")
    private String password;

    @NotBlank(message = "닉네임은 필수 입력사항입니다.")
    @Size(min = 2, max = 10, message = "닉네임은 2자 이상 10자 이하로 입력해주세요.")
    //@Pattern(regexp = "^[ㄱ-ㅎ가-힣a-z0-9-_]{2,10}$", message = "닉네임은 특수문자를 제외한 2~10자리여야 합니다.")
    private String nickname;

    //@NotBlank(message = "주소는 필수 입력사항입니다.")
    private String address;

    @NotBlank(message = "전화번호는 필수 입력사항입니다.")
    @Pattern(regexp = "(01[016789])-?(\\d{3,4})-?(\\d{4})", message = "올바른 휴대폰 번호를 입력해주세요.")
    private String tel;

    @NotBlank(message = "아이디는 필수 입력사항입니다.")
    @Pattern(regexp = "^[ㄱ-ㅎ가-힣a-z0-9-_]{2,10}$", message = "ID 특수문자를 제외한 2~10자리여야 합니다.")
    private String userid;

    @NotBlank
    private String auth;





}

    


