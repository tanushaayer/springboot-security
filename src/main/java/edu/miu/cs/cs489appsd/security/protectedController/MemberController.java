package edu.miu.cs.cs489appsd.security.protectedController;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/members")
public class MemberController {

    @GetMapping
    public String getMember(){
        return "Member: secured endpoint";
    }

    @GetMapping("/admin-write")
    @PreAuthorize("hasAuthority('admin:write')")
    public String adminWrite(){
        return "Member: secured endpoint only for admin write";
    }
}
