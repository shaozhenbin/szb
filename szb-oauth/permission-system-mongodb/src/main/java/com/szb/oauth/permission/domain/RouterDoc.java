package com.szb.oauth.permission.domain;

import javassist.runtime.Desc;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

/**
 * @ClassName RouterDoc
 * @Description 有了 Path 与角色的关联，就自然会有 Path 的详情，
 * 以方便管理，作为组成角色的权限的源，
 * 同时可以设置该接口是否开启数据验证权限
 * @Author szb
 * @Date 2020/3/27 14:27
 * @Version 1.0
 **/
@Document(collection = "router")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RouterDoc {

    //通过上面的简单表述，就可以建立 url + method 等于方法的实现，可以很方便的进行功能权限管理
    //Path 作唯一索引
    // 请求路径  service + module + /template
    @Id
    private String path;
    // 路径实现功能的大类， 例如 模块的 CRUD
    private String desc;
    // key 方法 具体表现  "1"  "2" ..
    private Map<String, MethodDetail> methodMap;
    //服务名称
    private String serviceName;
    //模块名称
    private String moduleName;



}
