package com.surveys_pro.categories_catalog.infrastructure.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import com.surveys_pro.categories_catalog.domain.entity.Category;
import com.surveys_pro.categories_catalog.domain.service.CategoryService;
import com.surveys_pro.dto.CategoryDto;
import com.surveys_pro.response_options.infrastructure.repository.ResponseOptionsRepository;

public class CategoryRepository implements CategoryService{
    private Connection connection;
    private ResponseOptionsRepository responseOptionsRepository;


    public CategoryRepository() {
        try {
            Properties props = new Properties();
            props.load(getClass().getClassLoader().getResourceAsStream("configdb.properties"));
            String url = props.getProperty("url");
            String user = props.getProperty("user");
            String password = props.getProperty("password");
            connection = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createCategory(Category category) {
        String sql = "INSERT INTO categories_catalog (created_at, updated_at, name) VALUES (NOW(), NOW(), ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, category.getName());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteCategory(int id) {
        String sql = "DELETE FROM categories_catalog WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Category> findCategory(int id) {
        String sql = "SELECT * FROM categories_catalog WHERE id = ? ";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if(rs.next()) {
                    Category category = new Category(rs.getInt("id"), rs.getDate("created_at"), rs.getDate("updated_at"), rs.getString("name"));
                    return Optional.of(category);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void updateCategoty(Category category) {
        String sql = "UPDATE category SET updated_at = NOW(), name = ? WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setDate(1, category.getCreatedAt());
            ps.setString(2, category.getName());
            ps.setInt(3, category.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }  
    }

    @Override
    public List<Category> getAllCategories() {
        String sql = "SELECT * FROM categories_catalog";
        List<Category> categories = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            try (ResultSet rs = ps.executeQuery()) {
                while(rs.next()) {
                    Category category = new Category(rs.getInt("id"), rs.getDate("created_at"), rs.getDate("updated_at"), rs.getString("name"));
                    categories.add(category);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }

    @Override
    public List<CategoryDto> findCategoryDto(int id) {
        String sql = "SELECT * FROM categories_catalog WHERE id = ? ";
        List<CategoryDto> categoryList = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if(rs.next()) {
                    categoryList.add(new CategoryDto(rs.getInt("id"), rs.getString("name"), responseOptionsRepository.findResponseDto(rs.getInt("id"))));
                    return categoryList;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categoryList;
    }
}
