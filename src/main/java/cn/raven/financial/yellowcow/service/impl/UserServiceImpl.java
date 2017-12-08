package cn.raven.financial.yellowcow.service.impl;

import cn.raven.financial.yellowcow.common.bean.request.UserBean;
import cn.raven.financial.yellowcow.dao.entity.User;
import cn.raven.financial.yellowcow.dao.repository.UserRepository;
import cn.raven.financial.yellowcow.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @Desription:
 * @Author: Raven wu
 * @Date: Created in 17:49 2017/12/5
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    @Override
    public User newUser(UserBean userBean) {
        User user = User.builder().build();
        BeanUtils.copyProperties(userBean, user);
        user.setCreateTime(new Date());
        return userRepository.save(user);
    }

}
