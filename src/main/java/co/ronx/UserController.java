package co.ronx;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/core")
public class UserController {

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public User getUser(@PathVariable String id) {
        if (id.equals(null) || id.equals(""))   {// refactor with google lib
            id = "0";
        }
        return new User(id);
    }
}
