package com.ngu.springcloud.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;

@Mapper
public interface AccountDao {
    /**
     * 扣减账户余额
     */
    int decrease(@Param("userId") Long userId, @Param("money") BigDecimal money);

}
