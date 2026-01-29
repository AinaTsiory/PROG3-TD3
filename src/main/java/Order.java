import java.time.Instant;
import java.util.List;
import java.util.Objects;

public class Order {
    private Integer id;
    private String reference;
    private Instant creationDatetime;
    private List<DishOrder> dishOrders;

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Order order)) return false;
        return Objects.equals(id, order.id) && Objects.equals(reference, order.reference) && Objects.equals(creationDatetime, order.creationDatetime) && Objects.equals(dishOrders, order.dishOrders);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, reference, creationDatetime, dishOrders);
    }

    private List<DishOrder> dishOrderList;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public Instant getCreationDatetime() {
        return creationDatetime;
    }

    public void setCreationDatetime(Instant creationDatetime) {
        this.creationDatetime = creationDatetime;
    }

    public List<DishOrder> getDishOrders() {
        return dishOrders;
    }

    public List<DishOrder> setDishOrderList(List<DishOrder> dishOrders) {
        this.dishOrders = dishOrders;
        return dishOrders;
    }

    public Double getTotalAmountWithoutVAT() {
        double total = 0.0;
        for (DishOrder doo : dishOrders) {
            Dish d = doo.getDish();
            if (d.getPrice() != null) {
                total += d.getPrice() * doo.getQuantity();
            }
        }
        return total;
    }

    public Double getTotalAmountWithVAT() {
        double ht = getTotalAmountWithoutVAT();
        return ht * 1.20;
    }


}
