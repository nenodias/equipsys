package br.com.glabs.equipsys.frontend.endpoint;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class HomeController {

    public ModelAndView index(){
        return new ModelAndView("index");
    }

}
