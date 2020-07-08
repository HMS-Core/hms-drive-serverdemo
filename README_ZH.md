# HMS Drivekit 服务端示例代码
中文 | [English](https://github.com/HMS-Core/hms-drive-serverdemo)
## 目录

 * [简介](#简介)
 * [安装](#安装)
 * [配置](#配置)
 * [环境支持](#环境支持)
 * [开始](#开始)
 * [License](#license)


## 简介

在本示例代码中, 请求及响应通过HTTP客户端实现收发. HUAWEI Drive Kit ("Dive Kit")开放的所有API接口都可以通过本示例代码实现调用。 
注意: Access_token (AT) 需要通过HUAWEI Account Kit获取.

## 安装
在使用HUAWEI Drive Kit服务端示例代码之前, 需要检查JAVA和MAVEN是否已正确安装。
解压缩HUAWEI Drive Kit服务器示例代码包。
运行Drive服务端示例代码的步骤如下：
1. 将项目导入到您的JAVA集成开发环境中。
2. 使用Maven编译该项目。
3. 预先获取参数填入每个Java文件的Main函数中。例如，通过HMS Core SDK获得AT。
更多详情请参见 : [HUAWEI Drive 服务端开发指南](https://developer.huawei.com/consumer/cn/doc/development/HMSCore-Guides/server-dev-0000001050039664)


## 环境支持 
建议使用Java 1.8 或者更高版本。
建议使用Maven 3.6.1版本。


## 配置 
无特殊配置要求。

## 开始 
当前华为云空间服务（HUAWEI Drive Kit）的核心能力包括文件的上传、下载和搜索，对文件的评论、回复，云端文件变化的查询、推送通知功能等。
1. 预先获取参数填入每个Java文件的Main函数中。例如，通过HMS Core SDK获得AT。
2. 调用接口时，有些接口依赖于其他接口的调用，例如:
在调用changesSubscribe 和 ChangesList接口之前, 你需要先调用ChangesGetStartCursor获取startCursor。
在调用ChannelsStop接口之前,你需要创建一个合法的订阅通道。
在调用FilesResume接口之前, 你需要先调用FilesCreateResume或FilesUpdateResume来获取uploadId和serverId。
在调用FilesCopy, FilesDelete, FilesGet, FilesUpdate, FilesUpdate*, FilesSubscribe, or FilesGet*等接口之前, 你需要先调用FilesCreate*接口来创建文件或目录并获取fileId。


##  License
Drivekit 服务端示例代码基于许可 [Apache License, version 2.0](http://www.apache.org/licenses/LICENSE-2.0).
