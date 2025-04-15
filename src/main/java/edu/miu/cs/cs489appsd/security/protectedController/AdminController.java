package edu.miu.cs.cs489appsd.security.protectedController;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/admin")
public class AdminController {

    @GetMapping
    public String admin() {
        return "Admin: secured endpoint";
    }
}
