package com.szb.oauth.permission.domain;

import javassist.runtime.Desc;
import lombok.Builder;

/**
 * @ClassName MethodDetail
 * @Description TODO
 * @Author szb
 * @Date 2020/3/27 14:30
 * @Version 1.0
 **/
@Builder
public class MethodDetail {
    // 该方法是否开启 数据验证
    private boolean dataVerify;
    // 模版的删除 则 MethodMap key = 8
    private String desc;
}
