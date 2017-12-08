package cn.raven.financial.yellowcow.common.bean.request;

import cn.raven.financial.yellowcow.common.bean.BaseBean;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

/**
 * @Desription:
 * @Author: Raven wu
 * @Date: Created in 17:40 2017/12/5
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserBean extends BaseBean {

    private Long id;

    private String userName;

    private String nickName;

    /**
     * 0：root；1：admin；9：normal
     */
    private Integer role;

    @Override
    public Boolean validate() {
        if (StringUtils.isBlank(userName)) {
            return true;
        }
        return false;
    }
}
