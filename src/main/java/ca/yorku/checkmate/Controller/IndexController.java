package ca.yorku.checkmate.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Simple hello world index page!
 */
@RestController
public class IndexController {

    @GetMapping("/")
    public String index() {
        return "Hello World!";
    }
}
