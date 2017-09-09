package dva.web;

import dva.domain.ObjectManager;
import dva.domain.Person;
import example.users.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by kar on 7/9/17.
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/people")
class PersonController {
    @Autowired
    private ObjectManager objectManager;

    @ModelAttribute("people")
    public Page<Person> person(@PageableDefault(size = 5) Pageable pageable) {
        return objectManager.findAll(pageable);
    }

    @RequestMapping(method = RequestMethod.GET)
    public String index() {
        return "people";
    }
}