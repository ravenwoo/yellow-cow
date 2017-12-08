package cn.raven.financial.yellowcow.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * @Desription:
 * @Author: Raven wu
 * @Date: Created in 17:10 2017/12/5
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "nick_name")
    private String nickName;

    /**
     * UserRoleEnum
     * 0：root；1：admin；9：normal
     */
    @Column(name = "role")
    private Integer role;

    @Column(name = "create_time")
    private Date createTime;

}
