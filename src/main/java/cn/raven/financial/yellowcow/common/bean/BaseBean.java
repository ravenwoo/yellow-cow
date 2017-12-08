package cn.raven.financial.yellowcow.common.bean;

import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;

/**
 * @Desription:
 * @Author: Raven wu
 * @Date: Created in 17:39 2017/12/5
 */
public abstract class BaseBean implements Serializable {

    /**
     * 当前页
     */
    protected Integer page;

    public abstract Boolean validate();

    @Override
    public String toString() {
        String json = JSONObject.toJSONString(this);
        return json;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }
}
