package com.panis.repository;

import com.panis.model.UserTableEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by fuyipeng on 2016/11/13.
 */
@Repository
public interface UserRepository extends JpaRepository<UserTableEntity, Integer> {
    public List<UserTableEntity> findOrderByUserName(String userName);
}