package cn.raven.financial.yellowcow.controller;

import cn.raven.financial.yellowcow.common.bean.request.GoodBean;
import cn.raven.financial.yellowcow.dao.entity.Good;
import cn.raven.financial.yellowcow.service.GoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Desription:
 * @Author: Raven wu
 * @Date: Created in 10:06 2017/12/6
 */
@RestController
@RequestMapping("good")
public class GoodController extends BaseController<GoodBean> {

    @Autowired
    private GoodService goodService;

    @PostMapping("new")
    public Good newGood(@RequestBody GoodBean bean) {
        if (super.validate(bean)) {
            return null;
        }
        return goodService.newGood(bean);
    }

    @PostMapping("query")
    public List<Good> query(@RequestBody GoodBean bean) {
        if (super.validate(bean)) {
            return null;
        }
        return goodService.findGoods(bean);
    }

    @PostMapping("inDate")
    public Page<Good> inDate(@RequestBody GoodBean bean) {
        if (super.validate(bean) || bean.getPage() == null || bean.getSaleTime() == null) {
            return null;
        }
        return goodService.goodsInDate(bean.getPage(), bean.getSaleTime());
    }


}
