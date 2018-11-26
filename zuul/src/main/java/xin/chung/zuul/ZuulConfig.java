package xin.chung.zuul;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.stereotype.Component;

/**
 * @Author Chung
 * @Date 2018/11/25 18:23
 */
@Component
public class ZuulConfig {
    @RefreshScope
    @ConfigurationProperties("zuul")
    public ZuulProperties zuulProperties() {
        return new ZuulProperties();
    }
}
