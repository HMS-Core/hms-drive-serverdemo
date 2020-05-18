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

package com.huawei.drive.demo.files;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huawei.drive.demo.utils.HttpClientUtil;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

/**
 *
 *
 * @author drive
 * @since
 */
public class FilesUpdatePutcontent {
    public static void main(String[] args) throws IOException {

        String url = "https://drive.cloud.hicloud.com/upload/drive/v1/files";
        String fileId = "AACeJVrcK7wAg6Ae3l8Vh4QNoBLeUiuwA";
        String access_token = "CF3pA9N/bBr1lFcpPsK7v1W9gtIScpAxfsR0VDiTsCj8TIZwQG4yGBaTYHTQgxVXoVcYgXw+hAhMTE904csrrkQ6v7+RhRm8C6yIEOnDi3YteB3bvyj40w==";

        JSONObject fileInfo = filesUpdatePutcontent(url, access_token, fileId);
        System.out.println(fileInfo.toJSONString());
    }

    private static JSONObject filesUpdatePutcontent(String url, String access_token, String fileId) throws IOException {
        StringBuilder stringBuilder = new StringBuilder("");
        stringBuilder.append("/").append(fileId);
        stringBuilder.append("?").append("fields=*");
        stringBuilder.append("&").append("uploadType=content");
        HttpPut httpPut = new HttpPut(url + stringBuilder);
        httpPut.setHeader("Authorization","Bearer " + access_token);
        httpPut.setHeader("Content-Type", "image/jpeg");
        httpPut.setHeader("Accept", "application/json");
        httpPut.setHeader("X-Upload-Content-Type", "image/jpeg");

        File file = new File("C:\\Users\\z00502234\\Pictures\\Saved Pictures\\5.jpg");
        if(!file.exists()){
            System.out.print("File not exist");
            return null;
        }
        FileBody bin = new FileBody(file, ContentType.create("image/jpeg", Consts.UTF_8));
        HttpEntity entity = MultipartEntityBuilder
            .create()
            .setCharset(Charset.forName("utf-8"))
            .addPart("file", bin)
            .build();
        httpPut.setEntity(entity);
        RequestConfig requestConfig = RequestConfig.custom()
            .setConnectTimeout(50000).setConnectionRequestTimeout(10000)
            .setSocketTimeout(50000).build();//set connection time
        httpPut.setConfig(requestConfig);

        CloseableHttpResponse response = HttpClientUtil.getClient().execute(httpPut);

        try {
            HttpEntity responseEntity = response.getEntity();
            String ret = responseEntity != null ? EntityUtils.toString(responseEntity) : null;
            JSONObject jsonObject = (JSONObject) JSON.parse(ret);
            EntityUtils.consume(responseEntity);
            return jsonObject;
        }finally {
            response.close();
        }
    }
}
