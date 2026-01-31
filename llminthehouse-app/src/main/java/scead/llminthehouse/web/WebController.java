package scead.llminthehouse.web;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController
{
    private static Logger log = LoggerFactory.getLogger(WebController.class);

    @GetMapping(value = {"/", "/**/{[path:[^\\.]*}" })
    public String redirect()
    {
        log.info("request app page");

        return "forward:/index.html";
    }
}
