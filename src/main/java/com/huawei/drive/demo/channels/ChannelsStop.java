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

package com.huawei.drive.demo.channels;

import com.alibaba.fastjson.JSONObject;
import com.huawei.drive.demo.utils.HttpClientUtil;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import java.io.IOException;

/**
 *
 *
 * @author drive
 * @since
 */
public class ChannelsStop {

    public static void main(String[] args) throws IOException {

        String url = "https://drive.cloud.hicloud.com/drive/v1/channels/stop";
        String access_token = "CF3mL4d3n515GWr+NIuuZWwvyInGcUSq7PvN2AYZUnnsrJIArWAuwXLkZB9u4Hlt9gGy8LI4fYk2FXeEMghAUgueN3HblOp+oSbjTcmm0w4I85GbMCCwmg==";

        Boolean isStop = false;
        isStop = channelsStop(url, access_token);

        System.out.println(isStop);
    }

    private static Boolean channelsStop(String url, String access_token) throws IOException {
        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader("Authorization","Bearer " + access_token);
        httpPost.setHeader("Content-Type", "application/json");
        httpPost.setHeader("Accept", "application/json");

        JSONObject jsonParam = new JSONObject();//You can get every value of jsonParam from ChangesSubscribe
        jsonParam.put("resourceId", "MTIzODc5MzU2NTkxNDc5MjMyMA");
        jsonParam.put("category", "api#channel");
        jsonParam.put("expirationTime", "1575363300000");
        jsonParam.put("id", "46TMU");
        jsonParam.put("userToken", "Qeqqn");
        jsonParam.put("type", "web_hook");
        jsonParam.put("url", "https://www.huawei.com");
        jsonParam.put("resourceUri", "https://drive.hicloud.com/drive/v3/changes?pageSize=10&pageToken=244021115121339392&fields=*&alt=json");

        StringEntity entity = new StringEntity(jsonParam.toString());
        entity.setContentType("application/json");
        httpPost.setEntity(entity);

        CloseableHttpResponse response = HttpClientUtil.getClient().execute(httpPost);

        try {
            if(response.getStatusLine().getStatusCode() == 204){
                return true;
            }
            return false;
        }finally {
            response.close();
        }
    }
}
