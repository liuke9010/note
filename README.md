# note

#### 介绍
网络笔记本项目

#### 软件架构
SpringBoot版本：2.1.6.RELEASE

MySQL版本：5.7.17


#### 安装教程

1. 创建application-dev.properties配置数据库连接信息
2. 创建数据库并导入数据结构note.sql
3. 启动：
    本地：建议使用IDEA打开项目，运行启动类
    
    服务器部署：执行启动脚本start.sh，脚本文件在doc中。
    注意：需要修改static/scripts/basepath.js中的根路径为当前服务器的IP
    var basepath="http://localhost:8086/note";换掉localhost

#### 使用说明
    本地访问登录页：http://localhost:8086/note/login

#### 参与贡献

1. Fork 本仓库
2. 新建 Feat_xxx 分支
3. 提交代码
4. 新建 Pull Request


#### 码云特技

1. 使用 Readme\_XXX.md 来支持不同的语言，例如 Readme\_en.md, Readme\_zh.md
2. 码云官方博客 [blog.gitee.com](https://blog.gitee.com)
3. 你可以 [https://gitee.com/explore](https://gitee.com/explore) 这个地址来了解码云上的优秀开源项目
4. [GVP](https://gitee.com/gvp) 全称是码云最有价值开源项目，是码云综合评定出的优秀开源项目
5. 码云官方提供的使用手册 [https://gitee.com/help](https://gitee.com/help)
6. 码云封面人物是一档用来展示码云会员风采的栏目 [https://gitee.com/gitee-stars/](https://gitee.com/gitee-stars/)