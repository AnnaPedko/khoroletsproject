package com.khorolets.validators.impl;

import com.khorolets.exception.BusinessException;
import com.khorolets.validators.ValidationService;
import org.springframework.stereotype.Component;

@Component
public class ValidationServiceImpl implements ValidationService {
    @Override
    public void validateAge(int age) throws BusinessException {
        if (age < 0 || age > 200) {
            throw new BusinessException("Incorrect type");
        }
    }

    @Override
    public void validatePhone(String phone) throws BusinessException {
        if (phone != null) {
            if (phone.length() != 13 || !isNumber(phone.substring(1, 13)) || !validateCode(phone.substring(0, 6)))
                throw new BusinessException("Incorrect phone. Please enter phone in this format:+380XXXXXXXXXX");
        }
    }

    @Override
    public void validateEmail(String email) throws BusinessException {
        if (email != null) {
            if (email.length() > 254 || !isEmail(email))
                throw new BusinessException("Incorrect email. Please enter email in this format: simple@example.com ");
        }
    }

    public boolean isNumber(String string) {
        return string.matches("[0-9]+");
    }

    public boolean isEmail(String string) {
        return string.matches(".+@.+");
    }

    public boolean validateCode(String code) {
        return code.equals("+38050") || code.equals("+38097") || code.equals("+38067");
    }

    public void validateString(String string) throws BusinessException {
        if (string.isEmpty())
            throw new BusinessException("Input string is empty");
    }
}