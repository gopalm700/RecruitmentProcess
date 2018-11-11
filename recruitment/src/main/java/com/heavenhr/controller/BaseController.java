
package com.heavenhr.controller;



import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import springfox.documentation.annotations.ApiIgnore;


@Controller
public class BaseController {

  @RequestMapping("/")
  @ApiIgnore
  public String home() {
    return "redirect:swagger-ui.html";
  }


}
