package com.cg.Services;

import com.cg.Exceptions.*;
import com.cg.Model.Customer;
import com.cg.Repository.CustomerRepository;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service("customerService")
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    private Validator validator;

    @Override
    public Customer registerCustomer(Customer newCustomer) {

        // Check date of birth
        if (newCustomer.getDateOfBirth() != null && newCustomer.getDateOfBirth().isAfter(LocalDate.of(2010, 1, 1))) {
            throw new InvalidDateOfBirthException("Date of birth should not be greater than 2010");
        }

        // Check if mobile number is unique
        Customer cu=customerRepository.findCustomerByMobileNumber(newCustomer.getMobileNumber());
        if (cu!=null) {
            throw new CustomerAlreadyRegisteredException("Customer with the same mobile number is already registered.");
        }
        else {
            // Check mobile number length
            if (newCustomer.getMobileNumber() != null && newCustomer.getMobileNumber().length() != 10) {
                throw new InvalidMobileNumberException("Mobile number should be of 10 digits.");
            }
        }

        // Check if Aadhaar number is unique
        Customer customer=customerRepository.findCustomerByAadhaarNumber(newCustomer.getAadhaarNumber());
        if (customer!=null) {
            throw new CustomerAlreadyRegisteredException("Customer with the same Aadhaar number is already registered.");
        }
        else {
            // Check Aadhaar number length
            if (newCustomer.getAadhaarNumber() != null && String.valueOf(newCustomer.getAadhaarNumber()).length() != 12) {
                throw new InvalidAadhaarNumberException("Aadhaar number should be of 12 digits.");
            }
        }
        Customer c1=new Customer();

        // Save the new customer if both mobile and Aadhaar numbers are unique
        return customerRepository.save(newCustomer);
    }
  @Override
    public Optional<Customer> loginUser(String username, String password) {
       Optional <Customer> customer= Optional.ofNullable((customerRepository.findByUserName(username)));
        if (customer.isPresent()) {

            Customer c = customer.get();
            // Compare passwords directly
            if (c.getPassword().equals(password) ) {
                if(c.getPassword().length()>=8) {
                    // Password matches, continue with authentication
                    c.setLoginTime(LocalDateTime.now());
                    c.setAccountBalance(c.getAccountBalance());
                    customerRepository.save(c);
                    return customer;
                }
                else {
                    throw new InvalidPasswordLengthException("Password length must be greater than 8");

                }
            }
            else {
                throw new UserNotFoundException("unAuthorized user:");
            }
        }
        else{
            throw new UsernameAndPasswordNotMathchesException("User Not Found With userName and password :" +username+ " " +password);
        }
        }

    @Override
    public void logoutUser(String userName) {
        // Set logoutTime in the User entity
        Customer c1 = customerRepository.findByUserName(userName);
        if (c1 != null) {
            c1.setLogoutTime(LocalDateTime.now());
            customerRepository.save(c1);
        }
        else {
            throw new UserNotFoundException("User Not found with Id:" +userName);
        }
    }

    @Override
    public List<Customer> getAllCustomers() {
        List <Customer> customers=customerRepository.findAll();
        return customers;
    }

    @Override
    public Customer getCustomerByUsername(String userName) {
        Customer c=customerRepository.findByUserName(userName);
        if (c!=null){
            return c;
        }
        else {
            throw new CustomerNotFoundException("Customer not found with user name :"+ userName);
        }
    }

    @Override
    public Customer updateCustomer( String userName,Customer updatedCustomer) {
        Customer existingCustomer=customerRepository.findByUserName(userName);
        if(existingCustomer==null){
            throw new CustomerNotFoundException("Customer not found with customerName:"+userName);
        }
        else {
            // Validate or set any additional business logic before updating
            if (updatedCustomer.getUserName()!= null) {
                existingCustomer.setUserName(updatedCustomer.getUserName());
            }
            if (updatedCustomer.getPassword()!= null) {
                existingCustomer.setPassword(updatedCustomer.getPassword());
            }
//
            if (updatedCustomer.getMobileNumber() != null && updatedCustomer.getMobileNumber().length() != 10) {
               if( updatedCustomer.getMobileNumber().length() != 10) {
                   throw new InvalidMobileNumberException("Mobile number should be of 10 digits.");
               }
                existingCustomer.setMobileNumber(updatedCustomer.getMobileNumber());
            }
//
            return customerRepository.save(existingCustomer);
        }
    }

    @Override
    public void deleteCustomer(Long customerId) {
        Optional<Customer> cu=customerRepository.findById(customerId);
        if(cu.isPresent()){
            customerRepository.deleteById(customerId);
            System.out.println("Customer deleted successfully:");
        }
        else {
            throw new CustomerNotFoundException("Customer not found with customerId:"+customerId);
        }

    }

}
