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
class FilesCreateMetadata {

    public static void main(String[] args) throws IOException {

        String url = "https://drive.cloud.hicloud.com/drive/v1/files";
        String access_token = "CV4Js+Z/i4U5W9kSoghQA5//o4orkhDLT55++P895MMHqMhNjCv66x3Ti1bhNvSxaueLBQat2Uh9yLRVdrMjwgdmK4ZkrkaPlQD2xoQh7zl1F5woM+LnHQ==";

        JSONObject folderInfo = filesCreateMetadata(url, access_token);
        System.out.println(folderInfo.toJSONString());
    }

    private static JSONObject filesCreateMetadata(String url, String access_token) throws IOException {
        StringBuilder stringBuilder = new StringBuilder("");
        stringBuilder.append("?").append("fields=*");
        HttpPost httpPost = new HttpPost(url + stringBuilder);
        httpPost.setHeader("Authorization","Bearer " + access_token);
        httpPost.setHeader("Content-Type", "application/json");
        httpPost.setHeader("Accept", "application/json");

        JSONObject jsonParam = new JSONObject();
        jsonParam.put("fileName", "HWTest1001");
        jsonParam.put("favorite","true");//optional
        jsonParam.put("description","folder");//optional
        jsonParam.put("mimeType","application/vnd.huawei-apps.folder");
        StringEntity entity = new StringEntity(jsonParam.toString());
        entity.setContentType("application/json");
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
