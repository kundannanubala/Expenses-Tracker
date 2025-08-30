package com.SpringBootMVC.ExpensesTracker.service;

import com.SpringBootMVC.ExpensesTracker.DTO.ExpenseDTO;
import com.SpringBootMVC.ExpensesTracker.DTO.FilterDTO;
import com.SpringBootMVC.ExpensesTracker.entity.Category;
import com.SpringBootMVC.ExpensesTracker.entity.Expense;
import com.SpringBootMVC.ExpensesTracker.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseServiceImpl implements ExpenseService {
    ExpenseRepository expenseRepository;
    ClientService clientService;
    CategoryService categoryService;
    MongoTemplate mongoTemplate;

    @Autowired
    public ExpenseServiceImpl(ExpenseRepository expenseRepository, ClientService clientService
            , CategoryService categoryService, MongoTemplate mongoTemplate) {
        this.expenseRepository = expenseRepository;
        this.clientService = clientService;
        this.categoryService = categoryService;
        this.mongoTemplate = mongoTemplate;
    }


    @Override
    public Expense findExpenseById(String id) {
        return expenseRepository.findById(id).orElse(null);
    }

    @Override
    public void save(ExpenseDTO expenseDTO) {
        System.out.println(expenseDTO);
        Expense expense = new Expense();
        expense.setAmount(expenseDTO.getAmount());
        expense.setDateTime(expenseDTO.getDateTime());
        expense.setDescription(expenseDTO.getDescription());
        expense.setClient(clientService.findClientById(expenseDTO.getClientId()));
        Category category = categoryService.findCategoryByName(expenseDTO.getCategory());
        expense.setCategory(category);
        expenseRepository.save(expense);
    }

    @Override
    public void update(ExpenseDTO expenseDTO) {
        Expense existingExpense = expenseRepository.findById(expenseDTO.getExpenseId()).orElse(null);
        if (existingExpense != null) {
            existingExpense.setAmount(expenseDTO.getAmount());
            existingExpense.setDateTime(expenseDTO.getDateTime());
            existingExpense.setDescription(expenseDTO.getDescription());
            Category category = categoryService.findCategoryByName(expenseDTO.getCategory());
            existingExpense.setCategory(category);
            expenseRepository.save(existingExpense);
        }
    }

    @Override
    public List<Expense> findAllExpenses() {
        return expenseRepository.findAll();
    }

    @Override
    public List<Expense> findAllExpensesByClientId(String id) {
        return expenseRepository.findByClientId(id);
    }

    @Override
    public void deleteExpenseById(String id) {
        expenseRepository.deleteById(id);
    }

    @Override
    public List<Expense> findFilterResult(FilterDTO filter) {
        Query query = new Query();
        
        // Category filter
        if (!"all".equals(filter.getCategory())) {
            Category category = categoryService.findCategoryByName(filter.getCategory());
            if (category != null) {
                query.addCriteria(Criteria.where("category").is(category));
            }
        }
        
        // Amount range filter
        int from = filter.getFrom();
        int to = filter.getTo();
        query.addCriteria(Criteria.where("amount").gte(from).lte(to));
        
        // Year filter
        if (!"all".equals(filter.getYear())) {
            String yearPattern = "^" + filter.getYear();
            query.addCriteria(Criteria.where("dateTime").regex(yearPattern));
        }
        
        // Month filter
        if (!"all".equals(filter.getMonth())) {
            String monthPattern = "^\\d{4}-" + String.format("%02d", Integer.parseInt(filter.getMonth()));
            query.addCriteria(Criteria.where("dateTime").regex(monthPattern));
        }
        
        return mongoTemplate.find(query, Expense.class);
    }




}
