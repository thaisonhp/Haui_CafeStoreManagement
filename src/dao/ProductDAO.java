package dao;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import model.Category;
import model.Product;
import common.FileConnector;
import dao.CategoryDAO;;
public class ProductDAO implements DAO<Product> {
	private final String FILE_NAME = "Products.bin";
	private final FileConnector<Product> fileConnector = new FileConnector<>();

	public List<Product> genProductData() throws IOException {
	    CategoryDAO manager = new CategoryDAO();
	    List<Category> categories = new ArrayList<>();
	    try {
	        categories = manager.getAll();
	    } catch (ClassNotFoundException | IOException e) {
	        e.printStackTrace();
	    }

	    // Các loại sản phẩm ứng với mỗi loại Category
	    String[] productNames = {
	    	    "Cafe đen nóng/đá",
	    	    "Cafe sữa nóng/đá",
	    	    "Cafe đá xay pha kem",
	    	    "Cafe xay Chocolate",
	    	    "Cafe xay hương chuối",
	    	    "Cafe xay vị Oreo",
	    	    
	    	    "Cam vắt",
	    	    "Cam vắt không đá",
	    	    "Nước ép thơm ",
	    	    "Nước ép ổi",
	    	    "Nước ép bưởi",
	    	    "Nước ép cà rốt",
	    	    "Nước ép Cà chua ",
	    	    "Nước ép thập cẩm",
	    	    
	    	    "Sinh tố dừa",
	    	    "Sinh tố tắc",
	    	    "Sinh tố thơm",
	    	    "Sinh tố cà chua",
	    	    "Sinh tố cà rốt",
	    	    "Sinh tố thập cẩm",
	    	    
	    	    "Sữa chua cacao",
	    	    "Sữa chua dâu",
	    	    "Sữa chua cà phê",
	    	    "Sữa chua mít",
	    	    "Sữa chua cam",
	    	    "Sữa chua thập cẩm"
	    	};


	    List<Product> products = new ArrayList<>();
	    Random random = new Random();

	    try {
	        int id = 1 ;
	    
	     
	            
	            for (String productName : productNames) {
	            	Category category = new Category();
	            	for(Category c : categories) {
	            		if(productName.contains(c.getName())) {
	            			category = c ; 
	            			break ;
	            		}
	            	}
	                double price = ((int)(Math.random()*20) + 30 )*1000 ; // Giá ngẫu nhiên từ 10 đến 50
	                Product product = new Product(id++, productName, category, price);
	                products.add(product);
	            }
	        fileConnector.writeToFile(FILE_NAME, products);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return products;
	}


	@Override
	public Product get(Predicate<Product> predicate) throws ClassNotFoundException, IOException {
		List<Product> products = fileConnector.readFromFile(FILE_NAME);
		for (Product product : products) {
			if (predicate.test(product)) {
				return product;
			}
		}
		return null;
	}

	@Override
	public List<Product> getAll() throws ClassNotFoundException, IOException {
		return fileConnector.readFromFile(FILE_NAME);
	}

	@Override
	public boolean add(Product product) throws ClassNotFoundException, IOException {
		List<Product> products = getAll();
		for (Product p : products) {
			if (p.getId() == product.getId()) {
				return false;
			}
		}
		fileConnector.appendObject(FILE_NAME, product);
		return true;
	}

	@Override
	public boolean update(Product updatedProduct) throws ClassNotFoundException, IOException {
		List<Product> products = fileConnector.readFromFile(FILE_NAME);
		for (int i = 0; i < products.size(); i++) {
			if (products.get(i).getId() == updatedProduct.getId()) {
				products.set(i, updatedProduct);
				break;
			}
		}
		fileConnector.writeToFile(FILE_NAME, products);
		return true;
	}

	@Override
	public boolean delete(Product deletedProduct) throws ClassNotFoundException, IOException {
		List<Product> products = fileConnector.readFromFile(FILE_NAME);
		products.removeIf(product -> product.getId() == deletedProduct.getId());
		fileConnector.writeToFile(FILE_NAME, products);
		return true;
	}

	public List<Product> searchByName(String name) throws ClassNotFoundException, IOException {
		List<Product> products = getAll();
		return products.stream().filter(product -> product.getName().toLowerCase().contains(name.toLowerCase()))
				.collect(Collectors.toList());
	}

}
