package controllers;

import forms.CreateProductForm;
import forms.LoginForm;
import models.Product;
import models.User;
import models.UserType;
import play.Logger;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;
import views.html.helper.form;

import java.util.List;

/**
 * Created by GHOST on 31/1/15.
 */
public class  CreateProductController extends Controller {
        public static Result getCreateProductsPage()
        {

            String email = session().get("email");
            User user = User.find.where().eq("email", email).findUnique();
            if(user==null)
            {
                return redirect("/logout");
            }
            if(user.getUserTypeId().getId()== 1)
            {
                return redirect("/logout");
            }
            Form<CreateProductForm> form = Form.form(CreateProductForm.class).bindFromRequest();
            if(form.hasErrors()) {
                return badRequest(createproducts.render("Login Form", form));
            }
            return ok(createproducts.render("This is my Product Creation Page",form.fill(new CreateProductForm())));
        }
        public static Result getCreateProductsFormPage()
        {
            String email = session().get("email");
            User user = User.find.where().eq("email", email).findUnique();

            if(user.getUserTypeId().getId()== 1)
            {
                return redirect("/logout");
            }
            Form<CreateProductForm> form = Form.form(CreateProductForm.class).bindFromRequest();
            if(form.hasErrors()) {
                return badRequest(createproducts.render("Login Form", form));
            }
            List<Product> productList = null;

            CreateProductForm createForm = form.get();
            Product prod = new Product();
            prod.setName(createForm.getTitle());
            prod.setDescription(createForm.getDescription());
            prod.setPrice(createForm.getPrice());
            prod.setSellerId(user);
            prod.save();
            return ok(createproducts.render("Your new application is ready.", form.fill(new CreateProductForm())));
        }

}
