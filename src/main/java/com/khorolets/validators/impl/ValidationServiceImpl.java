package com.khorolets.validators.impl;

import com.khorolets.exception.BusinessException;
import com.khorolets.validators.ValidationService;

public class ValidationServiceImpl implements ValidationService {
    @Override
    public void validateAge(int age) throws BusinessException {
        if (age < 0 || age > 200) {
            throw new BusinessException("Incorrect type");
        }
    }

    @Override
    public void validatePhone(String phone) throws BusinessException {
        if(phone.length() != 10 || !isNumber(phone) || !validateCode(phone.substring(0,3)))
            throw new BusinessException("Incorrect phone");
    }

    @Override
    public void validateEmail(String email) throws BusinessException {

    if( email.length() > 254 || !isEmail(email) )
        throw new BusinessException("Incorrect email");

    }

    public boolean isNumber(String string)
    {
        return string.matches("[0-9]+");
    }

    public boolean isEmail(String string)
    {
        return string.matches(".+@.+");
    }

    public boolean validateCode(String code)
    {
            return code.equals("050")|| code.equals("097")|| code.equals("067");
    }

    public boolean isEmpty(String string)
    {
        return string.trim().length() == 0;
    }
}
