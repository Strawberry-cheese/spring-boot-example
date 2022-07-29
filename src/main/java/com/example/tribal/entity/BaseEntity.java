package com.example.tribal.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author lhui    919238538@qq.com:
 * @description
 * @date 2022/7/29 上午11:14
 */

@Data
public class BaseEntity implements Serializable {
    protected Long id;//ID

    protected Integer state;//状态

    protected String creator;//创建人

    protected String createTime;//创建时间

    protected String updator;//修改人

    protected String updateTime;//修改时间

    protected Boolean deleted = false;//删除标志
}
