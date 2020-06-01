package com.para4digm.yumcdpl.client;

import com._4paradigm.prophet.keystone.KeystoneClient;
import com._4paradigm.prophet.rest.client.HttpOperator;
import com._4paradigm.prophet.rest.client.IPAddrSyncHttpOperator;
import com._4paradigm.prophet.rest.context.ProphetScope;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


/**
 * @author yaoyuan
 */
@Slf4j
@Component
public class KeyStoneClientFactory {


    @Value("${sso.sage.clientVersion}")
    String version;

    @Value("${sso.sage.permanentKey}")
    String key;

    @Value("${sso.sage.url}")
    String prophetUrl;

    KeystoneClient create() {
        ProphetScope.setPermanentKey(key);
        HttpOperator httpsOperator = new IPAddrSyncHttpOperator(10, 10);
        return new KeystoneClient(prophetUrl,
                version, httpsOperator);
    }
}
