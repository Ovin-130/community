package file.ovin.community.controller;

import file.ovin.community.mapper.UserMapper;
import file.ovin.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/")
   public String Index(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie: cookies) {
            if(cookie.getName().equals("token")){
                String token  = cookie.getValue();
                User user =userMapper.selectToken(token);
                if(user != null){
                    request.getSession().setAttribute("user",user);;
                    break;
                }
            }
        }
       return "index";
   }
}
