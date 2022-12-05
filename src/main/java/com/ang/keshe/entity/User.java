package com.ang.keshe.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 *
 * </p>
 *
 * @author ANG
 * @since 2022-06-19
 */
@Getter
@Setter
@TableName("sys_user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;
    /**
     * 昵称
     */

    private String nickname;
    /**
     * 权限等级
     */

    private String priority;

    /**
     * 学生名
     */
    private String name;

    /**
     * 学号
     */
    private String code;

    /**
     * 性别
     */
    private String sex;

    /**
     * 入学年份
     */
    private Integer year;

    /**
     * 所属学院
     */
    private String college;

    /**
     * 专业
     */
    private String major;

    /**
     * 所在校区
     */
    private String campus;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;


}
