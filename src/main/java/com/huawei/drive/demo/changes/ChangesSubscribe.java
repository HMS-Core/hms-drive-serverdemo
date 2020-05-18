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

package com.huawei.drive.demo.changes;

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
public class ChangesSubscribe {
    public static void main(String[] args) throws IOException {

        String url = "https://drive.cloud.hicloud.com/drive/v1/changes/subscribe";
        String cursor = "263696633739381760";//get cursor from ChangesGetStartCursor API
        String access_token = "CV4J+imzJ4fPpQ20ZN6wxBuGx0ncFnfDkXaODQsy5U0QbphRDEkeLneTnwybLVyiGeChaQhiFIdRq75SPD5oIRoTDBGqWE4qLC5FJtjVKL48z6PS4XLH3g==";

        JSONObject changesInfo = changesSubscribe(url, access_token, cursor);
        System.out.println(changesInfo.toJSONString());
    }

    private static JSONObject changesSubscribe(String url, String access_token, String cursor) throws IOException {
        StringBuilder stringBuilder = new StringBuilder("");
        stringBuilder.append("?").append("fields=*").append("&").append("cursor").append("=").append(cursor);
        HttpPost httpPost = new HttpPost(url + stringBuilder);
        httpPost.setHeader("Authorization","Bearer " + access_token);
        httpPost.setHeader("Content-Type", "application/json");
        httpPost.setHeader("Accept", "application/json");

        JSONObject jsonParam = new JSONObject();
        jsonParam.put("id", "46TMU");
        jsonParam.put("type", "web_hook");//default value,can't change
        jsonParam.put("userToken", "Qeqqn");
        jsonParam.put("url", "https://www.huawei.com");//Only support https.
        //jsonParam.put("expirationTime", "1575363300000");//optional.You can set it in 1-7 day or use default.Unix Time

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
