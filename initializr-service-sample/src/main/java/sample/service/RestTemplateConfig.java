package sample.service;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;

import java.net.InetSocketAddress;
import java.net.Proxy;

@Configuration
public class RestTemplateConfig {

    @Bean
    @ConfigurationProperties(prefix = "http.proxy")
    public ProxyProperties proxyProperties() {
        return new ProxyProperties();
    }

    @Bean
    public RestTemplateCustomizer proxyRestTemplateCustomizer(ProxyProperties proxyProperties) {
        return restTemplate -> {
            if (proxyProperties.isEnabled()) {
                SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
                requestFactory.setProxy(
                    new Proxy(Proxy.Type.HTTP, 
                    new InetSocketAddress(proxyProperties.getHost(), proxyProperties.getPort()))
                );
                restTemplate.setRequestFactory(requestFactory);
            }
        };
    }

    public static class ProxyProperties {
        private boolean enabled;
        private String host;
        private int port;

        // Getters and setters
        public boolean isEnabled() { return enabled; }
        public void setEnabled(boolean enabled) { this.enabled = enabled; }
        public String getHost() { return host; }
        public void setHost(String host) { this.host = host; }
        public int getPort() { return port; }
        public void setPort(int port) { this.port = port; }
    }
}
