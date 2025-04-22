package com.sumerge.ahmed.exception;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class DummyController {

    @GetMapping("/runtime-error")
    public String throwRuntime() {
        throw new RuntimeException("Bad request test");
    }

    @GetMapping("/general-error")
    public String throwGeneral() throws Exception {
        throw new Exception("Unexpected exception");
    }
}
