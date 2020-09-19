package com.ngu.springcloud.myhandler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.ngu.springcloud.entities.CommonResult;

public class CustomerBlockHandler {

    public static CommonResult handleException(BlockException e) {
        return new CommonResult(2020,"自定义的限流处理信息.....handleException");
    }
}
