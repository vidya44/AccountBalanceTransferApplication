package com.cg.Services;

import com.cg.Model.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    Customer registerCustomer(Customer newCustomer);
    public Optional<Customer> loginUser(String username, String password);
    public void logoutUser(String userName);
    public List<Customer> getAllCustomers() ;
    Customer getCustomerByUsername(String userName);

    public Customer updateCustomer(String userName, Customer updatedCustomer);
    public void deleteCustomer(Long customerId);
}
