package model;

public class Category {
    private int id;
    private String name;
    
    public Category() {
		
	}

	// Constructor
    public Category(int id, String name) {
        this.id = id;
        this.name = name;
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

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

 
}

