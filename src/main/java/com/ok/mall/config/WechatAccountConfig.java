package com.ok.mall.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


/**
 * @author Iverson
 * @company HuiYangSoft
 * @date 2018/6/21 23:29
 */
@Data
@Component
@ConfigurationProperties(prefix= "wechat" )
public class WechatAccountConfig {

    private String mpAppId;

    private String mpAppSecret;
}
