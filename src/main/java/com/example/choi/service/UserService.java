package com.example.choi.service;

import com.example.choi.domain.entity.UserInfo;
import com.example.choi.domain.repository.UserRepository;
import com.example.choi.dto.UserInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;


import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Transactional // 로직을 처리하다 에러가 발생하면 변경된 데이터를 콜백
@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {
    public Map<String, String> validateHandling(Errors errors) {
        Map<String, String> validatorResult = new HashMap<>();

        for (FieldError error : errors.getFieldErrors()) {
            String validKeyName = String.format("valid_%s", error.getField());
            validatorResult.put(validKeyName, error.getDefaultMessage());
        }

        return validatorResult;
    }


    private final UserRepository userRepository;


    @Override // 기본적인 반환 타입은 UserDetails, UserDetails를 상속받은 UserInfo로 반환 타입 지정 (자동으로 다운 캐스팅됨)
    public UserInfo loadUserByUsername(String id) throws UsernameNotFoundException { // 시큐리티에서 지정한 서비스이기 때문에 이 메소드를 필수로 구현
        return userRepository.findByUserid(id)
                .orElseThrow(() -> new UsernameNotFoundException((id)));
    }


    public Long save(UserInfoDto infoDto) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        infoDto.setPassword(encoder.encode(infoDto.getPassword()));

        return userRepository.save(UserInfo.builder()
                .email(infoDto.getEmail())
                .auth(infoDto.getAuth())
                .address(infoDto.getAddress())
                .tel(infoDto.getTel())
                .name(infoDto.getName())
                .nickname(infoDto.getNickname())
                .userid(infoDto.getUserid())
                .password(infoDto.getPassword()).build()).getCode();

    }


    //중복 검사 
    public int checkId(String s_id){
        Optional<UserInfo> findUsers = userRepository.findByUserid(s_id);
        System.out.println(findUsers);
        if(findUsers.isPresent()){
            return 1;
            
        } else{
            return 0;
        }

    }


}

