/*
*   Copyright 2020. Huawei Technologies Co., Ltd. All rights reserved.

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
*/

package com.huawei.drive.demo.smallThumbnail;

import com.huawei.drive.demo.utils.HttpClientUtil;
import org.apache.commons.io.FileUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 *
 *
 * @author drive
 * @since
 */
public class SmallThumbnailGet {
    public static void main(String[] args) throws IOException {

        String url = "https://drive.cloud.hicloud.com/drive/v1/smallThumbnails";
        String fileId = "AACUf44uK7wAg6pECq0Vh4QNqkgKoCuwA";
        String access_token = "CF3qHUcDOG3Rq+aCBdoTbdxTQYmIrt1c8xeVvh7Tquare/8VD+7YZFNxzuz5ZdhDrulEWOZVECrpQbVOqVMABeKe9p4wjL4UxskLw7KJKvs0fvZEJqjAKQ==";
        smallThumbnailGet(url, access_token, fileId);
    }

    private static void smallThumbnailGet(String url, String access_token, String fileId) throws IOException {
        StringBuilder stringBuilder = new StringBuilder("");
        stringBuilder.append("/").append(fileId);
        stringBuilder.append("?").append("form=content");
        HttpGet httpGet = new HttpGet(url + stringBuilder);
        httpGet.setHeader("Authorization","Bearer " + access_token);
        CloseableHttpResponse response = HttpClientUtil.getClient().execute(httpGet);

        try {
            HttpEntity responseEntity = response.getEntity();
            InputStream inputStream=responseEntity.getContent();
            FileUtils.copyToFile(inputStream, new File("D://smallThumbnail.jpg"));
        }finally {
            response.close();
        }
    }
}
