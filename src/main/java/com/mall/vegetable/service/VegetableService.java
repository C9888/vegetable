package com.mall.vegetable.service;

import com.mall.vegetable.dao.VegetableMapper;
import com.mall.vegetable.pojo.Vegetable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VegetableService {

    @Autowired
    private VegetableMapper vegetableMapper;

    public Vegetable findById(Integer id) {
        return vegetableMapper.findById(id);
    }

    public Vegetable findByName(String name) {
        return vegetableMapper.findByName(name);
    }

    public List<Vegetable> findAll() {
        return vegetableMapper.findAll();
    }

    public List<Vegetable> searchByKeyword(String keyword) {
        return vegetableMapper.searchByKeyword(keyword);
    }

    public List<Vegetable> findPopular(int limit) {
        return vegetableMapper.findPopular(limit);
    }

    public List<Vegetable> findByCategory(String category) {
        return vegetableMapper.findByCategory(category);
    }

    public boolean addVegetable(Vegetable vegetable) {
        vegetable.setStatus(1);
        return vegetableMapper.insert(vegetable) > 0;
    }

    public boolean updateVegetable(Vegetable vegetable) {
        System.out.println("Updating vegetable in service: " + vegetable);
        System.out.println("Vegetable ID: " + vegetable.getId());
        System.out.println("Vegetable Name: " + vegetable.getName());
        System.out.println("Vegetable Status: " + vegetable.getStatus());
        int result = vegetableMapper.update(vegetable);
        System.out.println("Update result: " + result);
        return result > 0;
    }

    public boolean deleteVegetable(Integer id) {
        return vegetableMapper.deleteById(id) > 0;
    }
}
