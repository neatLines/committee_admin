package com.panis.repository;

import com.panis.model.HouseTableEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by fuyipeng on 29/11/2016.
 */
@Repository
public interface HouseRepository extends JpaRepository<HouseTableEntity,Integer> {
    public List<HouseTableEntity> findOrderByHouseId(int houseId);

    @Modifying
    @Transactional
    @Query("update HouseTableEntity hst set hst.uId=:uId, hst.apHouse=:apHouse, " +
            "hst.pannant=:pannant, hst.state=:state where hst.houseId=:houseId")
    public void updateHouseInfo(@Param("uId") Integer uId,@Param("apHouse") String apHouse,
                                @Param("pannant") String pannant, @Param("state") String state,
                                @Param("houseId") int houseId);
}
