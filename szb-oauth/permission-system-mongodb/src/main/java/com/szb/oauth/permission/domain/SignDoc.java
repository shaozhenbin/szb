package com.szb.oauth.permission.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

/**
 * @ClassName SignDoc
 * @Description SignKey+UserId 作联合唯一索引
 * 这样创建者可分配的权限为自己的角色权限的子集，可以把一个私有key 暴露成公共的授权给他人，
 * 那么他人就对此签名下面所有的资源，都有相应你配置的权限，RouterMap 代表他拥有哪些权限，
 * 这样你就可以把自己的签名，分给不同的人，不同的人，拥有对此签名不同的权限。完全实现了，
 * 自己的数据自己做主管理。用于多种协调工作的场景。比如临时有一个修改活动逻辑的任务，
 * 有3个人做，那么他们组负责人可以创建一个公共的 key ，赋予这三个人，用于此次任务的所有权限操作
 * @Author szb
 * @Date 2020/3/27 14:42
 * @Version 1.0
 **/
@Document(collection = "sign")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SignDoc {

    @Id
    private String id;

    // 签名, 某人的私有签名 + 用户唯一标识
    // 可以理解成 这个人拥有这个签名的哪些数据权限
    private String signKey;

    // 签名的创建者
    private String createUserId;

    // 这个成员也拥有这个签名的一部分/全部权限， 相当于创建者把自己的签名共享出，用于多人使用
    private String userId;

    // key=Path value=Method   $bitsAllSet 计算  {"/template":14}
    // 表述 这个人拥有此签名下面的模版数据 增/改/删的权限
    private Map<String, Integer> routerMap;
}

//初始化路由 Method 描述以及是否开启数据权限 ---> 创建角色 --->把才创建好的路由与创建的角色进行绑定
// ---> 为角色添加用户 --->角色创建自己的签名 ---> 每一次请求携带上签名 ---> 创建资源的时候携带上此签名与资源绑定
//
//查看个人自己拥有什么权限
//
//userid 查询 RoleDoc 然后把所有返回的 PathMethods 做聚合
//通过 PathMethods 也可以结合 RouterDoc 查询具体的数据验证权限
//查看别人授予的权限
//
// userid 查询 SignDoc  然后对返回，做聚合即可
