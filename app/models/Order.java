package models;

import play.db.ebean.Model;

import javax.persistence.*;
import java.util.Date;



@Entity
@Table(name="order_table")
public class Order extends Model {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", columnDefinition = "INT")
    private int id;

    @ManyToOne
    @JoinColumn(name="product_id",referencedColumnName = "id")
    private Product productId;

    @ManyToOne
    @JoinColumn(name="buyer_id",referencedColumnName = "id")
    private User buyerId;

    @Column(name = "order_status_type", columnDefinition = "VARCHAR", length = 45)
    private String orderStatusType;

    @Column(name = "created_on", columnDefinition = "DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdOn;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Product getProductId() {
        return productId;
    }

    public void setProductId(Product productId) {
        this.productId = productId;
    }

    public User getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(User buyerId) {
        this.buyerId = buyerId;
    }

    public String getOrderStatusType() {
        return orderStatusType;
    }

    public void setOrderStatusType(String orderStatusType) {
        this.orderStatusType = orderStatusType;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public static Finder<Integer, Order> find = new Finder<Integer, Order>(
            "default",Integer.class,Order.class
    );
}
