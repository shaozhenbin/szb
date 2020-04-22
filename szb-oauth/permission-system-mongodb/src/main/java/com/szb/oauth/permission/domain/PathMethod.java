package com.szb.oauth.permission.domain;

import lombok.Builder;

/**
 * @ClassName PathMethod
 * @Description TODO
 * @Author szb
 * @Date 2020/3/27 14:14
 * @Version 1.0
 **/
@Builder
public class PathMethod {

    // restful API 风格设计 一个 path 有多个 method，
// method 使用 int 代替 GET=1 POST=2 PUT=4 DELETE=8 HEAD=16... 可以用到 mongodb 位运算
// 如果一个 path 的 get  post 方法都赋予了这个角色， 则 MethodInt=3
    private String path;

    private int methodInt;

}
