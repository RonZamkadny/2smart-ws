package com.ronx.smart;

import com.google.common.base.Preconditions;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/user")
public class UserController {

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public User getUser(@PathVariable String id) {
        if (id.equals(null) || id.equals("")) {// refactor with google lib
            id = "0";
        }
        return new User(id);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public String sendId(@RequestBody String id) throws NoSuchAlgorithmException {
        Preconditions.checkNotNull(id);
        MessageDigest md = MessageDigest.getInstance("SHA");
        md.update(id.getBytes());
        String digest = new String(md.digest());
        return digest;
    }
}
