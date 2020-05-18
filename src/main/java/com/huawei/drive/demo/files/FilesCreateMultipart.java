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
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
/**
 *
 *
 * @author drive
 * @since
 */
public class FilesCreateMultipart {
    public static void main(String[] args) throws IOException {

        String url = "https://drive.cloud.hicloud.com/upload/drive/v1/files";
        String access_token = "CF3oxnU++pa/3HhS2ZLPQFK8WvK1X5Jo76zCnZEI0ClGl8PkYzum8defyvLHlJfxwGaRiuMPikL7vf6DVlJvwnnzzVmV1mwp4UJuVDg9B/EUsT5VJTO3XQ==";

        JSONObject fileInfo = filesCreateMultipart(url, access_token);
        System.out.println(fileInfo.toJSONString());
    }

    private static JSONObject filesCreateMultipart(String url, String access_token) throws IOException {
        StringBuilder stringBuilder = new StringBuilder("");
        stringBuilder.append("?").append("fields=*").append("&").append("uploadType=multipart");
        HttpPost httpPost = new HttpPost(url + stringBuilder);
        httpPost.setHeader("Authorization","Bearer " + access_token);
        httpPost.setHeader("Content-Type", "multipart/related;boundary=OP8XTaXZ0UZs-Sjlefcj2OWskqXWwVQO");
        httpPost.setHeader("Accept", "application/json");

        File file = new File("C:\\Users\\z00502234\\Pictures\\Saved Pictures\\4.jpg");//The path of your local file
        if(!file.exists()){
            System.out.print("File not exist");
            return null;
        }
        String imageBase64 = encodeImgageToBase64(file);

        StringEntity entity = new StringEntity("--OP8XTaXZ0UZs-Sjlefcj2OWskqXWwVQO\r\nContent-Type: application/json\r\n\r\n{\r\n  \"fileName\":\"hwe001\",\r\n  \"contentExtras\": {\t\"thumbnail\":{\"content\":\"dGVzdDAwMw==\",\"mimeType\":\"image/png\"}},\r\n  \"parents\":[\"applicationData\"]\r\n}\r\n--OP8XTaXZ0UZs-Sjlefcj2OWskqXWwVQO\r\nContent-Type: application/octet-stream\r\n\r\n"
            + imageBase64
            + "\r\n--OP8XTaXZ0UZs-Sjlefcj2OWskqXWwVQO--");
        entity.setContentType("multipart/related;boundary=OP8XTaXZ0UZs-Sjlefcj2OWskqXWwVQO");
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

    /*
    *
    * To encode image for Base64
    * */
    public static String encodeImgageToBase64(File imageFile) {
        ByteArrayOutputStream outputStream = null;
        try {
            BufferedImage bufferedImage = ImageIO.read(imageFile);
            outputStream = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "jpg", outputStream);
        } catch (MalformedURLException e1) {
            e1.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(outputStream.toByteArray());
    }

}
