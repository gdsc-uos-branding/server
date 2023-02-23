package com.gdscuos.recruit.domain.applicant.exception;

import com.gdscuos.recruit.global.error.exception.EntityNotFoundException;
import com.gdscuos.recruit.global.error.exception.ErrorCode;

public class UserNotFoundException extends EntityNotFoundException {

    public UserNotFoundException() {
        super(ErrorCode.USER_NOT_FOUND);
    }
}
