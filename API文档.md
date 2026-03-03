# 移动端蔬菜识别系统 API 接口文档

## 基础信息

- **Base URL**: `http://localhost:8080/api`
- **Content-Type**: `application/json`
- **字符编码**: `UTF-8`

---

## 1. 蔬菜识别模块

### 1.1 蔬菜识别
识别上传的蔬菜图片，返回识别结果。

- **URL**: `/recognition/identify`
- **Method**: `POST`
- **Content-Type**: `multipart/form-data`

#### 请求参数

| 参数名 | 类型 | 必填 | 说明 |
|--------|------|------|------|
| image | File | 是 | 蔬菜图片文件（支持jpg、png格式） |

#### 响应示例

```json
{
  "code": 200,
  "message": "识别成功",
  "data": {
    "vegetableName": "西红柿",
    "confidence": 0.95,
    "vegetableId": 1
  }
}
```

#### 字段说明

| 字段名 | 类型 | 说明 |
|--------|------|------|
| code | Integer | 状态码，200表示成功 |
| message | String | 提示信息 |
| data.vegetableName | String | 识别出的蔬菜名称 |
| data.confidence | Double | 识别置信度（0-1之间） |
| data.vegetableId | Integer | 蔬菜ID |

---

### 1.2 批量识别
同时识别多张蔬菜图片。

- **URL**: `/recognition/batch-identify`
- **Method**: `POST`
- **Content-Type**: `multipart/form-data`

#### 请求参数

| 参数名 | 类型 | 必填 | 说明 |
|--------|------|------|------|
| images | File[] | 是 | 多张蔬菜图片文件 |

#### 响应示例

```json
{
  "code": 200,
  "message": "批量识别成功",
  "data": [
    {
      "index": 0,
      "vegetableName": "西红柿",
      "confidence": 0.95
    },
    {
      "index": 1,
      "vegetableName": "黄瓜",
      "confidence": 0.92
    }
  ]
}
```

---

### 1.3 获取蔬菜详细信息
根据蔬菜名称获取详细信息。

- **URL**: `/recognition/detail/{vegetableName}`
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
    "name": "西红柿",
    "nutrition": "富含维生素C、番茄红素、钾等营养素",
    "benefits": "抗氧化、保护心血管、增强免疫力",
    "selectionTips": "选择颜色鲜红、果实饱满、无损伤的",
    "storageMethod": "常温保存或冷藏，避免阳光直射",
    "recommendedRecipes": [
      {
        "name": "西红柿炒蛋",
        "difficulty": "简单"
      },
      {
        "name": "西红柿牛腩汤",
        "difficulty": "中等"
      }
    ]
  }
}
```

---

### 1.4 获取蔬菜营养信息
获取指定蔬菜的营养成分信息。

- **URL**: `/recognition/nutrition/{vegetableName}`
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
    "name": "西红柿",
    "calories": "18千卡/100克",
    "protein": "0.9克/100克",
    "fat": "0.2克/100克",
    "carbohydrates": "3.9克/100克",
    "fiber": "1.2克/100克",
    "vitaminC": "14毫克/100克"
  }
}
```

---

### 1.5 获取推荐菜谱
获取指定蔬菜的推荐菜谱列表。

- **URL**: `/recognition/recipes/{vegetableName}`
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
      "name": "西红柿炒蛋",
      "difficulty": "简单",
      "time": "15分钟",
      "image": "http://example.com/images/recipe1.jpg"
    },
    {
      "id": 2,
      "name": "西红柿牛腩汤",
      "difficulty": "中等",
      "time": "90分钟",
      "image": "http://example.com/images/recipe2.jpg"
    }
  ]
}
```

---

### 1.6 获取菜谱详情
获取指定菜谱的详细信息。

- **URL**: `/recognition/recipe/detail/{recipeId}`
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
    "name": "西红柿炒蛋",
    "description": "经典家常菜，营养丰富，制作简单",
    "difficulty": "简单",
    "time": "15分钟",
    "servings": 2,
    "ingredients": [
      {
        "name": "西红柿",
        "amount": "2个"
      },
      {
        "name": "鸡蛋",
        "amount": "3个"
      },
      {
        "name": "食用油",
        "amount": "适量"
      }
    ],
    "steps": [
      "西红柿洗净切块，鸡蛋打散备用",
      "热锅凉油，倒入蛋液炒至凝固盛出",
      "锅中留底油，放入西红柿翻炒出汁",
      "加入炒好的鸡蛋，调味即可出锅"
    ],
    "tips": "西红柿炒出汁后再加鸡蛋，口感更好"
  }
}
```

