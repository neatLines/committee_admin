package com.panis.repository;

import com.panis.model.PublicityTableEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by fuyipeng on 29/11/2016.
 */
@Repository
public interface PublicityRepository extends JpaRepository<PublicityTableEntity,Integer> {
    public List<PublicityTableEntity> findOrderByPId(Integer pId);
}
