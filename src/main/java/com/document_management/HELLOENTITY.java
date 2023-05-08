package com.document_management;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HELLOENTITY {
    @RequestMapping("/")
    public String hello()
    {
        return "Hello javaTpoint";
    }
}
