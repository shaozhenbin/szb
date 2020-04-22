package com.szb.oauth.permission.domain;

import lombok.*;
import org.mongodb.morphia.annotations.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Map;

/**
 * @ClassName UserDoc
 * @Description 可以看到一个人自己可以创建多个私有 签名
 * GroupName+UserId 作联合唯一索引
 * @Author szb
 * @Date 2020/3/27 14:06
 * @Version 1.0
 **/
@Document(collection = "user")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDoc {

    //工号
    @Id
    private String userId;

    //姓名
    private String name;

    private String groupName;

    //签名 key是签名 24字符id mongodb _id (可换成任意 唯一 字符串)，value是签名的描述。
    private Map<String, String> signKeyMap;

    //用户有哪些角色
    private List<String> roleNames;

}
