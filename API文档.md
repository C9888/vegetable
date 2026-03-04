# 蔬菜识别与管理系统 API 接口文档

## 基础信息

- **Base URL**: `http://localhost:8080`
- **Content-Type**: `application/x-www-form-urlencoded` 或 `application/json`
- **字符编码**: `UTF-8`

---

## 1. 认证模块

### 1.1 用户登录

- **URL**: `/auth/login`
- **Method**: `POST`
- **Content-Type**: `application/x-www-form-urlencoded`

#### 请求参数

| 参数名 | 类型 | 必填 | 说明 |
|--------|------|------|------|
| username | String | 是 | 用户名 |
| password | String | 是 | 密码 |
| role | Integer | 否 | 角色：0-普通用户（默认），1-管理员 |

#### 响应

登录成功后重定向到对应页面：
- 管理员 → `/admin/dashboard`
- 普通用户 → `/user/home`

登录失败重定向到 `/login` 并携带错误信息。

---

### 1.2 用户注册

- **URL**: `/auth/register`
- **Method**: `POST`
- **Content-Type**: `application/x-www-form-urlencoded`

#### 请求参数

| 参数名 | 类型 | 必填 | 说明 |
|--------|------|------|------|
| username | String | 是 | 用户名（至少3个字符） |
| password | String | 是 | 密码（至少6个字符） |
| confirmPassword | String | 是 | 确认密码 |
| email | String | 否 | 邮箱 |
| phone | String | 否 | 手机号 |

#### 响应

注册成功重定向到 `/login`，失败则返回错误信息。

---

### 1.3 退出登录

- **URL**: `/logout`
- **Method**: `GET`

#### 响应

清除Session并重定向到登录页面。

---

## 2. 蔬菜识别模块

### 2.1 获取所有蔬菜列表

- **URL**: `/api/recognition/vegetables`
- **Method**: `GET`

#### 响应示例

```json
{
  "code": 200,
  "message": "查询成功",
  "data": [
    {
      "id": 1,
      "name": "西红柿",
      "nameEn": "Tomato",
      "category": "茄果类",
      "image": "https://images.unsplash.com/photo-1592924357228-91a4daadcfea?w=400",
      "nutrition": "富含维生素C、番茄红素、钾、叶酸、维生素K"
    },
    {
      "id": 2,
      "name": "黄瓜",
      "nameEn": "Cucumber",
      "category": "瓜类",
      "image": "https://images.unsplash.com/photo-1449300079323-02e209d9d3a6?w=400",
      "nutrition": "富含水分、维生素C、维生素K、钾、镁"
    }
  ]
}
```

---

### 2.2 获取蔬菜详细信息

- **URL**: `/api/recognition/detail/{vegetableName}`
- **Method**: `GET`

#### 请求参数

| 参数名 | 类型 | 必填 | 说明 |
|--------|------|------|------|
| vegetableName | String | 是 | 蔬菜名称（路径参数） |

#### 响应示例

```json
{
  "code": 200,
  "message": "查询成功",
  "data": {
    "id": 1,
    "name": "西红柿",
    "nameEn": "Tomato",
    "category": "茄果类",
    "image": "https://images.unsplash.com/photo-1592924357228-91a4daadcfea?w=400",
    "nutrition": "富含维生素C、番茄红素、钾、叶酸、维生素K",
    "benefits": "抗氧化、保护心血管、增强免疫力、美容养颜",
    "selectionTips": "选择颜色鲜红、果实饱满、无损伤、手感适中的",
    "storageMethod": "常温保存或冷藏，避免阳光直射",
    "description": "西红柿是茄科番茄属植物，原产于南美洲，现广泛栽培于世界各地。",
    "recommendedRecipes": [
      {
        "id": 1,
        "name": "西红柿炒鸡蛋",
        "difficulty": "简单",
        "time": "15分钟",
        "image": "https://imgs.699pic.com/images/600/196/971.jpg!seo.v1"
      }
    ]
  }
}
```

---

### 2.3 搜索蔬菜

- **URL**: `/api/recognition/search`
- **Method**: `GET`

#### 请求参数

| 参数名 | 类型 | 必填 | 说明 |
|--------|------|------|------|
| keyword | String | 是 | 搜索关键词（查询参数） |

