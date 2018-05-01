package demo.Validator;

import demo.entity.User;

import java.util.ArrayList;
import java.util.List;

public class UserValidator {

    private User user;
    private List<String> errors;

    public UserValidator(User user) {
        this.user = user;
        errors = new ArrayList<>();
    }

    public List<String> validate(){
        if(user.getName().length() < 6) {
            errors.add("User name too short");
        }
        if(user.getUsername().length() < 4){
            errors.add("Username too short");
        }
        if(user.getPassword().length() <5){
            errors.add("Password too short");
        }
        if(user.getAge() < 18){
            errors.add("User age under 18");
        }
        return errors;
    }
}
