package ws;

public class StockDTO {
    private int id;
    private String size;
    private int stock;

    public StockDTO() {}

    public StockDTO(int id, String size, int stock) {
        this.id = id;
        this.size = size;
        this.stock = stock;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "StockDTO{" +
                "id=" + id +
                ", size='" + size + '\'' +
                ", stock=" + stock +
                '}';
    }
}
