package com.example.tribal.entity;

import lombok.Data;

/**
 * @author lhui    919238538@qq.com:
 * @description
 * @date 2022/7/29 上午11:15
 */

@Data
public class Menu extends BaseEntity {

    /**
     *
     */
    private static final long serialVersionUID = 4245833784962015367L;


    private String menuName;//菜单名称
    private String menuUrl;//Controller路径
    private String menuCode;//菜单编码
    private Long parentId;//父菜单ID
    private Integer menuType;//菜单类型：0-菜单1-按钮
    private Integer orderNum;//显示序号
}
