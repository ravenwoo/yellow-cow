package cn.raven.financial.yellowcow.common.enums;

/**
 * @Desription:
 * @Author: Raven wu
 * @Date: Created in 17:20 2017/12/5
 */
public enum GoodStatusEnum {

    BUYING(0, "待购"),

    SALING(1, "待售");

    private Integer value;

    private String desc;

    GoodStatusEnum(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public Integer getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }
}