#### 响应示例

```json
{
  "code": 200,
  "message": "搜索成功",
  "data": [
    {
      "id": 1,
      "name": "西红柿",
      "image": "https://images.unsplash.com/photo-1592924357228-91a4daadcfea?w=400",
      "category": "茄果类"
    }
  ]
}
```

---

### 2.4 获取热门蔬菜

- **URL**: `/api/recognition/popular`
- **Method**: `GET`

#### 请求参数

无

#### 响应示例

```json
{
  "code": 200,
  "message": "查询成功",
  "data": [
    {
      "id": 1,
      "name": "西红柿",
      "image": "https://images.unsplash.com/photo-1592924357228-91a4daadcfea?w=400",
      "category": "茄果类"
    }
  ]
}
```

---

### 2.5 蔬菜识别

- **URL**: `/api/recognition/identify`
- **Method**: `POST`
- **Content-Type**: `multipart/form-data`

#### 请求参数

| 参数名 | 类型 | 必填 | 说明 |
|--------|------|------|------|
| image | File | 是 | 蔬菜图片文件 |

#### 响应示例

```json
{
  "code": 200,
  "message": "识别成功",
  "data": {
    "vegetableName": "西红柿",
    "confidence": 0.95,
    "vegetableId": 1,
    "image": "https://images.unsplash.com/photo-1592924357228-91a4daadcfea?w=400"
  }
}
```

---

### 2.6 获取识别历史

- **URL**: `/api/recognition/history/{userId}`
- **Method**: `GET`

#### 请求参数

| 参数名 | 类型 | 必填 | 说明 |
|--------|------|------|------|
| userId | Integer | 是 | 用户ID（路径参数） |

#### 响应示例

```json
{
  "code": 200,
  "message": "查询成功",
  "data": [
    {
      "id": 1,
      "vegetableName": "西红柿",
      "imageUrl": "http://example.com/history/1.jpg",
      "recognizeTime": "2026-03-04 10:30:00",
      "confidence": 0.95
    }
  ]
}
```

---

## 3. 菜谱模块

### 3.1 获取蔬菜相关菜谱

- **URL**: `/api/recognition/recipes/{vegetableName}`
- **Method**: `GET`

#### 请求参数

| 参数名 | 类型 | 必填 | 说明 |
|--------|------|------|------|
| vegetableName | String | 是 | 蔬菜名称（路径参数） |

#### 响应示例

```json
{
  "code": 200,
  "message": "查询成功",
  "data": [
    {
      "id": 1,
      "name": "西红柿炒鸡蛋",
      "difficulty": "简单",
      "time": "15分钟",
      "image": "https://imgs.699pic.com/images/600/196/971.jpg!seo.v1"
    }
  ]
}
```

---

### 3.2 获取菜谱详情

- **URL**: `/api/recognition/recipe/detail/{recipeId}`
- **Method**: `GET`

#### 请求参数

| 参数名 | 类型 | 必填 | 说明 |
|--------|------|------|------|
| recipeId | Integer | 是 | 菜谱ID（路径参数） |

#### 响应示例

```json
{
  "code": 200,
  "message": "查询成功",
  "data": {
    "id": 1,
    "name": "西红柿炒鸡蛋",
    "image": "https://imgs.699pic.com/images/600/196/971.jpg!seo.v1",
    "difficulty": "简单",
    "time": "15分钟",
    "servings": 2,
    "ingredients": "[\"西红柿 2个\",\"鸡蛋 3个\",\"葱花 适量\",\"盐 适量\",\"糖 少许\",\"食用油 适量\"]",
    "steps": "[\"1. 西红柿洗净切块，鸡蛋打散加少许盐\",\"2. 热锅凉油，倒入蛋液炒至凝固盛出\",\"3. 锅中再加少许油，放入西红柿翻炒\",\"4. 加入盐和糖调味，炒至西红柿出汁\",\"5. 倒入炒好的鸡蛋，翻炒均匀\",\"6. 撒上葱花即可出锅\"]",
    "tips": "西红柿炒出汁后口感更好，糖可以提鲜"
  }
}
```

---

## 4. 用户模块

