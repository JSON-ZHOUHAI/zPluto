package cn.zPluto.service.base.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>Description:</p>
 *
 * @author zhouh
 * @version 1.0
 * @Date 2018/4/12.
 */
@RestController
public class HelloController {

    @Autowired
    private DiscoveryClient client;

    @RequestMapping(value="/hello",method = RequestMethod.GET)
    public String index(){
        //ServiceInstance instance=client.getl
        return "Hello World";
    }

}