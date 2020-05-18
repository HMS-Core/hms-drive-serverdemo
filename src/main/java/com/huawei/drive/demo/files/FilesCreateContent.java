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
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.FileEntity;
import org.apache.http.util.EntityUtils;

import java.io.File;

import java.io.IOException;

/**
 *
 *
 * @author drive
 * @since
 */
public class FilesCreateContent {
    public static void main(String[] args) throws IOException {

        String url = "https://drive.cloud.hicloud.com/upload/drive/v1/files";
        String access_token = "CV4EG6VAnvL8ILp2NsM/fLJNjjLXRxUsljMZnFmH3iuAyIO0n/o0ZS9JC69YF0eUyDgFLX9q4k4+qhacuEvmrGiXMSMnhWvK965gnEUb2ATXLgJ269beh5VNdwYJKNIN2+0=";

        JSONObject fileInfo = filesCreateContent(url, access_token);
        System.out.println(fileInfo.toJSONString());
    }

    private static JSONObject filesCreateContent(String url, String access_token) throws IOException {
        StringBuilder stringBuilder = new StringBuilder("");
        stringBuilder.append("?").append("fields=*").append("&").append("uploadType=content");
        HttpPost httpPost = new HttpPost(url + stringBuilder);
        httpPost.setHeader("Authorization","Bearer " + access_token);
        httpPost.setHeader("Content-Type", "image/jpeg");
        httpPost.setHeader("Accept", "application/json");

        File file = new File("C:\\Users\\z00502234\\Pictures\\Saved Pictures\\8.jpg");//The path of your local file
        if(!file.exists()){
            System.out.print("File not exist");
            return null;
        }
        HttpEntity entity = new FileEntity(file);
        httpPost.setEntity(entity);


        CloseableHttpResponse response = HttpClientUtil.getClient().execute(httpPost);

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
