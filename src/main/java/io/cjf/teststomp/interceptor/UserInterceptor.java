package io.cjf.teststomp.interceptor;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptorAdapter;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.stereotype.Component;

import java.security.Principal;
import java.util.Map;

/**
 * @program: teststomp
 * @description: websocket拦截器
 * @author: Mr.Shen
 * @create: 2023-04-26 20:18
 */
@Component
public class UserInterceptor extends ChannelInterceptorAdapter {
    /**
     * 获取包含在stomp中的用户信息
     */
    @SuppressWarnings("rawtypes")
    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
        if (StompCommand.CONNECT.equals(accessor.getCommand())) {
            Object raw = message.getHeaders().get(SimpMessageHeaderAccessor.NATIVE_HEADERS);
            if (raw instanceof Map) {
                Object name = ((Map) raw).get("name");
                // 简单处理设置对应权限。完整的应该根据用户的权限得出是否有发送/订阅到某个目的路径的权限
                accessor.setUser(new Principal() {
                    @Override
                    public String getName() {
                        return name.toString();
                    }
                });
            }
        }
        return message;
    }

}
