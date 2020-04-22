package com.szb.oauth.permission.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @ClassName RoleDoc
 * @Description 一个角色拥有多种职能，我们把每一种职能看作一个接口实现了不同的方法，那
 * 么角色权限，就是由一个或多个接口实现的组合，接口实现又体现在 url + method 上面，然而问题是，
 * 有些接口涉及到操作数据，对数据有写操作，有的接口，只是单纯的某一次事件，不涉及到任何数据查询和变更。
 * 那么在这里就会划分此接口是否开启对数据做认证。通过不同的接口实现组合，这样就拥有了角色权限的维护
 * @Author szb
 * @Date 2020/3/27 14:10
 * @Version 1.0
 **/
@Document(collection = "role")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoleDoc {

    //表结构 PathsDataVerify key="1_/template" value=true 因为 1 表示 GET
    //所以这种表示描述就为 模版的查询方法，需要开启数据认证权限
    //roleName+Typ 作联合唯一索引

    //角色名称
    @Id
    private String roleName;

    //角色描述
    private String desc;

    //是否为默认角色，就是用户登陆进来自带的角色权限
    private boolean isDefault;

    //角色下面有哪些用户，  这里是一个优化点，可以使用外键表关联。 视系统大小可以调整
//    private List<String> userIds;

    //该接口是否开启数据验证权限，用于 sql 快速查询数据
    private Map<String, Boolean> pathsDataVerify;

    //该角色有哪些功能即 API 的组合， 详细数据，是一份冗余数据，方便配置时使用 通过方法把详细数据转化为快速查询数据
    private List<PathMethod> pathMethods;

    //角色类型，用于做特权处理， 例如 超级管理员角色，无需验证权限 则 type = 0
    private int type;

    //公司
    private String company;

    //部门
    private String department;
}
