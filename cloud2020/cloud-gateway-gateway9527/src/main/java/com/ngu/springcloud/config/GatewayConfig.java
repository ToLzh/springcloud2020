package com.ngu.springcloud.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 路由配置方式二：编码方式配置
 */
@Configuration
public class GatewayConfig {

    /**
     * 配置自定义的路由转发规则
     * 多路由配置方式一： 直接加多一个route
     * @param routeLocatorBuilder
     * @return
     */
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder routeLocatorBuilder ){
        RouteLocatorBuilder.Builder routes = routeLocatorBuilder.routes();
        //http://news.baidu.com/guonei
        routes
            .route("path_route_ngu1",
                r->r.path("/guonei")
                    .uri("http://news.baidu.com/guonei")
            )
            .route("path_route_ngu2",
                    r->r.path("/guoji")
                            .uri("http://news.baidu.com/guoji"))
            .build();
        return routes.build();
    }

    /**
     * 多路由配置方式二：加多一个相应的配置bean
     */
//    @Bean
//    public RouteLocator customRouteLocator2(RouteLocatorBuilder routeLocatorBuilder ){
//        RouteLocatorBuilder.Builder routes = routeLocatorBuilder.routes();
//        //http://news.baidu.com/guonei
//        routes
//            .route("path_route_ngu2",
//                    r->r.path("/guoji")
//                            .uri("http://news.baidu.com/guoji"))
//            .build();
//        return routes.build();
//    }
}