### 4.1 获取浏览记录

- **URL**: `/api/user/history/{userId}`
- **Method**: `GET`

#### 请求参数

| 参数名 | 类型 | 必填 | 说明 |
|--------|------|------|------|
| userId | Integer | 是 | 用户ID（路径参数） |

#### 响应示例

```json
{
  "code": 200,
  "message": "操作成功",
  "data": [
    {
      "id": 1,
      "name": "西红柿",
      "nameEn": "Tomato",
      "category": "茄果类",
      "image": "https://images.unsplash.com/photo-1592924357228-91a4daadcfea?w=400"
    }
  ]
}
```

---

### 4.2 添加浏览记录

- **URL**: `/api/user/history/add`
- **Method**: `POST`
- **Content-Type**: `application/x-www-form-urlencoded`

#### 请求参数

| 参数名 | 类型 | 必填 | 说明 |
|--------|------|------|------|
| userId | Integer | 是 | 用户ID |
| vegetableId | Integer | 是 | 蔬菜ID |

#### 响应示例

```json
{
  "code": 200,
  "message": "操作成功",
  "data": "添加成功"
}
```

---

### 4.3 修改密码

- **URL**: `/api/user/changePassword`
- **Method**: `POST`
- **Content-Type**: `application/x-www-form-urlencoded`

#### 请求参数

| 参数名 | 类型 | 必填 | 说明 |
|--------|------|------|------|
| userId | Integer | 是 | 用户ID |
| oldPassword | String | 是 | 原密码 |
| newPassword | String | 是 | 新密码 |

#### 响应示例

成功：
```json
{
  "code": 200,
  "message": "操作成功",
  "data": "密码修改成功"
}
```

失败：
```json
{
  "code": 500,
  "message": "原密码错误",
  "data": null
}
```

---

### 4.4 修改手机号

- **URL**: `/api/user/changePhone`
- **Method**: `POST`
- **Content-Type**: `application/x-www-form-urlencoded`

#### 请求参数

| 参数名 | 类型 | 必填 | 说明 |
|--------|------|------|------|
| userId | Integer | 是 | 用户ID |
| phone | String | 是 | 新手机号 |

#### 响应示例

```json
{
  "code": 200,
  "message": "操作成功",
  "data": "手机号修改成功"
}
```

---

## 5. 管理员模块

### 5.1 获取所有蔬菜（管理员）

- **URL**: `/admin/vegetables`
- **Method**: `GET`

#### 请求参数

| 参数名 | 类型 | 必填 | 说明 |
|--------|------|------|------|
| keyword | String | 否 | 搜索关键词 |

#### 响应

返回管理员蔬菜管理页面。

---

### 5.2 获取单个蔬菜信息

- **URL**: `/admin/vegetable/{id}`
- **Method**: `GET`

#### 请求参数

| 参数名 | 类型 | 必填 | 说明 |
|--------|------|------|------|
| id | Integer | 是 | 蔬菜ID（路径参数） |

#### 响应示例

```json
{
  "code": 200,
  "data": {
    "id": 1,
    "name": "西红柿",
    "nameEn": "Tomato",
    "category": "茄果类",
    "image": "https://images.unsplash.com/photo-1592924357228-91a4daadcfea?w=400",
    "nutrition": "富含维生素C、番茄红素、钾、叶酸、维生素K",
    "benefits": "抗氧化、保护心血管、增强免疫力、美容养颜",
    "selectionTips": "选择颜色鲜红、果实饱满、无损伤、手感适中的",
    "storageMethod": "常温保存或冷藏，避免阳光直射",
    "description": "西红柿是茄科番茄属植物，原产于南美洲。",
    "status": 1
  }
}
```

---

### 5.3 保存蔬菜

- **URL**: `/admin/vegetable/save`
- **Method**: `POST`
- **Content-Type**: `application/x-www-form-urlencoded`

#### 请求参数

| 参数名 | 类型 | 必填 | 说明 |
|--------|------|------|------|
| id | Integer | 否 | 蔬菜ID（更新时必填） |
| name | String | 是 | 蔬菜名称 |
| nameEn | String | 否 | 英文名称 |
| category | String | 否 | 分类 |
| image | String | 否 | 图片URL |
| nutrition | String | 否 | 营养成分 |
| benefits | String | 否 | 功效作用 |
| selectionTips | String | 否 | 挑选技巧 |
| storageMethod | String | 否 | 储存方法 |
| description | String | 否 | 描述 |

