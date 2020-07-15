package com.nnk.springboot.repositories;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BidListRepository extends JpaRepository<BidList, Integer> {
//    @Query(value = "select b from BidList b where b.BidListId=:id")
    BidList findByBidListId(Integer id);

}
