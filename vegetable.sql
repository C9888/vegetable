/*
 Navicat Premium Data Transfer

 Source Server         : 3220608051
 Source Server Type    : MySQL
 Source Server Version : 80035 (8.0.35)
 Source Host           : localhost:3306
 Source Schema         : vegetable

 Target Server Type    : MySQL
 Target Server Version : 80035 (8.0.35)
 File Encoding         : 65001

 Date: 04/03/2026 03:34:55
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for recipe
-- ----------------------------
DROP TABLE IF EXISTS `recipe`;
CREATE TABLE `recipe`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '菜谱ID',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '菜谱名称',
  `vegetable_id` int NULL DEFAULT NULL COMMENT '关联蔬菜ID',
  `image` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '菜谱图片',
  `difficulty` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '简单' COMMENT '难度：简单/中等/困难',
  `time` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '制作时间',
  `servings` int NULL DEFAULT 2 COMMENT '份量（几人份）',
  `ingredients` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '食材清单（JSON格式）',
  `steps` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '制作步骤（JSON格式）',
  `tips` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '小贴士',
  `status` tinyint NOT NULL DEFAULT 1,
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_vegetable_id`(`vegetable_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '菜谱表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of recipe
-- ----------------------------
INSERT INTO `recipe` VALUES (1, '西红柿炒鸡蛋', 1, 'https://imgs.699pic.com/images/600/196/971.jpg!seo.v1', '简单', '15分钟', 2, '[\"西红柿 2个\",\"鸡蛋 3个\",\"葱花 适量\",\"盐 适量\",\"糖 少许\",\"食用油 适量\"]', '[\"1. 西红柿洗净切块，鸡蛋打散加少许盐\",\"2. 热锅凉油，倒入蛋液炒至凝固盛出\",\"3. 锅中再加少许油，放入西红柿翻炒\",\"4. 加入盐和糖调味，炒至西红柿出汁\",\"5. 倒入炒好的鸡蛋，翻炒均匀\",\"6. 撒上葱花即可出锅\"]', '西红柿炒出汁后口感更好，糖可以提鲜', 1, '2026-03-04 03:09:43', '2026-03-04 03:30:05');
INSERT INTO `recipe` VALUES (2, '凉拌黄瓜', 2, 'https://imgs.699pic.com/images/501/265/924.jpg!seo.v1', '简单', '10分钟', 2, '[\"黄瓜 2根\",\"蒜末 适量\",\"生抽 2勺\",\"醋 1勺\",\"香油 少许\",\"盐 适量\",\"辣椒油 适量\"]', '[\"1. 黄瓜洗净拍碎，切成小段\",\"2. 加入盐腌制10分钟，倒掉多余水分\",\"3. 加入蒜末、生抽、醋、香油\",\"4. 根据口味加入辣椒油\",\"5. 搅拌均匀即可食用\"]', '拍碎的黄瓜更容易入味，腌制后口感更脆', 1, '2026-03-04 03:09:43', '2026-03-04 03:30:36');
INSERT INTO `recipe` VALUES (3, '胡萝卜炒肉丝', 3, 'https://imgs.699pic.com/images/500/650/888.jpg!seo.v1', '中等', '20分钟', 2, '[\"胡萝卜 1根\",\"猪肉丝 150克\",\"葱姜蒜 适量\",\"生抽 1勺\",\"料酒 1勺\",\"盐 适量\",\"淀粉 适量\"]', '[\"1. 胡萝卜切丝，肉丝加料酒、淀粉腌制\",\"2. 热锅凉油，放入肉丝滑炒至变色盛出\",\"3. 锅中留油，放入葱姜蒜爆香\",\"4. 放入胡萝卜丝翻炒至断生\",\"5. 加入肉丝，调入生抽和盐\",\"6. 翻炒均匀即可出锅\"]', '胡萝卜用油炒更有利于营养吸收', 1, '2026-03-04 03:09:43', '2026-03-04 03:31:03');
INSERT INTO `recipe` VALUES (4, '蒜蓉菠菜', 4, 'https://imgs.699pic.com/images/365/001/257.jpg!seo.v1', '简单', '10分钟', 2, '[\"菠菜 300克\",\"蒜末 适量\",\"盐 适量\",\"食用油 适量\",\"生抽 少许\"]', '[\"1. 菠菜洗净，焯水后捞出沥干\",\"2. 热锅凉油，放入蒜末爆香\",\"3. 放入菠菜快速翻炒\",\"4. 加入盐和生抽调味\",\"5. 翻炒均匀即可出锅\"]', '菠菜含有草酸，焯水后食用更健康', 1, '2026-03-04 03:09:43', '2026-03-04 03:31:40');
INSERT INTO `recipe` VALUES (5, '酸辣土豆丝', 5, 'https://imgs.699pic.com/images/501/771/098.jpg!seo.v1', '简单', '15分钟', 2, '[\"土豆 2个\",\"干辣椒 适量\",\"花椒 少许\",\"醋 2勺\",\"盐 适量\",\"葱花 适量\"]', '[\"1. 土豆切丝，用清水浸泡去除淀粉\",\"2. 热锅凉油，放入花椒和干辣椒爆香\",\"3. 放入土豆丝大火翻炒\",\"4. 加入醋和盐调味\",\"5. 炒至土豆丝断生\",\"6. 撒上葱花即可出锅\"]', '土豆丝用清水浸泡后更脆爽', 1, '2026-03-04 03:09:43', '2026-03-04 03:32:02');
INSERT INTO `recipe` VALUES (6, '醋溜白菜', 6, 'https://imgs.699pic.com/images/600/221/166.jpg!seo.v1', '简单', '15分钟', 2, '[\"白菜 300克\",\"干辣椒 适量\",\"醋 2勺\",\"生抽 1勺\",\"盐 适量\",\"糖 少许\"]', '[\"1. 白菜洗净切片，菜帮和菜叶分开\",\"2. 热锅凉油，放入干辣椒爆香\",\"3. 先放入菜帮翻炒至断生\",\"4. 再放入菜叶翻炒\",\"5. 加入醋、生抽、盐和糖调味\",\"6. 翻炒均匀即可出锅\"]', '菜帮先炒，菜叶后放，口感更好', 1, '2026-03-04 03:09:43', '2026-03-04 03:32:23');
INSERT INTO `recipe` VALUES (7, '鱼香茄子', 7, 'https://imgs.699pic.com/images/502/385/917.jpg!seo.v1', '中等', '25分钟', 2, '[\"茄子 2根\",\"肉末 100克\",\"豆瓣酱 1勺\",\"醋 1勺\",\"糖 1勺\",\"生抽 1勺\",\"葱姜蒜 适量\"]', '[\"1. 茄子切条，用盐腌制后挤干水分\",\"2. 热锅多油，将茄子煎至金黄盛出\",\"3. 锅中留油，放入肉末炒散\",\"4. 加入豆瓣酱和葱姜蒜炒香\",\"5. 放入茄子，加入调味料\",\"6. 加少许水焖煮收汁即可\"]', '茄子用盐腌制后可减少吸油量', 1, '2026-03-04 03:09:43', '2026-03-04 03:32:47');
INSERT INTO `recipe` VALUES (8, '青椒肉丝', 8, 'https://imgs.699pic.com/images/402/675/275.jpg!seo.v1', '中等', '20分钟', 2, '[\"青椒 2个\",\"猪肉丝 150克\",\"葱姜蒜 适量\",\"生抽 1勺\",\"料酒 1勺\",\"盐 适量\",\"淀粉 适量\"]', '[\"1. 青椒切丝，肉丝加料酒、淀粉腌制\",\"2. 热锅凉油，放入肉丝滑炒至变色盛出\",\"3. 锅中留油，放入葱姜蒜爆香\",\"4. 放入青椒丝翻炒至断生\",\"5. 加入肉丝，调入生抽和盐\",\"6. 翻炒均匀即可出锅\"]', '肉丝滑油时油温不要太高，保持嫩滑', 1, '2026-03-04 03:09:43', '2026-03-04 03:33:15');
INSERT INTO `recipe` VALUES (9, '蒜蓉西兰花', 9, 'https://imgs.699pic.com/images/501/144/645.jpg!seo.v1', '简单', '10分钟', 2, '[\"西兰花 1朵\",\"蒜末 适量\",\"盐 适量\",\"蚝油 1勺\",\"食用油 适量\"]', '[\"1. 西兰花切小朵，焯水后捞出\",\"2. 热锅凉油，放入蒜末爆香\",\"3. 放入西兰花翻炒\",\"4. 加入蚝油和盐调味\",\"5. 翻炒均匀即可出锅\"]', '焯水时加少许盐和油，西兰花颜色更翠绿', 1, '2026-03-04 03:09:43', '2026-03-04 03:33:36');
INSERT INTO `recipe` VALUES (10, '干煸豆角', 10, 'https://imgs.699pic.com/images/501/061/809.jpg!seo.v1', '中等', '20分钟', 2, '[\"豆角 300克\",\"干辣椒 适量\",\"花椒 少许\",\"蒜末 适量\",\"盐 适量\",\"生抽 1勺\"]', '[\"1. 豆角摘洗干净，掰成段\",\"2. 热锅多油，放入豆角煸炒至表皮起皱\",\"3. 盛出豆角，锅中留少许油\",\"4. 放入干辣椒、花椒和蒜末爆香\",\"5. 放回豆角，加入盐和生抽\",\"6. 翻炒均匀即可出锅\"]', '豆角一定要充分炒熟，否则可能引起中毒', 1, '2026-03-04 03:09:43', '2026-03-04 03:33:59');

-- ----------------------------
-- Table structure for recognition_history
-- ----------------------------
DROP TABLE IF EXISTS `recognition_history`;
CREATE TABLE `recognition_history`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL COMMENT '用户ID',
  `vegetable_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '识别出的蔬菜名称',
  `image_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '识别的图片URL',
  `confidence` decimal(5, 2) NULL DEFAULT NULL COMMENT '置信度',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_user_id`(`user_id` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '识别历史记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of recognition_history
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '密码（加密存储）',
  `role` tinyint NOT NULL DEFAULT 0 COMMENT '角色：0-普通用户，1-管理员',
  `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '手机号',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '头像URL',
  `status` tinyint NOT NULL DEFAULT 1 COMMENT '状态：0-禁用，1-正常',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_username`(`username` ASC) USING BTREE,
  UNIQUE INDEX `uk_email`(`email` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'admin', '$2a$10$EqKcp1WFKVQISheBxmXNGexPR.i7QYXOJC.OFfQDT8iSaHuuPdlrW', 1, 'admin@vegetable.com', '13800138000', NULL, 1, '2026-03-04 03:09:43', '2026-03-04 03:09:43');
INSERT INTO `user` VALUES (2, '111', '$2a$10$qOCN4mQDl.D7wAXS1fu26uX4xyR1cxPPP/WZsI6vOIGHWPL79fijC', 0, '', '', NULL, 1, '2026-03-04 03:29:00', '2026-03-04 03:29:00');

-- ----------------------------
-- Table structure for user_favorite
-- ----------------------------
DROP TABLE IF EXISTS `user_favorite`;
CREATE TABLE `user_favorite`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL COMMENT '用户ID',
  `type` tinyint NOT NULL COMMENT '类型：1-蔬菜，2-菜谱',
  `target_id` int NOT NULL COMMENT '目标ID',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_user_type_target`(`user_id` ASC, `type` ASC, `target_id` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户收藏表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_favorite
-- ----------------------------

-- ----------------------------
-- Table structure for vegetable
-- ----------------------------
DROP TABLE IF EXISTS `vegetable`;
CREATE TABLE `vegetable`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '蔬菜ID',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '蔬菜名称',
  `name_en` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '英文名称',
  `category` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '分类',
  `image` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '图片URL',
  `nutrition` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '营养成分',
  `benefits` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '功效作用',
  `selection_tips` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '挑选技巧',
  `storage_method` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '储存方法',
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '描述',
  `status` tinyint NOT NULL DEFAULT 1 COMMENT '状态：0-禁用，1-正常',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_name`(`name` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '蔬菜信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of vegetableError starting ApplicationContext. To display the conditions report re-run your application with 'debug' enabled.
2026-03-04 04:01:37.726 ERROR 82324 --- [           main] o.s.b.d.LoggingFailureAnalysisReporter   : 

***************************
APPLICATION FAILED TO START
***************************

Description:

Field browseHistoryMapper in com.mall.vegetable.service.BrowseHistoryService required a bean of type 'com.mall.vegetable.mapper.BrowseHistoryMapper' that could not be found.

The injection point has the following annotations:
	- @org.springframework.beans.factory.annotation.Autowired(required=true)


Action:

Consider defining a bean of type 'com.mall.vegetable.mapper.BrowseHistoryMapper' in your configuration.
-- ----------------------------
INSERT INTO `vegetable` VALUES (1, '西红柿', 'Tomato', '茄果类', 'https://images.unsplash.com/photo-1592924357228-91a4daadcfea?w=400', '富含维生素C、番茄红素、钾、叶酸、维生素K', '抗氧化、保护心血管、增强免疫力、美容养颜', '选择颜色鲜红、果实饱满、无损伤、手感适中的', '常温保存或冷藏，避免阳光直射', '西红柿是茄科番茄属植物，原产于南美洲，现广泛栽培于世界各地。', 1, '2026-03-04 03:09:43', '2026-03-04 03:09:43');
INSERT INTO `vegetable` VALUES (2, '黄瓜', 'Cucumber', '瓜类', 'https://images.unsplash.com/photo-1449300079323-02e209d9d3a6?w=400', '富含水分、维生素C、维生素K、钾、镁', '清热解毒、利尿消肿、美容护肤、减肥瘦身', '选择表皮翠绿、刺瘤明显、手感硬实的', '冷藏保存，用保鲜膜包裹可延长保鲜', '黄瓜是葫芦科黄瓜属植物，含水量高达96%，是夏季消暑佳品。', 1, '2026-03-04 03:09:43', '2026-03-04 03:09:43');
INSERT INTO `vegetable` VALUES (3, '胡萝卜', 'Carrot', '根茎类', 'https://images.unsplash.com/photo-1598170845058-32b9d6a5da37?w=400', '富含胡萝卜素、维生素A、钾、膳食纤维', '保护视力、增强免疫力、抗氧化、促进消化', '选择表皮光滑、颜色鲜艳、无裂痕的', '冷藏保存，去掉顶部可延长保鲜', '胡萝卜是伞形科胡萝卜属植物，根部富含胡萝卜素。', 1, '2026-03-04 03:09:43', '2026-03-04 03:09:43');
INSERT INTO `vegetable` VALUES (4, '菠菜', 'Spinach', '叶菜类', 'https://images.unsplash.com/photo-1576045057995-568f588f82fb?w=400', '富含铁、维生素C、维生素K、叶酸、钙', '补血养血、保护视力、增强骨骼、预防贫血', '选择叶片翠绿、根部红润、无黄叶的', '冷藏保存，尽快食用', '菠菜是藜科菠菜属植物，被誉为\"营养模范生\"。', 1, '2026-03-04 03:09:43', '2026-03-04 03:09:43');
INSERT INTO `vegetable` VALUES (5, '土豆', 'Potato', '薯芋类', 'https://imgs.699pic.com/images/501/336/308.jpg!seo.v1', '富含淀粉、维生素C、钾、膳食纤维', '提供能量、增强饱腹感、维护心血管健康', '选择表皮光滑、无发芽、无绿色的', '阴凉干燥处保存，避免阳光直射', '土豆是茄科茄属植物，是全球第四大粮食作物。', 1, '2026-03-04 03:09:43', '2026-03-04 03:23:50');
INSERT INTO `vegetable` VALUES (6, '白菜', 'Chinese Cabbage', '叶菜类', 'https://images.unsplash.com/photo-1594282486552-05b4d80fbb9f?w=400', '富含维生素C、膳食纤维、钙、钾', '清热解毒、润肠通便、增强免疫力', '选择叶片紧实、颜色鲜绿、无烂叶的', '冷藏保存，可存放较长时间', '白菜是十字花科芸苔属植物，是中国最常见的蔬菜之一。', 1, '2026-03-04 03:09:43', '2026-03-04 03:09:43');
INSERT INTO `vegetable` VALUES (7, '茄子', 'Eggplant', '茄果类', 'https://images.unsplash.com/photo-1528826007177-f38517ce9a8a?w=400', '富含花青素、维生素P、膳食纤维', '保护心血管、抗氧化、降低胆固醇', '选择表皮紫黑发亮、手感柔软的', '冷藏保存，尽快食用', '茄子是茄科茄属植物，富含花青素，具有很好的抗氧化作用。', 1, '2026-03-04 03:09:43', '2026-03-04 03:09:43');
INSERT INTO `vegetable` VALUES (8, '青椒', 'Green Pepper', '茄果类', 'https://images.unsplash.com/photo-1563565375-f3fdfdbefa83?w=400', '富含维生素C、维生素A、钾', '增强免疫力、促进消化、抗氧化', '选择表皮光滑、颜色鲜亮、手感硬实的', '冷藏保存，可存放一周左右', '青椒是茄科辣椒属植物，不辣，适合各种烹饪方式。', 1, '2026-03-04 03:09:43', '2026-03-04 03:09:43');
INSERT INTO `vegetable` VALUES (9, '西兰花', 'Broccoli', '花菜类', 'https://images.unsplash.com/photo-1584270354949-c26b0d5b4a0c?w=400', '富含维生素C、维生素K、叶酸、膳食纤维', '抗癌防癌、增强免疫力、保护心血管', '选择花球紧实、颜色深绿、无黄花的', '冷藏保存，尽快食用', '西兰花是十字花科芸苔属植物，被誉为\"蔬菜皇冠\"。', 1, '2026-03-04 03:09:43', '2026-03-04 03:09:43');
INSERT INTO `vegetable` VALUES (10, '豆角', 'Green Bean', '豆类', 'https://images.unsplash.com/photo-1567375698348-5d9d5ae99de0?w=400', '富含蛋白质、膳食纤维、维生素C、钾', '提供蛋白质、促进消化、增强饱腹感', '选择颜色翠绿、无虫眼、手感脆嫩的', '冷藏保存，尽快食用', '豆角是豆科菜豆属植物，需要充分煮熟后食用。', 1, '2026-03-04 03:09:43', '2026-03-04 03:09:43');

-- ----------------------------
-- Table structure for browse_history
-- ----------------------------
DROP TABLE IF EXISTS `browse_history`;
CREATE TABLE `browse_history`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL COMMENT '用户ID',
  `vegetable_id` int NOT NULL COMMENT '蔬菜ID',
  `browse_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '浏览时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_user_browse`(`user_id` ASC, `browse_time` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '浏览记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of browse_history
-- ----------------------------

-- ----------------------------
-- Table structure for recipe_rating
-- ----------------------------
DROP TABLE IF EXISTS `recipe_rating`;
CREATE TABLE `recipe_rating`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '评价ID',
  `recipe_id` int NOT NULL COMMENT '菜谱ID',
  `user_id` int NOT NULL COMMENT '用户ID',
  `score` tinyint NOT NULL COMMENT '评分（1-5）',
  `comment` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '评价内容',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '评价时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_recipe_user`(`recipe_id` ASC, `user_id` ASC) USING BTREE,
  INDEX `idx_recipe_id`(`recipe_id` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '菜谱评分表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of recipe_rating
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
