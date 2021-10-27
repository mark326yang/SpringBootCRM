package com.hqyj.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName ErrorController
 * @Date 2021/10/27 16:20
 * @Author XianJiu
 * @Description TODO
 */
@Controller
public class ErrorController {
    @RequestMapping(value = "/Error/{errorCode}")
    public String error(@PathVariable("errorCode") String branch) {
        //logger.error("ErrorController@error {} !",branch);
        return "error";
    }
}
