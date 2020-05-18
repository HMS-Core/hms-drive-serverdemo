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

package com.huawei.drive.demo.comments;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huawei.drive.demo.utils.HttpClientUtil;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * 功能描述
 *
 * @author drive
 * @since
 */
public class CommentsUpdate {
    public static void main(String[] args) throws IOException {

        String url = "https://drive.cloud.hicloud.com/drive/v1/files";
        String fileId = "AB0tr6O8DTQAguUs3yPFt3wQ5SDfLg04A";
        String commentId = "BlIuT9Ue4I8qnKGDcmf9MUZf5sCpjOCDK";
        String access_token = "CV5T547SEXpRlyuxqvz+PnRCtnVu784+OEIXmXAafKC+ifA3eJRnM0Ttsv20bJCkrcA+FNi4bU4nKcbERpQxlkMLQ6/Xt1iEQJYrFMyamnnI9JUV+jz4cQ==";

        JSONObject commentInfo = commentsUpdate(url, access_token, fileId, commentId);
        System.out.println(commentInfo.toJSONString());
    }

    private static JSONObject commentsUpdate(String url, String access_token, String fileId, String commentId) throws IOException {
        StringBuilder stringBuilder = new StringBuilder("");
        stringBuilder.append("/").append(fileId).append("/").append("comments").append("/").append(commentId);
        stringBuilder.append("?").append("fields=*");
        HttpPatch httpPatch = new HttpPatch(url + stringBuilder);
        httpPatch.setHeader("Authorization","Bearer " + access_token);
        httpPatch.setHeader("Content-Type", "application/json");
        httpPatch.setHeader("Accept", "application/json");

        JSONObject jsonParam = new JSONObject();
        jsonParam.put("description", "2020/02/25 11:20");

        StringEntity entity = new StringEntity(jsonParam.toString());
        entity.setContentType("application/json");
        httpPatch.setEntity(entity);

        CloseableHttpResponse response = HttpClientUtil.getClient().execute(httpPatch);

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
