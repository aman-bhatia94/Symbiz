package models;

import play.db.ebean.Model;

import javax.persistence.Column;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Table(name="product")

public class Product extends Model{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", columnDefinition = "INT")
    private int id;

    @Column(name = "name", columnDefinition = "VARCHAR", length = 45)
    @NotNull
    private String name;

    @Column(name="price",precision=11,scale=3)
    private BigDecimal price;

    @Column(name = "description", columnDefinition = "VARCHAR", length = 45)
    @NotNull
    private String description;

    @ManyToOne
    @JoinColumn(name="seller_id",referencedColumnName = "id")
    private User sellerId;

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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getSellerId() {
        return sellerId;
    }

    public void setSellerId(User sellerId) {

        this.sellerId = sellerId;
    }

    public static Finder<Integer, Product> find = new Finder<Integer,Product>(
            "default",Integer.class,Product.class
    );
}

