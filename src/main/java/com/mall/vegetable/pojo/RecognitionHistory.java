package com.mall.vegetable.pojo;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class RecognitionHistory {
    private Integer id;
    private Integer userId;
    private String vegetableName;
    private String imageUrl;
    private BigDecimal confidence;
    private LocalDateTime createTime;
}