#### 响应

保存成功后重定向到蔬菜列表页面。

---

### 5.4 删除蔬菜

- **URL**: `/admin/vegetable/delete/{id}`
- **Method**: `GET`

#### 请求参数

| 参数名 | 类型 | 必填 | 说明 |
|--------|------|------|------|
| id | Integer | 是 | 蔬菜ID（路径参数） |

#### 响应

删除成功后重定向到蔬菜列表页面。

---

### 5.5 获取所有菜谱（管理员）

- **URL**: `/admin/recipes`
- **Method**: `GET`

#### 请求参数

| 参数名 | 类型 | 必填 | 说明 |
|--------|------|------|------|
| keyword | String | 否 | 搜索关键词 |
| vegetableId | Integer | 否 | 蔬菜ID筛选 |

#### 响应

返回管理员菜谱管理页面。

---

### 5.6 获取单个菜谱信息

- **URL**: `/admin/recipe/{id}`
- **Method**: `GET`

#### 请求参数

| 参数名 | 类型 | 必填 | 说明 |
|--------|------|------|------|
| id | Integer | 是 | 菜谱ID（路径参数） |

#### 响应示例

```json
{
  "code": 200,
  "data": {
    "id": 1,
    "name": "西红柿炒鸡蛋",
    "vegetableId": 1,
    "image": "https://imgs.699pic.com/images/600/196/971.jpg!seo.v1",
    "difficulty": "简单",
    "time": "15分钟",
    "servings": 2,
    "ingredients": "[\"西红柿 2个\",\"鸡蛋 3个\"]",
    "steps": "[\"1. 西红柿洗净切块\",\"2. 鸡蛋打散\"]",
    "tips": "西红柿炒出汁后口感更好"
  }
}
```

---

### 5.7 保存菜谱

- **URL**: `/admin/recipe/save`
- **Method**: `POST`
- **Content-Type**: `application/x-www-form-urlencoded`

#### 请求参数

| 参数名 | 类型 | 必填 | 说明 |
|--------|------|------|------|
| id | Integer | 否 | 菜谱ID（更新时必填） |
| name | String | 是 | 菜谱名称 |
| vegetableId | Integer | 是 | 关联蔬菜ID |
| image | String | 否 | 菜谱图片URL |
| difficulty | String | 否 | 难度：简单/中等/困难 |
| time | String | 否 | 制作时间 |
| servings | Integer | 否 | 份量（几人份） |
| ingredients | String | 否 | 食材清单（JSON格式） |
| steps | String | 否 | 制作步骤（JSON格式） |
| tips | String | 否 | 小贴士 |

#### 响应

保存成功后重定向到菜谱列表页面。

---

### 5.8 删除菜谱

- **URL**: `/admin/recipe/delete/{id}`
- **Method**: `GET`

#### 请求参数

| 参数名 | 类型 | 必填 | 说明 |
|--------|------|------|------|
| id | Integer | 是 | 菜谱ID（路径参数） |

#### 响应

删除成功后重定向到菜谱列表页面。

---

### 5.9 获取用户列表

- **URL**: `/admin/users`
- **Method**: `GET`

#### 响应

返回管理员用户管理页面。

---

### 5.10 切换用户状态

- **URL**: `/admin/user/status/{id}`
- **Method**: `GET`

#### 请求参数

| 参数名 | 类型 | 必填 | 说明 |
|--------|------|------|------|
| id | Integer | 是 | 用户ID（路径参数） |

#### 响应

切换成功后重定向到用户列表页面。

---

## 6. 状态码说明

| 状态码 | 说明 |
|--------|------|
| 200 | 请求成功 |
| 400 | 请求参数错误 |
| 403 | 无权限访问 |
| 404 | 资源不存在 |
| 500 | 服务器内部错误 |

---

## 7. 数据模型

### 7.1 ApiResponse（统一响应）

