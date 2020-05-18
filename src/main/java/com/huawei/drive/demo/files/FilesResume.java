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
public class FilesResume {
    public static void main(String[] args) throws IOException {

        String url = "https://drive.cloud.hicloud.com/upload/drive/v1";
        String serverId = "fc7300dbe0280d39";
        String upload_id = "246009705515379712";
        String access_token = "CF3pvqiyaVUXfclpdiztAQIRcW/gGlZzV0HAiJBRe4ClGDYseeS6/PA2FMdMvfSc1vMKwzKJn0ZNPQZglHbpjQvscQA8yj0wmlMz2lhESZoa+pruIHCYQw==";

        JSONObject fileInfo = filesResume(url, access_token, serverId, upload_id);
        System.out.println(fileInfo.toJSONString());
    }


    private static JSONObject filesResume(String url, String access_token, String serverId, String upload_id) throws IOException {
        StringBuilder stringBuilder = new StringBuilder("");
        stringBuilder.append("/").append(serverId).append("/").append("files");
        stringBuilder.append("?").append("fields=*");
        stringBuilder.append("&").append("uploadType=resume");
        stringBuilder.append("&").append("uploadId=").append(upload_id);
        HttpPut httpPut = new HttpPut(url + stringBuilder);
        httpPut.setHeader("Authorization","Bearer " + access_token);
        httpPut.setHeader("Content-Type", "application/json;charset=UTF-8");
        httpPut.setHeader("Content-Range", "bytes 0-8022/8023");

        File file = new File("C:\\Users\\z00502234\\Pictures\\Saved Pictures\\2.jpg");
        if(!file.exists()){
            System.out.print("File not exist");
            return null;
        }

        HttpEntity entity = new FileEntity(file);

        httpPut.setEntity(entity);

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

