package dao;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import model.Category;
import common.FileConnector;

public class CategoryDAO  implements DAO<Category> {
	private final String FILE_NAME = "categories.bin";
	
    private final FileConnector<Category> fileConnector = new FileConnector<>();
    
    public List<Category> genData() throws IOException {
        String[] drinkNames = {"Cafe", "Cam", "Sinh tố", "Sữa chua", "Nước ép",};
        List<Category> categories = new ArrayList<>();

        try (DataOutputStream out = new DataOutputStream(new FileOutputStream("categories.bin"))) {
            Random random = new Random();
            for (int i = 0; i < 5; i++) {
                int id = i + 1;
                String name = drinkNames[i]; 
                Category category = new Category(id, name);
                categories.add(category);
                
                // Write category details to file
                
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        fileConnector.writeToFile(FILE_NAME, categories);
        return categories;
    }

    @Override
    public Category get(Predicate<Category> predicate) throws ClassNotFoundException, IOException {
        List<Category> categories = getAll();
        return categories.stream().filter(predicate).findFirst().orElse(null);
    }

    @Override
    public List<Category> getAll() throws ClassNotFoundException, IOException {
        return fileConnector.readFromFile(FILE_NAME);
    }

    @Override
    public boolean add(Category category) throws ClassNotFoundException, IOException {
        List<Category> categories = getAll();
        if (categories.stream().anyMatch(c -> c.getId() == category.getId())) {
            return false; // Kiểm tra trùng ID trước khi thêm
        }
        categories.add(category);
        fileConnector.writeToFile(FILE_NAME, categories);
        return true;
    }

    @Override
    public boolean update(Category updatedCategory) throws ClassNotFoundException, IOException {
        List<Category> categories = getAll();
        for (int i = 0; i < categories.size(); i++) {
            if (categories.get(i).getId() == updatedCategory.getId()) {
                categories.set(i, updatedCategory);
                fileConnector.writeToFile(FILE_NAME, categories);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean delete(Category deletedCategory) throws ClassNotFoundException, IOException {
        List<Category> categories = getAll();
        boolean removed = categories.removeIf(category -> category.getId() == deletedCategory.getId());
        if (removed) {
            fileConnector.writeToFile(FILE_NAME, categories);
        }
        return removed;
    }

    public List<Category> searchByName(String name) throws ClassNotFoundException, IOException {
        List<Category> categories = getAll();
        return categories.stream()
                         .filter(category -> category.getName().toLowerCase().contains(name.toLowerCase()))
                         .collect(Collectors.toList());
    }
}
