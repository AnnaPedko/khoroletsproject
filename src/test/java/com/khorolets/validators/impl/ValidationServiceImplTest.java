package com.khorolets.validators.impl;

import com.khorolets.exception.BusinessException;
import com.khorolets.validators.ValidationService;
import org.junit.Before;
import org.junit.Test;

public class ValidationServiceImplTest {
    private ValidationService validationService;

    @Before
    public void setUp() {
        validationService = new ValidationServiceImpl();
    }

    @Test
    public void validateAge() throws BusinessException {
        int age = 50;
        validationService.validateAge(age);
    }

    @Test(expected = BusinessException.class)
    public void validateAgeNegative() throws BusinessException {
        int age = 201;
        validationService.validateAge(age);
    }

    @Test
    public void validatePhone() throws BusinessException {
        String[] phoneArr = {"+380971111111", "+380501111111", "+380679211212"};
        for (String phone : phoneArr) {
            validationService.validatePhone(phone);
        }
    }

    @Test(expected = BusinessException.class)
    public void validatePhoneNegative() throws BusinessException {
        String phone = "+380661111111";
        validationService.validatePhone(phone);
    }


    @Test
    public void validateEmail() throws BusinessException {
        String email = "test@gmail.com";
        validationService.validateEmail(email);
    }

    @Test(expected = BusinessException.class)
    public void validateEmailNegative() throws BusinessException {
        String email = "testgmail.com";
        validationService.validateEmail(email);
    }

    @Test
    public void validateString() throws BusinessException {
        String string = "test";
        validationService.validateString(string);
    }
}