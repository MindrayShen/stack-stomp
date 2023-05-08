package io.cjf.teststomp.config;

import io.cjf.teststomp.interceptor.UserInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry
                                               registry) {
        registry.addEndpoint("/cjfstompsrv2")
                .setAllowedOrigins("http://localhost:3000");
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/mytopic","/myqueue");
        config.setApplicationDestinationPrefixes("/myapp");
        config.setUserDestinationPrefix("/user/");
    }

    /**
     * 配置客户端入站通道拦截器
     */
    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.setInterceptors(createUserInterceptor());
    }

    /**
     *
     * @Title: createUserInterceptor
     * @Description: 将客户端渠道拦截器加入spring ioc容器
     * @return
     */
    @Bean
    public UserInterceptor createUserInterceptor() {
        return new UserInterceptor();
    }
}
