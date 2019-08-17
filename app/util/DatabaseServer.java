package util;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.EbeanServer;
import com.avaje.ebean.ExpressionFactory;
import com.avaje.ebean.Query;


/**
 * Created by hannan on 2/4/15.
 */
public class DatabaseServer {
    public static ExpressionFactory expression() {
        EbeanServer myDB = Ebean.getServer("default");
        return myDB.getExpressionFactory();
    }
    public static <T> Query<T> find(Class<T> beanType){
        EbeanServer myDB = Ebean.getServer("default");
        return myDB.find(beanType);
    }
}
