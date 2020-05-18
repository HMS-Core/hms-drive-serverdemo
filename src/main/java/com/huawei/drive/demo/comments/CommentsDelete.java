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

import com.huawei.drive.demo.utils.HttpClientUtil;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;

import java.io.IOException;

/**
 * 功能描述
 *
 * @author drive
 * @since
 */
public class CommentsDelete {
    public static void main(String[] args) throws IOException {

        String url = "https://drive.cloud.hicloud.com/drive/v1/files";
        String fileId = "AB0tr6O8DTQAguUs3yPFt3wQ5SDfLg04A";
        String commentId = "BlIuT9Ue4I8qnKGDcmf9MUZf5sCpjOCDK";
        String access_token = "CV5T547SEXpRlyuxqvz+PnRCtnVu784+OEIXmXAafKC+ifA3eJRnM0Ttsv20bJCkrcA+FNi4bU4nKcbERpQxlkMLQ6/Xt1iEQJYrFMyamnnI9JUV+jz4cQ==";

        Boolean isDelete = false;
        isDelete = commentDelete(url, access_token, fileId, commentId);

        System.out.println(isDelete);
    }

    private static Boolean commentDelete(String url, String access_token, String fileId, String commentId) throws IOException {
        StringBuilder stringBuilder = new StringBuilder("");
        stringBuilder.append("/").append(fileId).append("/").append("comments").append("/").append(commentId);
        HttpDelete httpDelete = new HttpDelete(url + stringBuilder);
        httpDelete.setHeader("Authorization","Bearer " + access_token);

        CloseableHttpResponse response = HttpClientUtil.getClient().execute(httpDelete);

        try {
            if(response.getStatusLine().getStatusCode() == 204){//httpStatus 204 stands for success
                return true;
            }
            return false;
        }finally {
            response.close();
        }
    }
}
