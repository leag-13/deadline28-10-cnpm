package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Category;
import com.example.demo.repository.CategoryRepository;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public Page<Category> listAll(int pageNum, String keyword) {
        Pageable pageable = PageRequest.of(pageNum - 1, 5); // Số lượng bản ghi mỗi trang
        if (keyword != null && !keyword.isEmpty()) {
            return categoryRepository.findByNameContaining(keyword, pageable);
        }
        return categoryRepository.findAll(pageable);
    }

    public void save(Category category) {
        categoryRepository.save(category);
    }

    public Category get(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }

    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }
}
