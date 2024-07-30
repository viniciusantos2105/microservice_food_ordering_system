package com.viniciusantos2105.userapi.domain.auth.contract;

import com.viniciusantos2105.userapi.dto.LoginRequestDto;
import com.viniciusantos2105.userapi.dto.LoginResponseDto;

public interface IAuthService {
    LoginResponseDto login(LoginRequestDto loginRequestDto);
}
