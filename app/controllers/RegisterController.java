package controllers;



import forms.RegistrationForm;
import models.User;
import models.UserType;
import play.Logger;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;

import java.util.Date;

/**
 * Created by GHOST on 31/1/15.
 */
public class RegisterController extends Controller {

    public static Result getRegistrationPage()
    {
        Form<RegistrationForm> form = Form.form(RegistrationForm.class).bindFromRequest();
        if(form.hasErrors()) {
            return badRequest(register.render("Login Form", form));
        }
        return ok(register.render("This is my Registration Page",form.fill(new RegistrationForm())));
    }
    public static Result getRegistrationFormPage()
    {
        Form<RegistrationForm> form = Form.form(RegistrationForm.class).bindFromRequest();
        if(form.hasErrors()) {
            return badRequest(register.render("Login Form", form));
        }
        RegistrationForm registrationform = form.get();
        Logger.debug(registrationform.getFirstName());
        Logger.debug(registrationform.getLastName());
        Logger.debug(registrationform.getEmail());
        Logger.debug(registrationform.getPassword() );
        Logger.debug(registrationform.getDateOfBirth());
        Logger.debug(registrationform.getPhoneNo());
        User person = new User();
        person.setEmail(registrationform.getEmail());
        person.setFname(registrationform.getFirstName());
        person.setLname(registrationform.getLastName());
        person.setCreatedOn(new Date());
        person.setPassword(registrationform.getPassword());
        int userTypeId = Integer.parseInt(registrationform.getUserType());
        UserType.Type userType = UserType.getInstanceOf(userTypeId);
        person.setUserTypeId(UserType.getInstanceOf(userType));
        //person.se(registrationform.getUserType());
        person.save();
        return ok(register.render("This is my Registration Page", form.fill(new RegistrationForm())));

    }
}
