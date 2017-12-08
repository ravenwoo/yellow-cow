package cn.raven.financial.yellowcow.service;

import cn.raven.financial.yellowcow.common.bean.request.GoodBean;
import cn.raven.financial.yellowcow.dao.entity.Good;
import org.springframework.data.domain.Page;

import java.util.Date;
import java.util.List;

/**
 * @Desription:
 * @Author: Raven wu
 * @Date: Created in 17:55 2017/12/5
 */
public interface GoodService {

    Good newGood(GoodBean bean);

    Page<Good> goodsInDate(Integer page, Date date);

    List<Good> findGoods(GoodBean goodBean);

}
