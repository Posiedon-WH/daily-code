package posiedon.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

/**
 * @author weihai
 *
 * @desc description
 * @date 2020/11/19
 */
@RestController
@Slf4j
public class HomeController {

    @RequestMapping("/")
    public String index(HttpServletRequest request){
        log.info("request info [{}]",request.toString());
        return "Welcome Java!";
    }
}
