# a springmvc webapp archetype


## 已包含
- 基本的web项目分层结构
- mybatis
- spring mvc
- 多环境profile


## TODO
借鉴：https://github.com/paranoidq/funiture，实现其中的各项功能模板，并实现高效的可插拔可选择

web基础功能：
- api网关、rest
- cookie、session管理组件化
- 动态刷新配置文件
- 监控和日志AOP


工具：
- json
-


前端：
- 文件上传
- pdf展示
- 富文本编辑器
-



日志：
- 日志组件可插拔
- 日志输出到不同的源



数据访问
- mysql、oracle
- mybatis plus
- 分页


安全：
- 登陆：常用、验证码、密码找回、第三方登录
- 权限控制：白名单、黑名单、访问控制


高可用：
- 多数据源、读写分离
- 分库分表


高性能：
- redis缓存
- ehcache缓存



## 更高级的功能TODO
- 重写maven-archetype:generate功能，增加参数，实现根据不同的参数，拷贝不同的项目框架和组件