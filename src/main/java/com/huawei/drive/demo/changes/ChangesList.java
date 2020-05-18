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
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 *
 *
 * @author drive
 * @since
 */
public class ChangesList {
    public static void main(String[] args) throws IOException {

        String url = "https://drive.cloud.hicloud.com/drive/v1/changes";
        String cursor = "244021115121339392";//get cursor from ChangesGetStartCursor API
        String access_token = "CF3mIRW1G2Ljt8qvDpZuNW0d6lNHXNT4V61o3/XLHIbcI54AQSc8wx2XAuMvOFEFFIwIvWjGitFRV3UCXWiUnuzCx0b5x/4Pq31BJpo4HK46G8l+k2DuEQ==";

        JSONObject changesInfo = changesList(url, access_token, cursor);
        System.out.println(changesInfo.toJSONString());
    }

    private static JSONObject changesList(String url, String access_token, String cursor) throws IOException {
        StringBuilder stringBuilder = new StringBuilder("");
        stringBuilder.append("?").append("fields=*").append("&").append("cursor").append("=").append(cursor);
        HttpGet httpGet = new HttpGet(url + stringBuilder);
        httpGet.setHeader("Authorization","Bearer " + access_token);
        CloseableHttpResponse response = HttpClientUtil.getClient().execute(httpGet);

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
