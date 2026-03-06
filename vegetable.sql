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

 Date: 06/03/2026 23:37:58
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

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
) ENGINE = InnoDB AUTO_INCREMENT = 56 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '浏览记录表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of browse_history
-- ----------------------------
INSERT INTO `browse_history` VALUES (5, 1, 9, '2026-03-04 06:57:02');
INSERT INTO `browse_history` VALUES (6, 1, 1, '2026-03-04 07:02:08');
INSERT INTO `browse_history` VALUES (7, 1, 8, '2026-03-04 08:17:45');
INSERT INTO `browse_history` VALUES (14, 8, 7, '2026-03-05 15:29:18');
INSERT INTO `browse_history` VALUES (15, 8, 7, '2026-03-05 15:29:24');
INSERT INTO `browse_history` VALUES (16, 8, 2, '2026-03-05 15:59:44');
INSERT INTO `browse_history` VALUES (24, 2, 10, '2026-03-05 17:13:47');
INSERT INTO `browse_history` VALUES (25, 2, 10, '2026-03-05 17:15:26');
INSERT INTO `browse_history` VALUES (26, 2, 8, '2026-03-05 17:15:38');
INSERT INTO `browse_history` VALUES (31, 4, 10, '2026-03-05 18:28:39');
INSERT INTO `browse_history` VALUES (53, 3, 2, '2026-03-06 23:20:52');
INSERT INTO `browse_history` VALUES (54, 3, 1, '2026-03-06 23:21:05');
INSERT INTO `browse_history` VALUES (55, 3, 10, '2026-03-06 23:29:10');

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
  `calories` int NULL DEFAULT 0 COMMENT '卡路里（kcal/份）',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_vegetable_id`(`vegetable_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 31 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '菜谱表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of recipe
