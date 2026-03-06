package com.mall.vegetable.service;

import com.mall.vegetable.dao.BrowseHistoryMapper;
import com.mall.vegetable.pojo.BrowseHistory;
import com.mall.vegetable.pojo.Vegetable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BrowseHistoryService {

    @Autowired
    private BrowseHistoryMapper browseHistoryMapper;

    public void addHistory(Integer userId, Integer vegetableId) {
        // 先删除该用户对该蔬菜的旧记录（确保同一个蔬菜ID只出现一次）
        browseHistoryMapper.deleteByUserIdAndVegetableId(userId, vegetableId);

        // 插入新的浏览记录
        BrowseHistory history = new BrowseHistory();
        history.setUserId(userId);
        history.setVegetableId(vegetableId);
        browseHistoryMapper.insert(history);

        // 删除旧的历史记录，只保留最近3条
        browseHistoryMapper.deleteOldHistory(userId);
    }

    public List<Vegetable> getRecentHistory(Integer userId) {
        List<BrowseHistory> histories = browseHistoryMapper.findByUserId(userId);
        List<Vegetable> vegetables = new ArrayList<>();
        
        for (BrowseHistory history : histories) {
            if (history.getVegetable() != null) {
                vegetables.add(history.getVegetable());
            }
        }
        
        return vegetables;
    }
}
