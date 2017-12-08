package cn.raven.financial.yellowcow.dao.repository;

import cn.raven.financial.yellowcow.dao.entity.Good;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

/**
 * @Desription:
 * @Author: Raven wu
 * @Date: Created in 17:34 2017/12/5
 */
public interface GoodRepository extends CrudRepository<Good, Long>, JpaSpecificationExecutor<Good> {

    Page<Good> findBySaleTimeBetween(Date start, Date end, Pageable pageable);

}
