package lk.backend.controller;

import lk.backend.entity.AppUser;
import lk.backend.service.UserService;
import lk.backend.util.CommonConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * User
 *
 * @author SE3070_WE_69
 * @version 1.0
 */

@CrossOrigin
@RestController
@RequestMapping(value = CommonConstants.PROCUMENTARY + CommonConstants.USER)
public class UserController {

    // Initialize service
    @Autowired
    private UserService userService;

    /**
     * Login user
     */

    @PostMapping(value = "/login")
    public ResponseEntity login(@RequestBody AppUser appUser) {
        return ResponseEntity.ok(userService.login(appUser));
    }

}
