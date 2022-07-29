package com.example.tribal.entity;

import lombok.Data;

import java.util.List;

/**
 * @author lhui    919238538@qq.com:
 * @description
 * @date 2022/7/29 上午11:16
 */

@Data
public class UserEntity {
    private Long id;// 用户id
    private String username;// 用户名
    private String password;// 密码
    private List<Role> userRoles;// 用户权限集合
    private List<Menu> roleMenus;// 角色菜单集合

}
