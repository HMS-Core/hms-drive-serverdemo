# HMS Drivekit Serverdemo
English | [中文](https://github.com/HMS-Core/hms-drive-serverdemo/blob/master/README_ZH.md)
## Table of Contents

 * [Introduction](#introduction)
 * [Installation](#installation)
 * [Configuration ](#configuration )
 * [Supported Environments](#supported-environments)
 * [Getting Started](#getting-started)
 * [License](#license)


## Introduction

In this demo, requests/responses are sent/received by using the HTTP client. All open APIs provided by HUAWEI Drive Kit ("Dive Kit") can be accessed by this demo. 
Note: Access_token (AT) needs to be obtained by using HUAWEI Account Kit.

## Installation
Before using HUAWEI Drive Kit Server sample code, check whether the JAVA and Maven environment has been installed.
Decompress the HUAWEI Drive Kit Server sample code package.
The steps to run the Drive service sample code are as follows:
1. Import project to your JAVA Integrated Development Environment.
2. Use Maven to compile this project. 
3. Obtain parameters in the Main function of each Java file in advance. For example, obtain the AT through the HMS SDK.
See details : [HUAWEI Drive Service Development Preparation](https://developer.huawei.com/consumer/en/doc/development/HMSCore-Guides/server-dev-0000001050039664)


## Supported Environments 
Java 1.8 or a later version is recommended.  
Maven 3.6.1 is recommended.


## Configuration 
No additional configuration is required.

## Getting Started 
Currently, the core capabilities of Drive Kit include uploading, downloading, deleting, recycling, and searching for files in Drive as well as querying and monitoring file changes.
1. Obtain parameters in the Main function of each Java file in advance. For example, obtain the AT through the HMS SDK.
2. Call the required APIs. Some APIs depend on each other, for example:
Before calling changesSubscribe and ChangesList, you need to call ChangesGetStartCursor to get the query parameter startCursor.
Before calling ChannelsStop, you need to create a valid channel for Watch.
Before calling FilesResume, call FilesCreateResume or FilesUpdateResume to obtain uploadId and serverId.
Before calling FilesCopy, FilesDelete, FilesGet, FilesUpdate, FilesUpdate*, FilesSubscribe, or FilesGet*, you need to call FilesCreate* to create a file and get fileId.

## Question or issues
If you want to evaluate more about HMS Core,
[r/HMSCore on Reddit](https://www.reddit.com/r/HuaweiDevelopers/
) is for you to keep up with latest news about HMS Core, and to exchange insights with other developers.

If you have questions about how to use HMS samples, try the following options:
- [Stack Overflow](https://stackoverflow.com/questions/tagged/huawei-mobile-services) is the best place for any programming questions. Be sure to tag your question with 
`huawei-mobile-services`.
- [Huawei Developer Forum](https://forums.developer.huawei.com/forumPortal/en/home?fid=0101187876626530001) HMS Core Module is great for general questions, or seeking recommendations and opinions.

If you run into a bug in our samples, please submit an [issue](https://github.com/HMS-Core/hms-drive-serverdemo/issues) to the Repository. Even better you can submit a [Pull Request](https://github.com/HMS-Core/hms-drive-serverdemo/pulls) with a fix.

##  License
Drivekit server Java sample is licensed under the [Apache License, version 2.0](http://www.apache.org/licenses/LICENSE-2.0).
