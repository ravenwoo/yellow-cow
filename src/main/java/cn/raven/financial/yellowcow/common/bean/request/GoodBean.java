package cn.raven.financial.yellowcow.common.bean.request;

import cn.raven.financial.yellowcow.common.bean.BaseBean;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Desription:
 * @Author: Raven wu
 * @Date: Created in 17:43 2017/12/5
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GoodBean extends BaseBean {

    private Long id;

    private String name;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date saleTime;

    private String buyUrl;

    /**
     * 买入价格
     */
    private BigDecimal buyPrice;

    /**
     * 建议出手价格
     */
    private BigDecimal advisePrice;

    /**
     * 实际出售价格
     */
    private BigDecimal sellPrice;

    /**
     * 最低可接受出售价格
     */
    private BigDecimal lowLimit;

    /**
     * 买入人
     */
    private Long buyer;

    /**
     * 商品负责人
     */
    private Long leader;

    /**
     * GoodStatusEnum
     * 0：待购,1:待售
     */
    private Integer status;

    /**
     * 优先级：0 > 10 ;数字越大，优先级越高
     */
    private Integer level;

    @Override
    public Boolean validate() {
        if (StringUtils.isBlank(name) && buyer == null && leader == null && status == null && level == null && saleTime == null) {
            return true;
        }
        return false;
    }
}
