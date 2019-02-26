package com.khorolets.validators;

import com.khorolets.exception.BusinessException;

public interface ValidationService {

    /**
     * Validate age
     *
     * @param age client age;
     **/
    void validateAge(int age) throws BusinessException;

    /**
     * Validate phone
     *
     * @param phone client phone;
     **/
    void validatePhone(String phone) throws BusinessException;

    /**
     * Validate email
     *
     * @param email client phone;
     **/
    void validateEmail(String email) throws BusinessException;

    /**
     * Validate string
     *
     * @param string string;
     **/
    void validateString(String string) throws BusinessException;
}