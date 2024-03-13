package com.cg.Repository;

import com.cg.Model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Long> {
//    @Query("SELECT t FROM Transaction t WHERE t.user.userID = :userID")
//    List<Transaction> findTransactionsByUserId(@Param("userID") Long userID);

    @Query("SELECT t FROM Transaction t WHERE t.customer.userName = :userName")
    List<Transaction> findTransactionsByUserName(String userName);
}
