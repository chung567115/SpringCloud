package xin.chung.feign_consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author Chung
 * @Date 2018/11/26 21:22
 */
@Service
public class ZipkinTestService {
    @Autowired
    private ProviderClient providerClient;

    public String test() {
        return providerClient.hello();
    }
}
