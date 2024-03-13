package com.cg.Repository;

import com.cg.Model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {

    @Query("SELECT c FROM Customer c WHERE c.mobileNumber = :mobileNumber")
    Customer findCustomerByMobileNumber(String mobileNumber);

    @Query("SELECT c FROM Customer c WHERE c.aadhaarNumber = :aadhaarNumber")
    Customer findCustomerByAadhaarNumber(Long aadhaarNumber);

//    @Query("SELECT c FROM Customer c WHERE c.customerId = :customerId")
//    Customer findCustomerByCustomerId(Long customerId);

    @Query("SELECT c FROM Customer c WHERE c.userName = :userName")
    Customer findByUserName(@Param("userName") String username);
}
