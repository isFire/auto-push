# 消息推送平台

一个基于Spring Boot 3和Vue 3开发的消息推送管理平台，支持多渠道消息推送和数据统计。

## 技术栈

### 前端
- Vue 3
- TypeScript
- Ant Design Vue
- ECharts/AntV G2
- Axios

### 后端
- Spring Boot 3.x
- Spring WebFlux (响应式编程)
- Project Reactor

## 项目结构

```
auto-push
├── auto-push-frontend/      # 前端Vue项目
│   ├── src/
│   │   ├── components/      # 组件
│   │   ├── assets/          # 静态资源
│   │   └── ...
│   └── ...
├── src/                    # 后端Spring Boot项目
│   ├── main/
│   │   ├── java/com/zhechengdata/autopush/
│   │   │   ├── controller/  # 控制器
│   │   │   ├── service/     # 服务
│   │   │   ├── model/       # 模型
│   │   │   └── config/      # 配置
│   │   └── resources/       # 资源文件
│   └── ...
└── ...
```

## 功能特性

- 多渠道消息推送(邮件、企业微信、钉钉、飞书、Webhook等)
- 消息发送统计和监控
- 渠道分布分析
- 任务管理
- 响应式API接口

## 本地开发

### 启动后端

```bash
./gradlew bootRun
```

### 启动前端

```bash
cd auto-push-frontend
npm install
npm run dev
```

## API文档

### 任务管理API

- `GET /api/tasks` - 获取所有任务
- `GET /api/tasks/{id}` - 获取特定任务
- `POST /api/tasks` - 创建新任务
- `PUT /api/tasks/{id}` - 更新任务
- `DELETE /api/tasks/{id}` - 删除任务
- `POST /api/tasks/{id}/execute` - 执行任务

### 统计API

- `GET /api/statistics/dashboard` - 获取仪表盘统计数据
- `GET /api/statistics/daily` - 获取每日发送统计
- `GET /api/statistics/channel-distribution` - 获取渠道分布统计
