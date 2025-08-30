package com.SpringBootMVC.ExpensesTracker.service;

import com.SpringBootMVC.ExpensesTracker.entity.Category;

public interface CategoryService {
    Category findCategoryByName(String name);
    Category findCategoryById(String id);
}
