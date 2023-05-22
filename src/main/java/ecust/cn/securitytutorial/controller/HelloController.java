package ecust.cn.securitytutorial.controller;

import ecust.cn.securitytutorial.entity.Users;
import ecust.cn.securitytutorial.mapper.UsersMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class HelloController {

    @Autowired
    UsersMapper usersMapper;
//    @Autowired
//    private PasswordEncoder passwordEncoder;

    @GetMapping("/hello")
    public String hello() {
        return "ok";
    }


    @GetMapping("/hello1")
//    @Secured({"ROLE_张san"})
//    @PreAuthorize("hasAnyAuthority('admins')")
    // 方法执行之后校验
//    @PostAuthorize("hasAnyRole('role')")
    // 对返回数据做过滤
//    @PostFilter("filterObject.username=='admin'")
    // 对传入参数做过滤
//    @PreFilter(value = "filterObject.id%2==0")
    public String hello1() {
        Users users = usersMapper.selectById(1);
//        String encode = passwordEncoder.encode("1234");
        return users.toString() + "\t" + "encode";
    }

    @GetMapping("/index")
    public String index(){
        return "forward:/index.html";
    }

}
