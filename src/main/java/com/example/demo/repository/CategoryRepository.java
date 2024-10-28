package com.example.demo.repository;

import com.example.demo.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    // Tìm kiếm Category dựa trên tên có chứa từ khóa (phục vụ cho chức năng tìm kiếm)
    Page<Category> findByNameContaining(String name, Pageable pageable);
}
