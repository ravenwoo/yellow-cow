package cn.raven.financial.yellowcow.common.enums;

/**
 * @Desription:
 * @Author: Raven wu
 * @Date: Created in 17:41 2017/12/5
 */
public enum UserRoleEnum {

    ROOT(0, "root"),
    ADMIN(1, "admin"),
    NORMAL(9, "normal");

    private Integer value;

    private String desc;

    UserRoleEnum(Integer value, String desc) {
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