---

### 1.7 获取热门蔬菜
获取搜索热度最高的蔬菜列表。

- **URL**: `/recognition/popular`
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
      "image": "http://example.com/images/tomato.jpg",
      "searchCount": 1250
    },
    {
      "id": 2,
      "name": "黄瓜",
      "image": "http://example.com/images/cucumber.jpg",
      "searchCount": 980
    }
  ]
}
```

---

### 1.8 搜索蔬菜
根据关键词搜索蔬菜。

- **URL**: `/recognition/search`
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
      "image": "http://example.com/images/tomato.jpg"
    }
  ]
}
```

---

### 1.9 获取识别历史
获取用户的蔬菜识别历史记录。

- **URL**: `/recognition/history/{userId}`
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
      "image": "http://example.com/history/1.jpg",
      "recognizeTime": "2024-03-04 10:30:00",
      "confidence": 0.95
    },
    {
      "id": 2,
      "vegetableName": "黄瓜",
      "image": "http://example.com/history/2.jpg",
      "recognizeTime": "2024-03-03 15:20:00",
      "confidence": 0.92
    }
  ]
}
```

---

## 2. 用户模块

### 2.1 用户注册

- **URL**: `/user/register`
- **Method**: `POST`

#### 请求参数

| 参数名 | 类型 | 必填 | 说明 |
|--------|------|------|------|
| username | String | 是 | 用户名 |
| password | String | 是 | 密码 |
| email | String | 否 | 邮箱 |
| phone | String | 否 | 手机号 |

#### 响应示例

```json
{
  "code": 200,
  "message": "注册成功",
  "data": {
    "userId": 1,
    "username": "张三"
  }
}
```

---

### 2.2 用户登录

- **URL**: `/user/login`
- **Method**: `POST`

#### 请求参数

| 参数名 | 类型 | 必填 | 说明 |
|--------|------|------|------|
| username | String | 是 | 用户名 |
| password | String | 是 | 密码 |

#### 响应示例

```json
{
  "code": 200,
  "message": "登录成功",
  "data": {
    "token": "eyJhbGciOiJIUzI1NiIs...",
    "userId": 1,
    "username": "张三"
  }
}
```

---

### 2.3 获取用户信息

- **URL**: `/user/info/{userId}`
- **Method**: `GET`

#### 响应示例

```json
{
  "code": 200,
  "message": "查询成功",
  "data": {
    "userId": 1,
    "username": "张三",
    "email": "zhangsan@example.com",
    "phone": "13800138000",
    "avatar": "http://example.com/avatar/1.jpg",
    "createTime": "2024-03-01 10:00:00"
  }
}
```

---

### 2.4 修改用户信息

- **URL**: `/user/update`
- **Method**: `PUT`

#### 请求参数

| 参数名 | 类型 | 必填 | 说明 |
|--------|------|------|------|
| userId | Integer | 是 | 用户ID |
| email | String | 否 | 邮箱 |
| phone | String | 否 | 手机号 |
| avatar | String | 否 | 头像URL |

---

### 2.5 修改密码

- **URL**: `/user/password`
- **Method**: `PUT`

#### 请求参数

| 参数名 | 类型 | 必填 | 说明 |
|--------|------|------|------|
| userId | Integer | 是 | 用户ID |
| oldPassword | String | 是 | 旧密码 |
| newPassword | String | 是 | 新密码 |

---

### 2.6 上传头像

- **URL**: `/user/avatar`
- **Method**: `POST`
- **Content-Type**: `multipart/form-data`

#### 请求参数

| 参数名 | 类型 | 必填 | 说明 |
|--------|------|------|------|
| userId | Integer | 是 | 用户ID |
| avatar | File | 是 | 头像图片 |

---

## 3. 收藏模块

### 3.1 收藏蔬菜/菜谱

- **URL**: `/favorite/add`
- **Method**: `POST`

#### 请求参数

| 参数名 | 类型 | 必填 | 说明 |
|--------|------|------|------|
| userId | Integer | 是 | 用户ID |
| type | Integer | 是 | 类型：1-蔬菜，2-菜谱 |
| targetId | Integer | 是 | 目标ID |

---

### 3.2 取消收藏

- **URL**: `/favorite/cancel`
- **Method**: `DELETE`

#### 请求参数

| 参数名 | 类型 | 必填 | 说明 |
|--------|------|------|------|
| userId | Integer | 是 | 用户ID |
| type | Integer | 是 | 类型：1-蔬菜，2-菜谱 |
| targetId | Integer | 是 | 目标ID |

---

### 3.3 获取收藏列表

- **URL**: `/favorite/list/{userId}`
- **Method**: `GET`

#### 请求参数

| 参数名 | 类型 | 必填 | 说明 |
|--------|------|------|------|
| userId | Integer | 是 | 用户ID（路径参数） |
| type | Integer | 否 | 类型：1-蔬菜，2-菜谱，不传则返回全部 |

---

## 4. 状态码说明

| 状态码 | 说明 |
|--------|------|
| 200 | 请求成功 |
| 400 | 请求参数错误 |
| 401 | 未授权，需要登录 |
| 403 | 禁止访问 |
| 404 | 资源不存在 |
| 500 | 服务器内部错误 |
| 1001 | 用户名已存在 |
| 1002 | 用户名或密码错误 |
| 1003 | 图片格式不支持 |
| 1004 | 识别失败 |

---

## 5. 数据模型

### 5.1 User（用户）

| 字段名 | 类型 | 说明 |
|--------|------|------|
| userId | Integer | 用户ID |
| username | String | 用户名 |
| password | String | 密码（加密存储） |
| email | String | 邮箱 |
| phone | String | 手机号 |
| avatar | String | 头像URL |
| createTime | String | 创建时间 |
| updateTime | String | 更新时间 |

### 5.2 Vegetable（蔬菜）

| 字段名 | 类型 | 说明 |
|--------|------|------|
| id | Integer | 蔬菜ID |
| name | String | 蔬菜名称 |
| image | String | 蔬菜图片 |
| nutrition | String | 营养成分 |
| benefits | String | 功效作用 |
| selectionTips | String | 挑选技巧 |
| storageMethod | String | 储存方法 |

### 5.3 Recipe（菜谱）

| 字段名 | 类型 | 说明 |
|--------|------|------|
| id | Integer | 菜谱ID |
| name | String | 菜谱名称 |
| description | String | 描述 |
| image | String | 菜谱图片 |
| difficulty | String | 难度：简单/中等/困难 |
| time | String | 制作时间 |
| servings | Integer | 份量（几人份） |
| ingredients | List | 食材清单 |
| steps | List | 制作步骤 |
| tips | String | 小贴士 |

### 5.4 RecognitionRecord（识别记录）

| 字段名 | 类型 | 说明 |
|--------|------|------|
| id | Integer | 记录ID |
| userId | Integer | 用户ID |
| vegetableName | String | 识别出的蔬菜名称 |
| image | String | 识别的图片 |
| confidence | Double | 置信度 |
| recognizeTime | String | 识别时间 |

---

## 6. 接口调用示例

### 6.1 使用curl测试识别接口

```bash
curl -X POST \
  http://localhost:8080/api/recognition/identify \
  -H 'Content-Type: multipart/form-data' \
  -F 'image=@/path/to/tomato.jpg'
```

### 6.2 使用JavaScript调用

```javascript
// 识别蔬菜
async function identifyVegetable(imageFile) {
  const formData = new FormData();
  formData.append('image', imageFile);
  
  const response = await fetch('http://localhost:8080/api/recognition/identify', {
    method: 'POST',
    body: formData
  });
  
  return await response.json();
}

// 获取蔬菜详情
async function getVegetableDetail(name) {
  const response = await fetch(`http://localhost:8080/api/recognition/detail/${name}`);
  return await response.json();
}
```

---

## 7. 注意事项

1. **图片上传限制**：
   - 单张图片大小不超过 5MB
   - 支持格式：jpg、jpeg、png
   - 建议分辨率：不低于 640x480

2. **认证方式**：
   - 除登录、注册外，其他接口需要在请求头中携带 Token
   - Header: `Authorization: Bearer {token}`

3. **错误处理**：
   - 所有接口统一返回 JSON 格式
   - 发生错误时，code 不为 200，message 包含错误信息

4. **图片URL**：
   - 图片URL为临时链接，有效期24小时
   - 建议客户端缓存图片或使用CDN加速

---

**文档版本**: v1.0  
**更新日期**: 2024-03-04  
**作者**: 蔬菜识别系统开发团队
