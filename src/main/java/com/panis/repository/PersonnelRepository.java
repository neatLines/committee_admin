package com.panis.repository;

import com.panis.model.PersonnelTableEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by fuyipeng on 29/11/2016.
 */
@Repository
public interface PersonnelRepository extends JpaRepository<PersonnelTableEntity,Integer> {

}