-- ----------------------------
INSERT INTO `recipe` VALUES (1, '西红柿炒鸡蛋', 1, 'https://imgs.699pic.com/images/603/523/243.jpg!seo.v1', '简单', '15分钟', 2, '[\"西红柿 2个\",\"鸡蛋 3个\",\"葱花 适量\",\"盐 适量\",\"糖 少许\",\"食用油 适量\"]', '[\"1. 西红柿洗净切块，鸡蛋打散加少许盐\",\"2. 热锅凉油，倒入蛋液炒至凝固盛出\",\"3. 锅中再加少许油，放入西红柿翻炒\",\"4. 加入盐和糖调味，炒至西红柿出汁\",\"5. 倒入炒好的鸡蛋，翻炒均匀\",\"6. 撒上葱花即可出锅\"]', '西红柿炒出汁后口感更好，糖可以提鲜', 1, 280, '2026-03-05 16:36:28', '2026-03-05 17:01:48');
INSERT INTO `recipe` VALUES (2, '凉拌黄瓜', 2, 'https://imgs.699pic.com/images/500/927/650.jpg!seo.v1', '简单', '10分钟', 2, '[\"黄瓜 2根\",\"蒜末 适量\",\"生抽 2勺\",\"醋 1勺\",\"香油 少许\",\"盐 适量\",\"辣椒油 适量\"]', '[\"1. 黄瓜洗净拍碎，切成小段\",\"2. 加入盐腌制10分钟，倒掉多余水分\",\"3. 加入蒜末、生抽、醋、香油\",\"4. 根据口味加入辣椒油\",\"5. 搅拌均匀即可食用\"]', '拍碎的黄瓜更容易入味，腌制后口感更脆', 1, 85, '2026-03-05 16:36:28', '2026-03-05 17:01:06');
INSERT INTO `recipe` VALUES (3, '胡萝卜炒肉丝', 3, 'https://imgs.699pic.com/images/500/650/888.jpg!seo.v1', '中等', '20分钟', 2, '[\"胡萝卜 1根\",\"猪肉丝 150克\",\"葱姜蒜 适量\",\"生抽 1勺\",\"料酒 1勺\",\"盐 适量\",\"淀粉 适量\"]', '[\"1. 胡萝卜切丝，肉丝加料酒、淀粉腌制\",\"2. 热锅凉油，放入肉丝滑炒至变色盛出\",\"3. 锅中留油，放入葱姜蒜爆香\",\"4. 放入胡萝卜丝翻炒至断生\",\"5. 加入肉丝，调入生抽和盐\",\"6. 翻炒均匀即可出锅\"]', '胡萝卜用油炒更有利于营养吸收', 1, 320, '2026-03-05 16:36:28', '2026-03-05 17:01:03');
INSERT INTO `recipe` VALUES (4, '蒜蓉菠菜', 4, 'https://imgs.699pic.com/images/365/001/257.jpg!seo.v1', '简单', '10分钟', 2, '[\"菠菜 300克\",\"蒜末 适量\",\"盐 适量\",\"食用油 适量\",\"生抽 少许\"]', '[\"1. 菠菜洗净，焯水后捞出沥干\",\"2. 热锅凉油，放入蒜末爆香\",\"3. 放入菠菜快速翻炒\",\"4. 加入盐和生抽调味\",\"5. 翻炒均匀即可出锅\"]', '菠菜含有草酸，焯水后食用更健康', 1, 95, '2026-03-05 16:36:28', '2026-03-05 17:00:22');
INSERT INTO `recipe` VALUES (5, '酸辣土豆丝', 5, 'https://imgs.699pic.com/images/501/771/098.jpg!seo.v1', '简单', '15分钟', 2, '[\"土豆 2个\",\"干辣椒 适量\",\"花椒 少许\",\"醋 2勺\",\"盐 适量\",\"葱花 适量\"]', '[\"1. 土豆切丝，用清水浸泡去除淀粉\",\"2. 热锅凉油，放入花椒和干辣椒爆香\",\"3. 放入土豆丝大火翻炒\",\"4. 加入醋和盐调味\",\"5. 炒至土豆丝断生\",\"6. 撒上葱花即可出锅\"]', '土豆丝用清水浸泡后更脆爽', 1, 180, '2026-03-05 16:36:28', '2026-03-05 16:59:56');
INSERT INTO `recipe` VALUES (6, '醋溜白菜', 6, 'https://imgs.699pic.com/images/600/221/166.jpg!seo.v1', '简单', '15分钟', 2, '[\"白菜 300克\",\"干辣椒 适量\",\"醋 2勺\",\"生抽 1勺\",\"盐 适量\",\"糖 少许\"]', '[\"1. 白菜洗净切片，菜帮和菜叶分开\",\"2. 热锅凉油，放入干辣椒爆香\",\"3. 先放入菜帮翻炒至断生\",\"4. 再放入菜叶翻炒\",\"5. 加入醋、生抽、盐和糖调味\",\"6. 翻炒均匀即可出锅\"]', '菜帮先炒，菜叶后放，口感更好', 1, 120, '2026-03-05 16:36:28', '2026-03-05 16:59:53');
INSERT INTO `recipe` VALUES (7, '鱼香茄子', 7, 'https://imgs.699pic.com/images/502/385/917.jpg!seo.v1', '中等', '25分钟', 2, '[\"茄子 2根\",\"肉末 100克\",\"豆瓣酱 1勺\",\"醋 1勺\",\"糖 1勺\",\"生抽 1勺\",\"葱姜蒜 适量\"]', '[\"1. 茄子切条，用盐腌制后挤干水分\",\"2. 热锅多油，将茄子煎至金黄盛出\",\"3. 锅中留油，放入肉末炒散\",\"4. 加入豆瓣酱和葱姜蒜炒香\",\"5. 放入茄子，加入调味料\",\"6. 加少许水焖煮收汁即可\"]', '茄子用盐腌制后可减少吸油量', 1, 380, '2026-03-05 16:36:28', '2026-03-05 16:59:01');
INSERT INTO `recipe` VALUES (8, '青椒肉丝', 8, 'https://imgs.699pic.com/images/402/675/275.jpg!seo.v1', '中等', '20分钟', 2, '[\"青椒 2个\",\"猪肉丝 150克\",\"葱姜蒜 适量\",\"生抽 1勺\",\"料酒 1勺\",\"盐 适量\",\"淀粉 适量\"]', '[\"1. 青椒切丝，肉丝加料酒、淀粉腌制\",\"2. 热锅凉油，放入肉丝滑炒至变色盛出\",\"3. 锅中留油，放入葱姜蒜爆香\",\"4. 放入青椒丝翻炒至断生\",\"5. 加入肉丝，调入生抽和盐\",\"6. 翻炒均匀即可出锅\"]', '肉丝滑油时油温不要太高，保持嫩滑', 1, 290, '2026-03-05 16:36:28', '2026-03-05 16:58:29');
INSERT INTO `recipe` VALUES (9, '蒜蓉西兰花', 9, 'https://imgs.699pic.com/images/501/144/645.jpg!seo.v1', '简单', '10分钟', 2, '[\"西兰花 1朵\",\"蒜末 适量\",\"盐 适量\",\"蚝油 1勺\",\"食用油 适量\"]', '[\"1. 西兰花切小朵，焯水后捞出\",\"2. 热锅凉油，放入蒜末爆香\",\"3. 放入西兰花翻炒\",\"4. 加入蚝油和盐调味\",\"5. 翻炒均匀即可出锅\"]', '焯水时加少许盐和油，西兰花颜色更翠绿', 1, 110, '2026-03-05 16:36:28', '2026-03-05 16:58:05');
INSERT INTO `recipe` VALUES (10, '干煸豆角', 10, 'https://imgs.699pic.com/images/501/061/809.jpg!seo.v1', '中等', '20分钟', 2, '[\"豆角 300克\",\"干辣椒 适量\",\"花椒 少许\",\"蒜末 适量\",\"盐 适量\",\"生抽 1勺\"]', '[\"1. 豆角摘洗干净，掰成段\",\"2. 热锅多油，放入豆角煸炒至表皮起皱\",\"3. 盛出豆角，锅中留少许油\",\"4. 放入干辣椒、花椒和蒜末爆香\",\"5. 放回豆角，加入盐和生抽\",\"6. 翻炒均匀即可出锅\"]', '豆角一定要充分炒熟，否则可能引起中毒', 1, 260, '2026-03-05 16:36:28', '2026-03-05 16:57:27');
INSERT INTO `recipe` VALUES (11, '清蒸鲈鱼', 1, 'https://imgs.699pic.com/images/700/942/032.jpg!seo.v1', '简单', '20分钟', 2, '[\"鲈鱼 1条\",\"葱 2根\",\"姜 1块\",\"蒸鱼豉油 2勺\",\"料酒 1勺\",\"盐 适量\"]', '[\"1. 鲈鱼处理干净，在鱼身划几刀\",\"2. 用盐和料酒腌制10分钟\",\"3. 葱姜切丝，铺在鱼身上\",\"4. 水开后放入蒸锅，大火蒸8-10分钟\",\"5. 取出倒掉蒸鱼水，淋上蒸鱼豉油\",\"6. 另起锅烧油，浇在鱼身上即可\"]', '清蒸能最大程度保留鱼的鲜味和营养', 1, 150, '2026-03-06 20:00:00', '2026-03-06 23:31:14');
INSERT INTO `recipe` VALUES (12, '红烧肉', 1, 'https://imgs.699pic.com/images/500/667/212.jpg!seo.v1', '困难', '60分钟', 2, '[\"五花肉 500克\",\"冰糖 30克\",\"生抽 3勺\",\"老抽 1勺\",\"料酒 2勺\",\"葱姜蒜 适量\"]', '[\"1. 五花肉切块，冷水下锅焯水\",\"2. 热锅不放油，放入肉块煸炒出油\",\"3. 盛出肉块，锅中留底油\",\"4. 放入冰糖小火炒至糖色\",\"5. 倒入肉块翻炒上色\",\"6. 加入调料和热水，小火炖40分钟\",\"7. 大火收汁即可\"]', '红烧肉要小火慢炖，肉质才会软烂', 1, 550, '2026-03-06 20:00:00', '2026-03-06 23:31:35');
INSERT INTO `recipe` VALUES (13, '清炒时蔬', 4, 'https://imgs.699pic.com/images/600/216/811.jpg!seo.v1', '简单', '10分钟', 2, '[\"菠菜 100克\",\"胡萝卜 50克\",\"木耳 50克\",\"蒜 2瓣\",\"盐 适量\",\"油 适量\"]', '[\"1. 菠菜洗净切段，胡萝卜切丝，木耳泡发\",\"2. 热锅凉油，爆香蒜末\",\"3. 放入胡萝卜丝翻炒\",\"4. 加入木耳和菠菜\",\"5. 加盐调味，大火快炒\",\"6. 炒至蔬菜断生即可\"]', '多种蔬菜搭配，营养更均衡', 1, 75, '2026-03-06 20:00:00', '2026-03-06 23:32:01');
INSERT INTO `recipe` VALUES (14, '宫保鸡丁', 1, 'https://imgs.699pic.com/images/502/369/539.jpg!seo.v1', '中等', '25分钟', 2, '[\"鸡胸肉 300克\",\"花生米 50克\",\"干辣椒 10个\",\"花椒 20粒\",\"葱 1根\",\"姜 1块\"]', '[\"1. 鸡胸肉切丁，用料酒和淀粉腌制\",\"2. 花生米炸至酥脆备用\",\"3. 热锅凉油，放入花椒和干辣椒爆香\",\"4. 放入鸡丁炒至变色\",\"5. 加入调味料翻炒\",\"6. 最后放入花生米和葱段\",\"7. 翻炒均匀即可出锅\"]', '花生米最后放，保持酥脆口感', 1, 420, '2026-03-06 20:00:00', '2026-03-06 23:32:18');
INSERT INTO `recipe` VALUES (15, '水煮肉片', 1, 'https://imgs.699pic.com/images/501/719/666.jpg!seo.v1', '中等', '30分钟', 2, '[\"猪里脊肉 300克\",\"豆芽 200克\",\"豆瓣酱 2勺\",\"干辣椒 20个\",\"花椒 30粒\",\"葱姜蒜 适量\"]', '[\"1. 肉片用蛋清和淀粉腌制\",\"2. 豆芽焯水铺在碗底\",\"3. 热锅炒豆瓣酱出红油\",\"4. 加水煮开，放入肉片滑熟\",\"5. 连汤带肉倒入碗中\",\"6. 撒上辣椒和花椒\",\"7. 热油浇在辣椒上即可\"]', '肉片要一片片放入，避免粘连', 1, 480, '2026-03-06 20:00:00', '2026-03-06 23:32:34');
INSERT INTO `recipe` VALUES (16, '清蒸蛋羹', 1, 'https://imgs.699pic.com/images/700/914/547.jpg!seo.v1', '简单', '15分钟', 2, '[\"鸡蛋 3个\",\"温水 150ml\",\"盐 适量\",\"香油 少许\",\"葱花 适量\"]', '[\"1. 鸡蛋打散，加入温水\",\"2. 加盐调味，搅拌均匀\",\"3. 过筛去泡沫\",\"4. 盖上保鲜膜，蒸锅蒸8-10分钟\",\"5. 取出淋上香油，撒上葱花\"]', '蛋液和水的比例是1:1.5，蒸出来最嫩滑', 1, 180, '2026-03-06 20:00:00', '2026-03-06 23:33:03');
INSERT INTO `recipe` VALUES (17, '冬瓜排骨汤', 1, 'https://imgs.699pic.com/images/501/318/771.jpg!seo.v1', '中等', '45分钟', 2, '[\"排骨 400克\",\"冬瓜 300克\",\"姜 3片\",\"料酒 1勺\",\"盐 适量\",\"葱花 适量\"]', '[\"1. 排骨焯水去血沫\",\"2. 冬瓜去皮切块\",\"3. 热锅爆香姜片，放入排骨翻炒\",\"4. 加料酒去腥，加水煮开\",\"5. 小火炖30分钟\",\"6. 加入冬瓜继续炖15分钟\",\"7. 加盐调味，撒上葱花\"]', '冬瓜最后放，避免煮烂', 1, 320, '2026-03-06 20:00:00', '2026-03-06 23:33:22');
INSERT INTO `recipe` VALUES (18, '凉拌木耳', 2, 'https://imgs.699pic.com/images/507/715/359.jpg!seo.v1', '简单', '15分钟', 2, '[\"木耳 100克\",\"黄瓜 1根\",\"胡萝卜 半根\",\"蒜 3瓣\",\"醋 2勺\",\"生抽 1勺\"]', '[\"1. 木耳泡发焯水，过凉水\",\"2. 黄瓜和胡萝卜切丝\",\"3. 蒜切末，加入醋和生抽调成料汁\",\"4. 所有食材放在一起\",\"5. 倒入料汁拌匀即可\"]', '木耳焯水后过凉水，口感更脆', 1, 65, '2026-03-06 20:00:00', '2026-03-06 23:33:43');
INSERT INTO `recipe` VALUES (19, '糖醋里脊', 1, 'https://imgs.699pic.com/images/365/009/347.jpg!seo.v1', '中等', '30分钟', 2, '[\"猪里脊肉 300克\",\"番茄酱 3勺\",\"白糖 2勺\",\"醋 1勺\",\"淀粉 适量\",\"鸡蛋 1个\"]', '[\"1. 里脊肉切条，用蛋液和淀粉裹糊\",\"2. 油温6成热，下肉条炸至金黄\",\"3. 复炸一次至酥脆\",\"4. 锅中留底油，放入番茄酱炒出红油\",\"5. 加入糖醋汁煮开\",\"6. 放入肉条快速翻炒\",\"7. 收汁即可出锅\"]', '复炸能让外皮更酥脆', 1, 520, '2026-03-06 20:00:00', '2026-03-06 23:34:00');
INSERT INTO `recipe` VALUES (20, '番茄牛腩', 1, 'https://imgs.699pic.com/images/601/269/372.jpg!seo.v1', '中等', '60分钟', 2, '[\"牛腩 500克\",\"番茄 3个\",\"洋葱 半个\",\"姜 3片\",\"生抽 2勺\",\"料酒 1勺\"]', '[\"1. 牛腩切块焯水\",\"2. 番茄去皮切块\",\"3. 热锅爆香姜片，放入牛腩翻炒\",\"4. 加料酒和生抽去腥上色\",\"5. 加水没过牛腩，小火炖40分钟\",\"6. 加入番茄和洋葱\",\"7. 继续炖20分钟至软烂\"]', '番茄要选熟透的，汤汁更浓郁', 1, 380, '2026-03-06 20:00:00', '2026-03-06 23:34:34');
INSERT INTO `recipe` VALUES (21, '白灼菜心', 6, 'https://imgs.699pic.com/images/501/781/406.jpg!seo.v1', '简单', '10分钟', 2, '[\"菜心 300克\",\"蒜 3瓣\",\"生抽 2勺\",\"蚝油 1勺\",\"油 适量\",\"盐 适量\"]', '[\"1. 菜心洗净，去掉老叶\",\"2. 烧一锅开水，加少许盐和油\",\"3. 放入菜心焯水1分钟\",\"4. 捞出沥干摆盘\",\"5. 蒜切末撒在菜心上\",\"6. 淋上生抽和蚝油即可\"]', '焯水时加油，菜心颜色更翠绿', 1, 55, '2026-03-06 20:00:00', '2026-03-06 23:34:50');
INSERT INTO `recipe` VALUES (22, '麻婆豆腐', 1, 'https://imgs.699pic.com/images/501/210/885.jpg!seo.v1', '中等', '20分钟', 2, '[\"豆腐 1块\",\"肉末 100克\",\"豆瓣酱 2勺\",\"花椒粉 适量\",\"葱花 适量\",\"蒜 2瓣\"]', '[\"1. 豆腐切块，用盐水焯一下\",\"2. 热锅炒香肉末\",\"3. 加入豆瓣酱炒出红油\",\"4. 加水煮开，放入豆腐\",\"5. 小火煮5分钟\",\"6. 勾芡收汁\",\"7. 撒上花椒粉和葱花\"]', '豆腐焯水可以去豆腥味', 1, 280, '2026-03-06 20:00:00', '2026-03-06 23:35:05');
INSERT INTO `recipe` VALUES (23, '清炒虾仁', 1, 'https://imgs.699pic.com/images/365/009/915.jpg!seo.v1', '简单', '15分钟', 2, '[\"虾仁 300克\",\"黄瓜 半根\",\"胡萝卜 半根\",\"姜 2片\",\"料酒 1勺\",\"盐 适量\"]', '[\"1. 虾仁用料酒和盐腌制10分钟\",\"2. 黄瓜和胡萝卜切丁\",\"3. 热锅凉油，放入姜片爆香\",\"4. 放入虾仁炒至变色\",\"5. 加入黄瓜和胡萝卜丁\",\"6. 加盐调味，大火快炒\",\"7. 炒至虾仁熟透即可\"]', '虾仁不要炒太久，保持鲜嫩', 1, 220, '2026-03-06 20:00:00', '2026-03-06 23:35:25');
INSERT INTO `recipe` VALUES (24, '土豆炖牛肉', 1, 'https://imgs.699pic.com/images/600/341/882.jpg!seo.v1', '中等', '60分钟', 2, '[\"牛肉 400克\",\"土豆 2个\",\"胡萝卜 1根\",\"洋葱 半个\",\"生抽 2勺\",\"料酒 1勺\"]', '[\"1. 牛肉切块焯水\",\"2. 土豆和胡萝卜切块\",\"3. 热锅炒香洋葱和牛肉\",\"4. 加料酒和生抽去腥上色\",\"5. 加水没过牛肉，小火炖40分钟\",\"6. 加入土豆和胡萝卜\",\"7. 继续炖20分钟至软烂\"]', '土豆不要切太小，避免煮烂', 1, 450, '2026-03-06 20:00:00', '2026-03-06 23:35:42');
INSERT INTO `recipe` VALUES (25, '蒜蓉生菜', 6, 'https://imgs.699pic.com/images/501/274/638.jpg!seo.v1', '简单', '5分钟', 2, '[\"生菜 300克\",\"蒜 4瓣\",\"盐 适量\",\"蚝油 1勺\",\"油 适量\"]', '[\"1. 生菜洗净沥干\",\"2. 蒜切末\",\"3. 热锅凉油，爆香蒜末\",\"4. 放入生菜大火快炒\",\"5. 加盐和蚝油调味\",\"6. 炒至生菜变软即可\"]', '生菜炒制时间要短，保持脆嫩', 1, 45, '2026-03-06 20:00:00', '2026-03-06 23:36:00');
INSERT INTO `recipe` VALUES (26, '可乐鸡翅', 1, 'https://imgs.699pic.com/images/500/108/356.jpg!seo.v1', '简单', '30分钟', 2, '[\"鸡翅 8个\",\"可乐 1罐\",\"生抽 2勺\",\"料酒 1勺\",\"姜 3片\",\"盐 适量\"]', '[\"1. 鸡翅洗净，两面划刀\",\"2. 热锅煎鸡翅至两面金黄\",\"3. 加入姜片爆香\",\"4. 倒入可乐没过鸡翅\",\"5. 加入生抽和料酒\",\"6. 小火炖20分钟\",\"7. 大火收汁即可\"]', '可乐的甜味能让鸡翅更鲜嫩', 1, 380, '2026-03-06 20:00:00', '2026-03-06 23:36:17');
INSERT INTO `recipe` VALUES (27, '清蒸茄子', 7, 'https://imgs.699pic.com/images/700/058/011.jpg!seo.v1', '简单', '15分钟', 2, '[\"茄子 2根\",\"蒜 3瓣\",\"生抽 2勺\",\"香油 少许\",\"葱花 适量\",\"盐 适量\"]', '[\"1. 茄子切条，摆盘\",\"2. 水开后蒸10分钟\",\"3. 蒜切末，加生抽和香油调成料汁\",\"4. 蒸好的茄子倒掉蒸水\",\"5. 淋上料汁，撒上葱花\"]', '清蒸茄子比炒的更健康', 1, 85, '2026-03-06 20:00:00', '2026-03-06 23:36:37');
INSERT INTO `recipe` VALUES (28, '香菇滑鸡', 1, 'https://imgs.699pic.com/images/501/639/523.jpg!seo.v1', '中等', '30分钟', 2, '[\"鸡胸肉 300克\",\"香菇 8朵\",\"姜 3片\",\"葱 1根\",\"生抽 2勺\",\"料酒 1勺\"]', '[\"1. 鸡肉切块，用料酒和生抽腌制\",\"2. 香菇泡发切片\",\"3. 热锅炒香姜片和鸡肉\",\"4. 加入香菇翻炒\",\"5. 加水没过食材\",\"6. 小火炖20分钟\",\"7. 大火收汁，撒上葱花\"]', '香菇要选用干香菇，味道更浓郁', 1, 280, '2026-03-06 20:00:00', '2026-03-06 23:37:04');
INSERT INTO `recipe` VALUES (29, '凉拌海带丝', 2, 'https://imgs.699pic.com/images/500/720/933.jpg!seo.v1', '简单', '20分钟', 2, '[\"海带丝 200克\",\"黄瓜 半根\",\"胡萝卜 半根\",\"蒜 3瓣\",\"醋 2勺\",\"生抽 1勺\"]', '[\"1. 海带丝泡发，焯水煮熟\",\"2. 过凉水，沥干备用\",\"3. 黄瓜和胡萝卜切丝\",\"4. 蒜切末，加入醋和生抽调成料汁\",\"5. 所有食材放在一起\",\"6. 倒入料汁拌匀即可\"]', '海带丝要煮软，口感更好', 1, 70, '2026-03-06 20:00:00', '2026-03-06 23:37:20');
INSERT INTO `recipe` VALUES (30, '孜然羊肉', 1, 'https://imgs.699pic.com/images/700/024/489.jpg!seo.v1', '中等', '20分钟', 2, '[\"羊肉 300克\",\"洋葱 半个\",\"孜然 2勺\",\"辣椒粉 1勺\",\"生抽 1勺\",\"料酒 1勺\"]', '[\"1. 羊肉切片，用料酒和生抽腌制\",\"2. 洋葱切丝\",\"3. 热锅炒羊肉至变色\",\"4. 加入洋葱翻炒\",\"5. 撒入孜然和辣椒粉\",\"6. 加盐调味\",\"7. 翻炒均匀即可出锅\"]', '孜然是羊肉的最佳搭档', 1, 420, '2026-03-06 20:00:00', '2026-03-06 23:37:40');

