package model;

public class Product {
    private int id;
    private String name;
    private Category category; // Đối tượng Category
    private double price;
    
    
    public Product() {
		
	}

	// Constructor
    public Product(int id, String name, Category category, double price) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
    }

    // Getter và Setter cho các thuộc tính
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category=" + category +
                ", price=" + price +
                '}';
    }

    public static void main(String[] args) {
        // Tạo một đối tượng Category
        Category category1 = new Category(1, "Technology");

        // Tạo một đối tượng Product
        Product product1 = new Product(1, "Laptop", category1, 1500.00);

        System.out.println(product1);
    }
}

