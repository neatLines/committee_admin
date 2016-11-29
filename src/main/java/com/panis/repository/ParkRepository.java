package com.panis.repository;

import com.panis.model.ParkTableEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by fuyipeng on 29/11/2016.
 */
@Repository
public interface ParkRepository extends JpaRepository<ParkTableEntity, Integer>{
    public List<ParkTableEntity> findOrderByUId(Integer uId);
    public List<ParkTableEntity> findOrderByParkId(Integer parkId);
}
