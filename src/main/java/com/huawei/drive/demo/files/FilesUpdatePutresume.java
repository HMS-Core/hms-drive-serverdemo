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
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 *
 *
 * @author drive
 * @since
 */
public class FilesUpdatePutresume {
    public static void main(String[] args) throws IOException {

        String url = "https://drive.cloud.hicloud.com/upload/drive/v1/files";
        String fileId = "AACWO19qK7wAg6gA2-kVh4QNqAzb5CuwA";
        String access_token = "CF3p+5TNdpCs0OjI+bP04rVLLe6WJpFWefKlASOzIaDArxxS36ku2TePpHUGzfJAW4C4YO06wB7eIhMdu/MEOsqAkWu0rpNmVcTUsVefUSmt5RUvhAr2oQ==";

        String fileInfo = filesUpdatePutresume(url, access_token, fileId);
        if(fileInfo != null){
            System.out.println(fileInfo);
        }
    }

    private static String filesUpdatePutresume(String url, String access_token, String fileId) throws IOException {
        StringBuilder stringBuilder = new StringBuilder("");
        stringBuilder.append("/").append(fileId);
        stringBuilder.append("?").append("fields=").append("*");
        stringBuilder.append("&").append("uploadType=resume");
        HttpPut httpPut = new HttpPut(url + stringBuilder);
        httpPut.setHeader("Authorization","Bearer " + access_token);
        httpPut.setHeader("Content-Type", "application/json;charset=UTF-8");
        httpPut.setHeader("Accept", "*/*");
        httpPut.setHeader("X-Upload-Content-Length", "49");

        JSONObject jsonParam = new JSONObject();

        jsonParam.put("\"appSettings\"", "{    \"test2\": \"value1\"  }");
        StringEntity entity = new StringEntity(jsonParam.toString());
        httpPut.setEntity(entity);

        CloseableHttpResponse response = HttpClientUtil.getClient().execute(httpPut);

        try {
            if(response.getStatusLine().getStatusCode() == 200){
                String ret = response.getHeaders("Location")[0].getValue();
                return ret;
            }
            HttpEntity responseEntity = response.getEntity();
            String ret = responseEntity != null ? EntityUtils.toString(responseEntity) : null;
            JSONObject jsonObject = (JSONObject) JSON.parse(ret);
            EntityUtils.consume(responseEntity);
            return jsonObject.toJSONString();
        }finally {
            response.close();
        }
    }
}
