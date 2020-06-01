package com._4paradigm.prophet.rest.client;

import com._4paradigm.prophet.rest.context.ProphetContext;
import com._4paradigm.prophet.rest.context.ProphetScope;
import com._4paradigm.prophet.rest.exception.ConnectionException;
import com._4paradigm.prophet.rest.exception.RestBaseException;
import com._4paradigm.prophet.rest.utils.DataManagerResponseInterceptor;
import com._4paradigm.prophet.rest.utils.Validator;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.concurrent.ThreadSafe;
import javax.net.ssl.SSLContext;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map.Entry;

@ThreadSafe
public class IPAddrSyncHttpOperator extends HttpOperator {
    private static final Logger log = LoggerFactory.getLogger(IPAddrSyncHttpOperator.class);
    private final CloseableHttpClient http;

    public IPAddrSyncHttpOperator(int maxConn, int maxConnPerRoute) {
        try {
            Validator.validateIntPositive(maxConn, "maxConn");
            Validator.validateIntPositive(maxConnPerRoute, "maxConnPerRoute");

            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
                    SSLContexts.custom().loadTrustMaterial(null, new TrustSelfSignedStrategy()).build(),
                    NoopHostnameVerifier.INSTANCE
            );

            this.http = HttpClients.custom().setSSLSocketFactory(sslsf).setMaxConnTotal(maxConn).setMaxConnPerRoute(maxConnPerRoute).build();
        } catch (Exception var5) {
            log.error("Initialize sync HttpOperator without target keystore error", var5);
            throw new RuntimeException(var5);
        }
    }

    public IPAddrSyncHttpOperator(int maxConn, int maxConnPerRoute, String keystorePath, String keystorePassword, String[] protocols) {
        try {
            Validator.validateIntPositive(maxConn, "maxConn");
            Validator.validateIntPositive(maxConnPerRoute, "maxConnPerRoute");
            SSLContext sslcontext = SSLContexts.custom().loadTrustMaterial(new File(keystorePath), keystorePassword.toCharArray(), new TrustSelfSignedStrategy()).build();
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, protocols, (String[]) null, SSLConnectionSocketFactory.getDefaultHostnameVerifier());
            this.http = HttpClients.custom().setSSLSocketFactory(sslsf).setSSLSocketFactory(sslsf).setMaxConnTotal(maxConn).setMaxConnPerRoute(maxConnPerRoute).build();
        } catch (Exception var8) {
            log.error("Initialize sync HttpOperator with target keystore error", var8);
            throw new RuntimeException(var8);
        }
    }

    @Override
    public HttpResponse executeHttp(HttpUriRequest request) throws RestBaseException {
        log.debug("executing {}...", request);
        ProphetContext pc = ProphetScope.getProphetContext();
        if (StringUtils.isNotBlank(pc.getToken())) {
            request.addHeader("User-Token", pc.getToken());
        }

        if (StringUtils.isNotBlank(pc.getPermanentKey())) {
            request.addHeader("Access-Key", pc.getPermanentKey());
        }

        if (StringUtils.isNotBlank(pc.getRequestId())) {
            request.addHeader("X-Prophet-Tracing-v1", pc.getRequestId());
        }

        if (StringUtils.isNotBlank(pc.getSpanId())) {
            request.addHeader("X-Prophet-Tracing-Span-v1", pc.getSpanId());
        }

        if (pc.getWorkspaceId() != null) {
            request.addHeader("X-Prophet-Workspace-Id", Integer.toString(pc.getWorkspaceId()));
        }

        if (pc.getMap() != null) {
            Iterator var3 = pc.getMap().entrySet().iterator();

            while (var3.hasNext()) {
                Entry<String, String> entry = (Entry) var3.next();
                request.addHeader((String) entry.getKey(), (String) entry.getValue());
            }
        }

        CloseableHttpResponse response;
        try {
            response = this.http.execute(request);
        } catch (ClientProtocolException var5) {
            log.warn("request failed", var5);
            throw new RuntimeException(var5);
        } catch (IOException var6) {
            log.warn("request failed", var6);
            throw new ConnectionException(var6.getMessage());
        }

        return DataManagerResponseInterceptor.intercept(request, response);
    }

    public void finalize() {
        if (this.http != null) {
            try {
                this.http.close();
            } catch (IOException var2) {
                log.error("failed to close http client", var2);
            }
        }

    }
}