| 字段名 | 类型 | 说明 |
|--------|------|------|
| code | Integer | 状态码 |
| message | String | 提示信息 |
| data | T | 数据内容 |

### 7.2 User（用户）

| 字段名 | 类型 | 说明 |
|--------|------|------|
| id | Integer | 用户ID |
| username | String | 用户名 |
| password | String | 密码（BCrypt加密） |
| role | Integer | 角色：0-普通用户，1-管理员 |
| email | String | 邮箱 |
| phone | String | 手机号 |
| avatar | String | 头像URL |
| status | Integer | 状态：0-禁用，1-正常 |
| createTime | DateTime | 创建时间 |
| updateTime | DateTime | 更新时间 |

### 7.3 Vegetable（蔬菜）

| 字段名 | 类型 | 说明 |
|--------|------|------|
| id | Integer | 蔬菜ID |
| name | String | 蔬菜名称 |
| nameEn | String | 英文名称 |
| category | String | 分类 |
| image | String | 图片URL |
| nutrition | String | 营养成分 |
| benefits | String | 功效作用 |
| selectionTips | String | 挑选技巧 |
| storageMethod | String | 储存方法 |
| description | String | 描述 |
| status | Integer | 状态 |

### 7.4 Recipe（菜谱）

| 字段名 | 类型 | 说明 |
|--------|------|------|
| id | Integer | 菜谱ID |
| name | String | 菜谱名称 |
| vegetableId | Integer | 关联蔬菜ID |
| image | String | 菜谱图片 |
| difficulty | String | 难度 |
| time | String | 制作时间 |
| servings | Integer | 份量 |
| ingredients | String | 食材清单（JSON） |
| steps | String | 制作步骤（JSON） |
| tips | String | 小贴士 |
| status | Integer | 状态 |

### 7.5 BrowseHistory（浏览记录）

| 字段名 | 类型 | 说明 |
|--------|------|------|
| id | Integer | 记录ID |
| userId | Integer | 用户ID |
| vegetableId | Integer | 蔬菜ID |
| browseTime | DateTime | 浏览时间 |

---

## 8. 接口调用示例

### 8.1 Android (OkHttp)

```java
// 获取蔬菜列表
public static void getVegetables(final ApiCallback callback) {
    Request request = new Request.Builder()
        .url(BASE_URL + "/api/recognition/vegetables")
        .get()
        .build();
    
    client.newCall(request).enqueue(new Callback() {
        @Override
        public void onResponse(Call call, Response response) {
            callback.onSuccess(response.body().string());
        }
        @Override
        public void onFailure(Call call, IOException e) {
            callback.onError(e.getMessage());
        }
    });
}

// 修改密码
public static void changePassword(int userId, String oldPassword, String newPassword, final ApiCallback callback) {
    RequestBody body = new FormBody.Builder()
        .add("userId", String.valueOf(userId))
        .add("oldPassword", oldPassword)
        .add("newPassword", newPassword)
        .build();
    
    Request request = new Request.Builder()
        .url(BASE_URL + "/api/user/changePassword")
        .post(body)
        .build();
    
    client.newCall(request).enqueue(callback);
}
```

### 8.2 curl

```bash
# 获取蔬菜列表
curl http://localhost:8080/api/recognition/vegetables

# 搜索蔬菜
curl "http://localhost:8080/api/recognition/search?keyword=西红柿"

# 获取蔬菜详情
curl http://localhost:8080/api/recognition/detail/西红柿

# 用户登录
curl -X POST http://localhost:8080/auth/login \
  -d "username=admin" \
  -d "password=123456" \
  -d "role=1"
```

---

## 9. 注意事项

1. **认证方式**：
   - 管理员接口需要先登录，Session中保存用户信息
   - API接口无需认证，直接调用

2. **数据格式**：
   - 菜谱的 `ingredients` 和 `steps` 字段为JSON字符串格式
   - 前端需要解析JSON后展示

3. **浏览记录**：
   - 系统自动保存用户最近3条浏览记录
   - 重复浏览同一蔬菜会更新时间

4. **图片URL**：
   - 图片使用外部图床链接
   - 建议使用Glide等图片加载库处理

---

**文档版本**: v2.0  
**更新日期**: 2026-03-04  
**作者**: C9888
