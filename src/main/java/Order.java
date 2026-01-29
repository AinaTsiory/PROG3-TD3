import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Order {
    private Integer id;
    private String reference;
    private Instant creationDatetime;
    private OrderTypeEnum orderType;          // ← nouveau obligatoire
    private OrderStatusEnum status;           // ← nouveau obligatoire
    private List<DishOrder> dishOrders = new ArrayList<>();  // une seule liste

    public Order() {
        this.creationDatetime = Instant.now();
        this.status = OrderStatusEnum.CREATED;  // statut initial
    }

    // Getters & Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getReference() { return reference; }
    public void setReference(String reference) { this.reference = reference; }
    public Instant getCreationDatetime() { return creationDatetime; }
    public void setCreationDatetime(Instant creationDatetime) { this.creationDatetime = creationDatetime; }
    public OrderTypeEnum getOrderType() { return orderType; }
    public void setOrderType(OrderTypeEnum orderType) { this.orderType = orderType; }
    public OrderStatusEnum getStatus() { return status; }
    public void setStatus(OrderStatusEnum status) { this.status = status; }

    public List<DishOrder> getDishOrders() {
        return dishOrders;
    }

    public void setDishOrders(List<DishOrder> dishOrders) {
        this.dishOrders = (dishOrders != null) ? new ArrayList<>(dishOrders) : new ArrayList<>();
    }

    public Double getTotalAmountWithoutVAT() {
        double total = 0.0;
        for (DishOrder doo : dishOrders) {
            Dish d = doo.getDish();
            if (d != null && d.getPrice() != null) {
                total += d.getPrice() * doo.getQuantity();
            }
        }
        return total;
    }

    public Double getTotalAmountWithVAT() {
        return getTotalAmountWithoutVAT() * 1.20;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order order)) return false;
        return Objects.equals(id, order.id) &&
                Objects.equals(reference, order.reference) &&
                Objects.equals(creationDatetime, order.creationDatetime) &&
                orderType == order.orderType &&
                status == order.status &&
                Objects.equals(dishOrders, order.dishOrders);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, reference, creationDatetime, orderType, status, dishOrders);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", reference='" + reference + '\'' +
                ", status=" + status +
                ", orderType=" + orderType +
                ", totalHT=" + getTotalAmountWithoutVAT() +
                '}';
    }
}