package cn.raven.financial.yellowcow.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Desription:
 * @Author: Raven wu
 * @Date: Created in 17:13 2017/12/5
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "good")
public class Good {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "sale_time")
    private Date saleTime;

    @Column(name = "buy_url")
    private String buyUrl;

    @Column(name = "buy_price")
    private BigDecimal buyPrice;

    @Column(name = "advise_price")
    private BigDecimal advisePrice;

    @Column(name = "sell_price")
    private BigDecimal sellPrice;

    @Column(name = "low_limit")
    private BigDecimal lowLimit;

    @Column(name = "buyer")
    private Long buyer;

    @Column(name = "leader")
    private Long leader;

    /**
     * GoodStatusEnum
     * 0：待购,1:待售
     */
    @Column(name = "status")
    private Integer status;

    /**
     * 优先级：0 > 10 ;数字越大，优先级越高
     */
    @Column(name = "level")
    private Integer level;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

}
