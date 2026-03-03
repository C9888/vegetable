package com.mall.vegetable.service;

import com.mall.vegetable.dao.RecognitionHistoryMapper;
import com.mall.vegetable.pojo.RecognitionHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecognitionHistoryService {

    @Autowired
    private RecognitionHistoryMapper recognitionHistoryMapper;

    public RecognitionHistory findById(Integer id) {
        return recognitionHistoryMapper.findById(id);
    }

    public List<RecognitionHistory> findByUserId(Integer userId) {
        return recognitionHistoryMapper.findByUserId(userId);
    }

    public List<RecognitionHistory> findRecentByUserId(Integer userId, int limit) {
        return recognitionHistoryMapper.findRecentByUserId(userId, limit);
    }

    public boolean addHistory(RecognitionHistory history) {
        return recognitionHistoryMapper.insert(history) > 0;
    }

    public boolean deleteHistory(Integer id) {
        return recognitionHistoryMapper.deleteById(id) > 0;
    }
}
