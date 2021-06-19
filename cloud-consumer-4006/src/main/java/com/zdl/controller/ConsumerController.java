package com.zdl.controller;

import com.google.gson.Gson;
import com.zdl.User;
import com.zdl.controller.loadbalance.LoadBalancer;
import com.zdl.controller.loadbalance.RotationLoadBalancer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhudaolin
 * @date 2021/6/1
 */
@RestController
@RequestMapping("/consumer")
public class ConsumerController {

    private static String serviceName = "cloud-provider-4003";

    @Autowired
    private RestTemplate restTemplate;

    //@Autowired
    //private LoadBalancerClient loadBalancerClient; //这里不知道为啥报错，估计版本太新，未引用到包

    @Autowired
    private RotationLoadBalancer loadBalancer; // 自己手工实现的负载均衡

    @Autowired
    DiscoveryClient discoveryClient;

    /**
     * DiscoveryClient + restTemplate
     * @return
     */
    @GetMapping("/getUserJson")
    public String getUserJson(){
        //使用LoadBlanceClient 和 restTemplage结合方式访问  // 如果restTemplage配置有加@LoadBlance，则不能再结合loadBalancerClient使用
        //ServiceInstance serviceInstance = loadBalancerClient.choose("cloud-provider-4003");

        List<ServiceInstance> list = discoveryClient.getInstances(serviceName);
        ServiceInstance serviceInstance = list.get(0); // 这里可以自己实现轮询、、、负载均衡
        ServiceInstance ss = loadBalancer.getSingleAddres(list);// 这个是自己手工实现的，
        //http://localhost:4003/providerUser/getUserJson
        String url = String.format("http://%s:%s/cloud-provider-4003/providerUser/getUserJson",serviceInstance.getHost(),serviceInstance.getPort());

        String result = getForEntry(url); // getForObject(url);
        return result;
    }


    private String getForEntry(String url) {
        ResponseEntity<String> result = restTemplate.getForEntity(url,String.class);
        return result.getBody();
    }

    private String getForObject(String url) {
        return restTemplate.getForObject(url,String.class);
    }

    @GetMapping("/addUser")
    public User addUser(Long id, String name, Integer age){
        //List<ServiceInstance> list = discoveryClient.getInstances(serviceName);
        //ServiceInstance serviceInstance = list.get(0); // 这里可以自己实现轮询、、、负载均衡
        //http://localhost:4003/addUser
        //String url = String.format("http://%s:%s/providerUser/addUser",serviceInstance.getHost(),serviceInstance.getPort());
        String url = String.format("http://%s/cloud-provider-4003/providerUser/addUser",serviceName);
                User paramUser = new User(id,name,age);
        User user = restTemplate.postForObject(url,paramUser, User.class);
        return user;
    }

    @GetMapping("/addUserByJSON")
    public User addUserByJSON(Long id, String name, Integer age){
        List<ServiceInstance> list = discoveryClient.getInstances(serviceName);
        ServiceInstance serviceInstance = list.get(0); // 这里可以自己实现轮询、、、负载均衡
        //http://localhost:4003/addUser
        String url = String.format("http://%s:%s/cloud-provider-4003/providerUser/addUser",serviceInstance.getHost(),serviceInstance.getPort());
        User paramUser = new User(id,name,age);
        String json = new Gson().toJson(paramUser);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(json,headers);
        User user = restTemplate.postForObject(url,entity, User.class);
        return user;
    }

    @GetMapping("/addUserByParm")
    public User addUserByParm(Long id, String name, Integer age){
        List<ServiceInstance> list = discoveryClient.getInstances(serviceName);
        ServiceInstance serviceInstance = list.get(0); // 这里可以自己实现轮询、、、负载均衡
        //http://localhost:4003/addUser
        String url = String.format("http://%s:%s/cloud-provider-4003/providerUser/addUserByParm",serviceInstance.getHost(),serviceInstance.getPort());

        MultiValueMap<String,Object> dataMap = new LinkedMultiValueMap<>();
        dataMap.add("id",id);
        dataMap.add("name",name);
        dataMap.add("age",age);
        User user = restTemplate.postForObject(url,dataMap, User.class);
        return user;
    }

}
