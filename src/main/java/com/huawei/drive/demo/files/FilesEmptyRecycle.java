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

import com.huawei.drive.demo.utils.HttpClientUtil;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;

import java.io.IOException;

/**
 *
 *
 * @author drive
 * @since
 */
public class FilesEmptyRecycle {
    public static void main(String[] args) throws IOException {

        String url = "https://drive.cloud.hicloud.com/drive/v1/files/recycle";
        String access_token = "CF3mIRW1G2Ljt8qvDpZuNW0d6lNHXNT4V61o3/XLHIbcI54AQSc8wx2XAuMvOFEFFIwIvWjGitFRV3UCXWiUnuzCx0b5x/4Pq31BJpo4HK46G8l+k2DuEQ==";

        Boolean isTrash = false;
        isTrash = filesEmptyTrash(url, access_token);

        System.out.println(isTrash);
    }

    private static Boolean filesEmptyTrash(String url, String access_token) throws IOException {
        HttpDelete httpDelete = new HttpDelete(url);
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

