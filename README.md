# a springmvc webapp archetype


## 整体层次设计
借鉴《阿里巴巴Java开发手册》提倡的分层结构待见代码框架
- 数据源、外部接口或第三方平台
- 数据层（DAO)
 - 数据访问层，与底层 MySQL、Oracle、Hbase 等进行数据交互。
- 通用处理层（Manager）：
 - 1） 对第三方平台封装的层，预处理返回结果及转化异常信息；
 - 2） 对 Service 层通用能力的下沉，如缓存方案、中间件通用处理；
 - 3） 与 DAO 层交互，对多个 DAO 的组合复用。
- Service层：
 - 具体的业务逻辑
- Web层：
 - 主要是对访问控制进行转发，各类基本参数校验，或者不复用的业务简单处理等。
- 终端显示层：
 - 各个端的模板渲染并执行显示的层。当前主要是 velocity 渲染，JS 渲染，JSP 渲染，移动端展示等。
- 开放接口层
 - 可直接封装 Service 方法暴露成 RPC 接口；通过 Web 封装成 http 接口；进行网关安全控制、流量控制等。

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