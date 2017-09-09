package dva.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by kar on 8/9/17.
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/claims")
public class ClaimController {
    @RequestMapping(method = RequestMethod.GET)
    public String index() {
        return "claims";
    }
}
