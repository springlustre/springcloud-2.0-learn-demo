package com.springlustre.tech.consuldemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class HelloController {

    @Autowired
    private LoadBalancerClient loadBalancer;
    @Autowired
    private DiscoveryClient discoveryClient;

    @RequestMapping("/hello")
    public String hello() {
        ServiceInstance serviceInstance = loadBalancer.choose("consul-demo-producer");
        System.out.println("服务：" + serviceInstance);
        System.out.println("服务地址：" + serviceInstance.getUri());
        System.out.println("服务名称：" + serviceInstance.getServiceId());

        String callServiceResult = new RestTemplate().getForObject(serviceInstance.getUri().toString() + "/hello", String.class);
        System.out.println(callServiceResult);
        return callServiceResult;
//        return "helle consul consumer";
    }



    /**
     * 获取所有服务
     */
    @RequestMapping("/services")
    public Object services() {
        // 获取对应服务名称的所有实例信息
        return discoveryClient.getInstances("consul-demo-producer");
    }

    /**
     * 从所有服务中选择一个服务（轮询）
     */
    @RequestMapping("/discover")
    public Object discover() {
        return loadBalancer.choose("consul-demo-producer").getUri().toString();
    }

}
