package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "cart")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private long id;

    @Column(name = "good_id")
    private Long goodId;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "payed")
    private boolean payed;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    protected Item() {
    }

    public Item(Long goodId, int quantity, boolean paid, User user) {
        this.goodId = goodId;
        this.quantity = quantity;
        this.payed = paid;
        this.user = user;
    }

    public Item(Long id, Long goodId, int quantity, boolean paid, User user) {
        this.id = id;
        this.goodId = goodId;
        this.quantity = quantity;
        this.payed = paid;
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Long getGoodId() {
        return goodId;
    }

    public void setGoodId(Long goodId) {
        this.goodId = goodId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isPayed() {
        return payed;
    }

    public void setPayed(boolean payed) {
        this.payed = payed;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item)) return false;
        Item item = (Item) o;
        return getId() == item.getId() &&
                getQuantity() == item.getQuantity() &&
                isPayed() == item.isPayed() &&
                getGoodId().equals(item.getGoodId()) &&
                getUser().equals(item.getUser());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getGoodId(), getQuantity(), isPayed(), getUser());
    }
}
