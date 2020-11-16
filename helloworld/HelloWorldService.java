package com.spring.restful.webservices.demos.restfulwebservices.helloworld;

import org.springframework.web.bind.annotation.*;

@RestController //handles REST request
public class HelloWorldService {

    @RequestMapping(method = RequestMethod.GET, path = "/hello-world")
    public String helloWorld()
    {
        return "Hello World!";
    }

    @GetMapping(path = "/hello-world-new")
    public String helloWorldNew()
    {
        return "Hello-World!";
    }

    @GetMapping(path = "/hello-world-bean")
    public HelloWorldBean helloWorldBean()
    {
        return new HelloWorldBean("Hello-World-Bean");
    }

    @GetMapping(path = "/hello-world/path-variable/{name}")
    public HelloWorldBean helloWorldPathVariable(@PathVariable String name)
    {
        return new HelloWorldBean(String.format("Hello World %s", name)); // the value passed as parameter from the browser is replaced as method's output
    }


}
