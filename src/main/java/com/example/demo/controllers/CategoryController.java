package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.demo.entity.Category;
import com.example.demo.services.CategoryService;

import org.springframework.data.domain.Page;

@Controller
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public String viewHomePage(Model model,
                               @RequestParam(defaultValue = "1") int pageNum,
                               @RequestParam(defaultValue = "") String keyword) {
        Page<Category> page = categoryService.listAll(pageNum, keyword);
        model.addAttribute("categoryList", page.getContent());
        model.addAttribute("currentPage", pageNum);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("keyword", keyword);
        return "category/index";
    }

    @GetMapping("/new")
    public String showNewForm(Model model) {
        model.addAttribute("category", new Category());
        return "category/new";
    }
    

    @PostMapping("/save")
    public String saveCategory(Category category) {
        categoryService.save(category);
        return "redirect:/categories";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Category category = categoryService.get(id);
        model.addAttribute("category", category);
        return "category/edit";
    }

    @GetMapping("/delete/{id}")
    public String deleteCategory(@PathVariable("id") Long id) {
        categoryService.delete(id);
        return "redirect:/categories";
    }
}
