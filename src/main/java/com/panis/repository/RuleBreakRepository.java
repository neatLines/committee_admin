package com.panis.repository;

import com.panis.model.RuleBreakTableEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by fuyipeng on 28/11/2016.
 */
@Repository
public interface RuleBreakRepository extends JpaRepository<RuleBreakTableEntity,Integer> {
    public List<RuleBreakTableEntity> findOrderByBreakUId(int breakUId);

    @Modifying      // 说明该方法是修改操作
    @Transactional  // 说明该方法是事务性操作
    // 定义查询
    // @Param注解用于提取参数
    @Query("update RuleBreakTableEntity rb set rb.adminUId=:adminUId, " +
            "rb.breakUId=:breakUId, rb.decribe=:decribe, rb.flag=:flag" +
            " where rb.breakLogId=:breakLogId")
    public void updateRuleBreakTable(@Param("adminUId") Integer adminUId, @Param("breakUId") Integer breakUId,
                           @Param("decribe") String decribe, @Param("flag") Byte flag, @Param("breakLogId") Integer breakLogId);
}