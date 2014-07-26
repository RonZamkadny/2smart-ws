package com.ronx.smart;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/user")
public class UserController {

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public User getUser(@PathVariable String id) {
        if (id.equals(null) || id.equals(""))   {// refactor with google lib
            id = "0";
        }
        return new User(id);
    }
}
