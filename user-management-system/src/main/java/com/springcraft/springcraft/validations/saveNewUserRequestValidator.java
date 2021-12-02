package com.springcraft.springcraft.validations;

import com.springcraft.springcraft.Constants.constants;
import com.springcraft.springcraft.entity.User;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component("saveNewUserRequestValidator")
public class saveNewUserRequestValidator implements  ApiRequestValidator<User> {

    @Override
    public String isRequestValid(User request) throws ParseException {
        boolean check = true;
        if(!validatePhoneNumber(request.getPhoneNumber()))
        {
            return "Please enter a valid phone number";
        }

        if(!validateEmailId(request.getEmail()))
        {
            return "Please enter a valid email id";
        }
        return constants.Success;
    }

    private boolean validatePhoneNumber(String phoneNumber)
    {
        // Creating a Pattern class object
        Pattern p = Pattern.compile("^\\d{10}$");
        Matcher match = p.matcher(phoneNumber);
        return (match.matches());
    }

    private boolean validateEmailId(String emailId)
    {
        if (emailId == null)
            return false;
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        return pat.matcher(emailId).matches();
    }
}
