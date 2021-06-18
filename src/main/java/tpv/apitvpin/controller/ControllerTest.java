package tpv.apitvpin.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControllerTest {

    @GetMapping("/test")
    public String tester() {
        return "Test Ok.";
    }
}
