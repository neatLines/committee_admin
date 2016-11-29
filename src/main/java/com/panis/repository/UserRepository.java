package com.panis.repository;

import com.panis.model.UserTableEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by fuyipeng on 2016/11/13.
 */
@Repository
public interface UserRepository extends JpaRepository<UserTableEntity, Integer> {
    public List<UserTableEntity> findOrderByUserName(String userName);
    public List<UserTableEntity> findOrderByUId(Integer uId);
    @Modifying
    @Transactional
    @Query("update UserTableEntity us set us.uName=:uName,us.uAge=:uAge,us.uSex=:uSex,us.phoneNumber=:phoneNumber,us.userName=:userName,us.password=:password,us.power=:power where us.uId = :uId")
    public void updateUserInfo (@Param("uName") String uName,
                                @Param("uAge")Integer uAge,@Param("uSex") String uSex,
                                @Param("phoneNumber") String phoneNumber, @Param("userName") String userName,
                                @Param("password") String password,@Param("power") byte power,@Param("uId") Integer uId);
}