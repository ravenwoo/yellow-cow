package cn.raven.financial.yellowcow.controller;

import cn.raven.financial.yellowcow.common.bean.request.UserBean;
import cn.raven.financial.yellowcow.dao.entity.User;
import cn.raven.financial.yellowcow.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * @Desription:
 * @Author: Raven wu
 * @Date: Created in 10:10 2017/12/6
 */
@RestController
@RequestMapping("user")
public class UserController extends BaseController<UserBean> {

    @Autowired
    private UserService userService;

    @PostMapping("save")
    public User saveUser(@RequestBody UserBean bean) {
        if (super.validate(bean)) {
            return null;
        }
        return userService.saveUser(bean);
    }

    @PostMapping("delete")
    public String delete(@RequestBody Long id) {
        userService.delete(id);
        return "999";
    }

    @PostMapping("query")
    public List<User> query() {

        return userService.query();
    }

}
