package controllers;

import forms.LoginForm;
import models.User;
import play.data.Form;
import play.mvc.Controller;
import views.html.*;
import play.mvc.Result;

import java.util.Date;
import java.util.List;

/**
 * Created by GHOST on 5/2/15.
 */
public class Authentication extends Controller {
    public static Result getAuthLoginPage()
    {

            Form<LoginForm> form = Form.form(LoginForm.class);
        return ok(login.render("This is my new app", form));
    }

    public static Result authenticate(){
        session().clear();

        Boolean hasEmail = false;
        Boolean invalidEmail = false;
        Boolean hasPassword = true;

        Form<LoginForm> loginForm = Form.form(LoginForm.class).bindFromRequest();
        //check here for errors
        if(loginForm.hasErrors()) {
            return badRequest(login.render("Login Form", loginForm));
        }
        //and then simply validate the username and password from database.

        /*
         * For now simply have a hard coded username and password for someone to login
         * when we will connect database to this, we will make the login dynamic.
         */
        String email = loginForm.get().getEmail();
        String password = loginForm.get().getPassword();
        User user = User.find.where().eq("email", email).findUnique();
        List<User> userlist = User.find.where().like("fname", "%raj").like("lname","%ary").findList();

        /*User person = new User();
        person.setEmail("newuser@gmail.com");
        person.setPassword("wow");
        person.setCreatedOn(new Date());
        person.save();*/

        if(user==null){
            flash("error","unable to locate user");
            return badRequest(login.render("Login Form", loginForm));

        } else if(!user.getPassword().equals(password)){
            return badRequest(login.render("Login Form", loginForm));
        }

        //validate them against certain name
        session("email", loginForm.get().getEmail());
        session("userRole","admin");

        return redirect("/productlist");
    }

    /**
     * Logout and clean the session.
     */
    public static Result logout() {
        session().clear();
        if(flash().containsKey("error")){
            String message = flash().get("error");
            flash("error", message);
        } else if(flash().containsKey("success")){
            String message = flash().get("success");
            flash("success", message);
        }

        return redirect("/login");
    }
}
