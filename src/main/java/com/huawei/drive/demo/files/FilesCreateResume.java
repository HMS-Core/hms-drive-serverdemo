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
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 *
 *
 * @author drive
 * @since
 */
public class FilesCreateResume {
    public static void main(String[] args) throws IOException {

        String url = "https://drive.cloud.hicloud.com/upload/drive/v1/files";
        String access_token = "CF3pvqiyaVUXfclpdiztAQIRcW/gGlZzV0HAiJBRe4ClGDYseeS6/PA2FMdMvfSc1vMKwzKJn0ZNPQZglHbpjQvscQA8yj0wmlMz2lhESZoa+pruIHCYQw==";

        String fileInfo = filesCreateResume(url, access_token);
        if(fileInfo != null){
            System.out.println(fileInfo);
        }
    }

    private static String filesCreateResume(String url, String access_token) throws IOException {
        StringBuilder stringBuilder = new StringBuilder("");
        stringBuilder.append("?").append("fields=*");
        stringBuilder.append("&").append("uploadType=resume");
        HttpPost httpPost = new HttpPost(url + stringBuilder);
        httpPost.setHeader("Authorization","Bearer " + access_token);
        httpPost.setHeader("Content-Type", "application/json;charset=UTF-8");
        httpPost.setHeader("Accept", "application/json");
        //httpPost.setHeader("X-Upload-Content-Type", "image/jpeg");
        httpPost.setHeader("X-Upload-Content-Length", "8023");

        JSONObject jsonParam = new JSONObject();
        jsonParam.put("fileName", "HWTest001");
        StringEntity entity = new StringEntity(jsonParam.toString());
        entity.setContentType("application/json");
        httpPost.setEntity(entity);

        CloseableHttpResponse response = HttpClientUtil.getClient().execute(httpPost);

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
