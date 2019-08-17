package models;

import play.api.libs.iteratee.internal;
import play.db.ebean.Model;
import scala.reflect.runtime.SynchronizedTypes;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by hannan on 2/3/15.
 */
@Entity
@Table(name="user_type")
public class UserType extends Model{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", columnDefinition = "INT")
    private int id;

    @Column(name = "name", columnDefinition = "VARCHAR", length = 45)
    @NotNull
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static Finder<Integer, UserType> find = new Finder<Integer, UserType>(
            "default",Integer.class, UserType.class
    );

    public enum Type{

        SELLER((int) 2),BUYER((int) 1);

        private  int id;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        Type(int id) {
            this.id = id;
        }

    }
    public static UserType getInstanceOf(Type type){
        UserType userType = new UserType();
        userType.setId(type.getId());
        userType.setName(type.toString());
        return userType;

    }
    public static UserType typeBuyer(){
        return getInstanceOf(Type.BUYER);
    }

    public static UserType typeSeller(){
        return getInstanceOf(Type.SELLER);
    }

    public static Type getInstanceOf(int id){
        for(Type t:Type.values()){
            if(t.id==id)
                return t;
        }
        return null;
    }


}

