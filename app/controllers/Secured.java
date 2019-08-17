package controllers;

import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Security;


public class Secured extends Security.Authenticator {
    @Override
    public String getUsername(Http.Context ctx) {
        Http.Session session = ctx.session();
        // see if user is logged in
        if (session.get("email") == null)
            return null;
        return ctx.session().get("email");
    }

    @Override
    public Result onUnauthorized(Http.Context context) {
        return redirect("/logout");
    }
}
