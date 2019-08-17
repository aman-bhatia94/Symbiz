package controllers;

import models.Order;
import models.Product;
import models.User;
import models.UserType;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;

import java.util.Date;
import java.util.List;

import static controllers.Authentication.logout;

/**
 * Created by GHOST on 31/1/15.
 */
public class ProductListController extends Controller {
    public static Result getProductsPage()
    {

        String userRole = session().get("userRole");
        String email = session().get("email");
        User user = User.find.where().eq("email", email).findUnique();
        if(user==null)
        {
            return redirect("/logout");
        }
        if(user.getUserTypeId().getId()==UserType.Type.SELLER.getId())            //for a seller
        {
            return redirect("/createproducts");


        }
        return ok(productlist.render("This is my ProductsListPage",userRole));
    }
    public static  Result getRoleBasedProductList(){


        String email = session().get("email");
        User user = User.find.where().eq("email", email).findUnique();
        List<Product> productList = null;
        if(user.getUserTypeId().getId()== UserType.Type.BUYER.getId())
        {
            productList=Product.find.findList();
        }
        if(user.getUserTypeId().getId()==UserType.Type.SELLER.getId())            //for a seller
        {
            return redirect("/logout");


        }
        return ok(Json.toJson(productList));
    }
    public static Result orderProducts()
    {
        //Form<BuyProductForm> form = Form.form(BuyProductForm.class).bindFromRequest();
        //BuyProductForm buyproducts = form.get();
        String email = session().get("email");
        User user = User.find.where().eq("email", email).findUnique();
        if(user == null){
            redirect("/logout");
        }

        if (request().body() != null && request().body().asFormUrlEncoded().size() != 0 && request().body().asFormUrlEncoded().get("productId").length != 0) {
            Integer productId = Integer.parseInt(request().body().asFormUrlEncoded().get("productId")[0]);
            Product product = Product.find.byId(productId);

            Order order = new Order();
            order.setBuyerId(user);
            order.setOrderStatusType("Ordered");
            order.setProductId(product);
            order.setCreatedOn(new Date());
            order.save();
        }
        String userRole = session().get("userRole");
        return ok(productlist.render("This is my ProductsListPage",userRole));


    }
}
