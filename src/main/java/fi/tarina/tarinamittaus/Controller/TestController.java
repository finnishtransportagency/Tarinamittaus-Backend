package fi.tarina.tarinamittaus.Controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/")
public class TestController {

    @GetMapping
    public String test() {
        return "This endpoint works";
    }
}
