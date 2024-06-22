package controller;

import java.io.IOException;
import java.util.List;

import dao.CategoryDAO;
import model.Category;
import view.DrinkManagementView;

public class DrinkController {
    private CategoryDAO categoryDAO;
    private DrinkManagementView cmv;

    public DrinkController(CategoryDAO categoryDAO, DrinkManagementView cmv) {
        this.categoryDAO = categoryDAO;
        this.cmv = cmv;
    }
    public void genCategory() throws ClassNotFoundException, IOException {
    	categoryDAO.genData();
    }

    public List<Category> getAllCategories() throws ClassNotFoundException, IOException {
    	System.out.println(categoryDAO.getAll());
        return categoryDAO.getAll();
    }

    public Category getCategoryById(int id) throws ClassNotFoundException, IOException {
        return categoryDAO.get(category -> category.getId() == id);
    }

    public boolean addCategory(Category category) throws ClassNotFoundException, IOException {
        if (!categoryDAO.add(category)) {
            return false;
        }
        cmv.updateCategoryTable(getAllCategories());
        return true;
    }

    public boolean updateCategory(Category category) throws ClassNotFoundException, IOException {
        if (!categoryDAO.update(category)) {
            return false;
        }
        cmv.updateCategoryTable(getAllCategories());
        return true;
    }

    public boolean deleteCategory(Category category) throws ClassNotFoundException, IOException {
        if (!categoryDAO.delete(category)) {
            return false;
        }
        cmv.updateCategoryTable(getAllCategories());
        return true;
    }

    public List<Category> searchCategories(String name) throws ClassNotFoundException, IOException {
        List<Category> categories = categoryDAO.searchByName(name);
        cmv.updateCategoryTable(categories);
		return categories;
    }
}
