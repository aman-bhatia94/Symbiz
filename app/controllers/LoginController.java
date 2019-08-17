package controllers;

import forms.LoginForm;
import play.Logger;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;
/**
 * Created by GHOST on 31/1/15.
 */
public class LoginController extends Controller{

    public static Result getLoginPage()
    {
        Form<LoginForm> form = Form.form(LoginForm.class).bindFromRequest();
        return ok(login.render("This is my Login Page",form.fill(new LoginForm())));
    }
    public static Result getLoginFormPage()
    {

        Form<LoginForm> form = Form.form(LoginForm.class).bindFromRequest();
        LoginForm loginform = form.get();
        Logger.debug(loginform.getEmail());
        Logger.debug(loginform.getPassword());
        return ok(login.render("This is my Login Page",form.fill(new LoginForm())));
    }
}
