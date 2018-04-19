package com.simple.webfluxfunctional.config;

import com.simple.webfluxfunctional.handler.UserHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.PUT;
import static org.springframework.web.reactive.function.server.RequestPredicates.DELETE;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

/**
 * @author wangbowen
 * @Description 基于Functional 函数式路由实现Restful API
 * 创建路由配置类，定义路由信息，每个路由映射到一个具体处理的方法
 * @Date 2018/4/19 14:24
 */
@Configuration
public class RoutingConfiguration {
    @Bean
    public RouterFunction<ServerResponse> monoRouterFunction(UserHandler userHandler) {
        return route(GET("/api/user/all").and(accept(MediaType.APPLICATION_JSON)), userHandler::getAll)
                .andRoute(GET("/api/user/{id}").and(accept(MediaType.APPLICATION_JSON)), userHandler::getUser)
                .andRoute(POST("/api/user/create").and(accept(MediaType.APPLICATION_JSON)), userHandler::postUser)
                .andRoute(PUT("/api/user/{id}").and(accept(MediaType.APPLICATION_JSON)), userHandler::putUser)
                .andRoute(DELETE("/api/user/{id}").and(accept(MediaType.APPLICATION_JSON)), userHandler::deleteUser);
    }
}
