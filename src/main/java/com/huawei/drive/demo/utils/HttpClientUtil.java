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

package com.huawei.drive.demo.utils;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

/**
 * 功能描述
 *
 * @author drive
 * @since 2019-12-03
 */
public class HttpClientUtil {
    /**
     * get httpclient
     * @return
     */
    public static CloseableHttpClient getClient() {
        PoolingHttpClientConnectionManager poolingHttpClientConnectionManager = connectionManager("TLSv1.2", new String[]{"TLSv1.2 TLSv1.1"},
            new String[]{"TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA256 TLS_DHE_RSA_WITH_AES_128_CBC_SHA256 TLS_DHE_RSA_WITH_AES_128_CBC_SHA TLS_DHE_DSS_WITH_AES_128_CBC_SHA"});
        poolingHttpClientConnectionManager.setMaxTotal(400);
        poolingHttpClientConnectionManager.setDefaultMaxPerRoute(400);
        RequestConfig requestConfig =
            RequestConfig.custom().setConnectionRequestTimeout(100).setRedirectsEnabled(false).build();

        return HttpClients.custom()
            .useSystemProperties()
            .setConnectionManager(poolingHttpClientConnectionManager)
            .setDefaultRequestConfig(requestConfig)
            .build();
    }

    /**
     *
     * @param protocol
     * @param supportedProtocols
     * @param supportedCipherSuites
     * @return
     */
    public static PoolingHttpClientConnectionManager connectionManager(String protocol,
        String[] supportedProtocols, String[] supportedCipherSuites) {
        PoolingHttpClientConnectionManager poolingHttpClientConnectionManager = null;
        try {
            SSLContext sslContext = SSLContext.getInstance(protocol);
            TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            tmf.init((KeyStore) null);
            sslContext.init(null, tmf.getTrustManagers(), null);
            SSLConnectionSocketFactory sslConnectionSocketFactory = new SSLConnectionSocketFactory(sslContext, supportedProtocols,
                supportedCipherSuites, SSLConnectionSocketFactory.getDefaultHostnameVerifier());

            Registry<ConnectionSocketFactory> connectionSocketFactory = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", new PlainConnectionSocketFactory())
                .register("https", sslConnectionSocketFactory)
                .build();
            poolingHttpClientConnectionManager = new PoolingHttpClientConnectionManager(connectionSocketFactory);
        } catch (KeyStoreException | KeyManagementException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return poolingHttpClientConnectionManager;
    }
}