-- ----------------------------
-- Table structure for recipe_rating
-- ----------------------------
DROP TABLE IF EXISTS `recipe_rating`;
CREATE TABLE `recipe_rating`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `recipe_id` int NOT NULL,
  `user_id` int NOT NULL,
  `score` tinyint NOT NULL,
  `comment` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_recipe_user`(`recipe_id` ASC, `user_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of recipe_rating
-- ----------------------------
INSERT INTO `recipe_rating` VALUES (1, 2, 2, 4, '', '2026-03-05 16:38:59');
INSERT INTO `recipe_rating` VALUES (2, 10, 2, 3, '', '2026-03-05 17:15:32');
INSERT INTO `recipe_rating` VALUES (3, 8, 2, 5, '', '2026-03-05 17:15:40');
INSERT INTO `recipe_rating` VALUES (4, 7, 3, 5, '', '2026-03-05 18:18:20');
INSERT INTO `recipe_rating` VALUES (5, 10, 3, 4, '', '2026-03-05 18:27:46');
INSERT INTO `recipe_rating` VALUES (6, 10, 4, 5, '', '2026-03-05 18:28:42');
INSERT INTO `recipe_rating` VALUES (7, 8, 3, 5, '', '2026-03-05 18:43:13');
INSERT INTO `recipe_rating` VALUES (8, 1, 3, 5, '', '2026-03-05 18:57:51');
INSERT INTO `recipe_rating` VALUES (9, 12, 3, 2, '', '2026-03-06 19:46:32');
INSERT INTO `recipe_rating` VALUES (10, 18, 3, 4, '', '2026-03-06 23:20:55');
INSERT INTO `recipe_rating` VALUES (11, 16, 3, 4, '', '2026-03-06 23:21:28');

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
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '识别历史记录表' ROW_FORMAT = DYNAMIC;

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
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'admin', '123456', 1, 'admin@vegetable.com', '13800138000', NULL, 1, '2026-03-05 16:36:27', '2026-03-05 18:15:35');
INSERT INTO `user` VALUES (3, '111', '$2a$10$I2Yf8D1Gl01HGImhMK.KzuKk5LBEYED9mOBpTZeaKgX9iyUAknC3C', 0, '', NULL, 'https://imgs.699pic.com/images/500/329/965.jpg!seo.v1', 1, '2026-03-05 18:18:09', '2026-03-05 19:29:58');
INSERT INTO `user` VALUES (4, '222', '$2a$10$tyRQ5DVdrr8NZ275PQ.GFuTEEVDl0dL71zvTkt/RUtRJ3rt29UVba', 0, NULL, NULL, NULL, 1, '2026-03-05 18:28:31', '2026-03-05 18:28:31');

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
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户收藏表' ROW_FORMAT = DYNAMIC;

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
  `calories` int NULL DEFAULT 0 COMMENT '卡路里（kcal/100g）',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_name`(`name` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '蔬菜信息表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of vegetable
-- ----------------------------
INSERT INTO `vegetable` VALUES (1, '西红柿', 'Tomato', '茄果类', 'https://imgs.699pic.com/images/500/950/712.jpg!seo.v1', '富含维生素C、番茄红素、钾、叶酸、维生素K', '抗氧化、保护心血管、增强免疫力、美容养颜', '选择颜色鲜红、果实饱满、无损伤、手感适中的', '常温保存或冷藏，避免阳光直射', '西红柿是茄科番茄属植物，原产于南美洲，现广泛栽培于世界各地。', 1, 18, '2026-03-05 16:36:28', '2026-03-05 17:03:14');
INSERT INTO `vegetable` VALUES (2, '黄瓜', 'Cucumber', '瓜类', 'https://imgs.699pic.com/images/501/197/799.jpg!seo.v1', '富含水分、维生素C、维生素K、钾、镁', '清热解毒、利尿消肿、美容护肤、减肥瘦身', '选择表皮翠绿、刺瘤明显、手感硬实的', '冷藏保存，用保鲜膜包裹可延长保鲜', '黄瓜是葫芦科黄瓜属植物，含水量高达96%，是夏季消暑佳品。', 1, 16, '2026-03-05 16:36:28', '2026-03-05 17:04:56');
INSERT INTO `vegetable` VALUES (3, '胡萝卜', 'Carrot', '根茎类', 'https://imgs.699pic.com/images/500/772/709.jpg!seo.v1', '富含胡萝卜素、维生素A、钾、膳食纤维', '保护视力、增强免疫力、抗氧化、促进消化', '选择表皮光滑、颜色鲜艳、无裂痕的', '冷藏保存，去掉顶部可延长保鲜', '胡萝卜是伞形科胡萝卜属植物，根部富含胡萝卜素。', 1, 41, '2026-03-05 16:36:28', '2026-03-05 17:04:16');
INSERT INTO `vegetable` VALUES (4, '菠菜', 'Spinach', '叶菜类', 'https://imgs.699pic.com/images/501/137/504.jpg!seo.v1', '富含铁、维生素C、维生素K、叶酸、钙', '补血养血、保护视力、增强骨骼、预防贫血', '选择叶片翠绿、根部红润、无黄叶的', '冷藏保存，尽快食用', '菠菜是藜科菠菜属植物，被誉为\"营养模范生\"。', 1, 23, '2026-03-05 16:36:28', '2026-03-05 17:05:39');
INSERT INTO `vegetable` VALUES (5, '土豆', 'Potato', '薯芋类', 'https://imgs.699pic.com/images/501/194/651.jpg!seo.v1', '富含淀粉、维生素C、钾、膳食纤维', '提供能量、增强饱腹感、维护心血管健康', '选择表皮光滑、无发芽、无绿色的', '阴凉干燥处保存，避免阳光直射', '土豆是茄科茄属植物，是全球第四大粮食作物。', 1, 77, '2026-03-05 16:36:28', '2026-03-05 17:05:52');
INSERT INTO `vegetable` VALUES (6, '白菜', 'Chinese Cabbage', '叶菜类', 'https://imgs.699pic.com/images/501/191/378.jpg!seo.v1', '富含维生素C、膳食纤维、钙、钾', '清热解毒、润肠通便、增强免疫力', '选择叶片紧实、颜色鲜绿、无烂叶的', '冷藏保存，可存放较长时间', '白菜是十字花科芸苔属植物，是中国最常见的蔬菜之一。', 1, 17, '2026-03-05 16:36:28', '2026-03-05 17:06:14');
INSERT INTO `vegetable` VALUES (7, '茄子', 'Eggplant', '茄果类', 'https://imgs.699pic.com/images/501/173/818.jpg!seo.v1', '富含花青素、维生素P、膳食纤维', '保护心血管、抗氧化、降低胆固醇', '选择表皮紫黑发亮、手感柔软的', '冷藏保存，尽快食用', '茄子是茄科茄属植物，富含花青素，具有很好的抗氧化作用。', 1, 25, '2026-03-05 16:36:28', '2026-03-05 17:07:04');
INSERT INTO `vegetable` VALUES (8, '青椒', 'Green Papper', '茄果类', 'https://imgs.699pic.com/images/501/339/573.jpg!seo.v1', '富含维生素C、维生素A、钾', '增强免疫力、促进消化、抗氧化', '选择表皮光滑、颜色鲜亮、手感硬实的', '冷藏保存，尽快食用', '青椒是好东西', 1, 31, '2026-03-06 19:41:51', '2026-03-06 19:42:06');
INSERT INTO `vegetable` VALUES (9, '西兰花', 'Broccoli', '花菜类', 'https://imgs.699pic.com/images/300/837/051.jpg!seo.v1', '富含维生素C、维生素K、叶酸、膳食纤维', '抗癌防癌、增强免疫力、保护心血管', '选择花球紧实、颜色深绿、无黄花的', '冷藏保存，尽快食用', '西兰花是十字花科芸苔属植物，被誉为\"蔬菜皇冠\"。', 1, 34, '2026-03-05 16:36:28', '2026-03-05 17:08:10');
INSERT INTO `vegetable` VALUES (10, '豆角', 'Green Bean', '豆类', 'https://imgs.699pic.com/images/501/233/171.jpg!seo.v1', '富含蛋白质、膳食纤维、维生素C、钾', '提供蛋白质、促进消化、增强饱腹感', '选择颜色翠绿、无虫眼、手感脆嫩的', '冷藏保存，尽快食用', '豆角是豆科菜豆属植物，需要充分煮熟后食用。', 1, 47, '2026-03-05 16:36:28', '2026-03-05 17:08:13');

SET FOREIGN_KEY_CHECKS = 1;
