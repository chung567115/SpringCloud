package xin.chung.feign_provider;

import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @Author Chung
 * @Date 2018/11/20 11:51
 */
@Component
public class IPConfig implements ApplicationListener<WebServerInitializedEvent> {

    private int serverPort;

    @Override
    public void onApplicationEvent(WebServerInitializedEvent event) {
        this.serverPort = event.getWebServer().getPort();
    }

    public int getPort() {
        return this.serverPort;
    }
}