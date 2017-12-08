package cn.raven.financial.yellowcow.dao.repository;

import cn.raven.financial.yellowcow.dao.entity.User;
import org.springframework.data.repository.CrudRepository;

/**
 * @Desription:
 * @Author: Raven wu
 * @Date: Created in 17:33 2017/12/5
 */
public interface UserRepository extends CrudRepository<User, Long> {

}
