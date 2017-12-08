package cn.raven.financial.yellowcow.controller;

import cn.raven.financial.yellowcow.common.bean.BaseBean;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Desription:
 * @Author: Raven wu
 * @Date: Created in 16:28 2017/12/6
 */
public class BaseController<T extends BaseBean> {

    private static final Logger logger = LoggerFactory.getLogger(BaseController.class);

    public Boolean validate(T t) {
        logger.info("{} param: {}", t.getClass().getSimpleName(), JSON.toJSONString(t));
        if (t.validate()) {
            logger.error("goodBean validate fail!!!");
            return true;
        }
        return false;
    }

}
