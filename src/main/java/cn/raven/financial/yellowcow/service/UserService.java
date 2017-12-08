package cn.raven.financial.yellowcow.service;

import cn.raven.financial.yellowcow.common.bean.request.UserBean;
import cn.raven.financial.yellowcow.dao.entity.User;

import java.util.List;

/**
 * @Desription:
 * @Author: Raven wu
 * @Date: Created in 17:38 2017/12/5
 */
public interface UserService {

    User saveUser(UserBean userBean);

    List<User> query();

    void delete(Long id);

}
