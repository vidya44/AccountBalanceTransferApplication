package com.cg.Controller;

import com.cg.Exceptions.CustomerAlreadyRegisteredException;
import com.cg.Exceptions.CustomerNotFoundException;
import com.cg.Exceptions.InvalidMobileNumberException;
import com.cg.Model.Customer;
import com.cg.Services.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController("customerController")
@RequestMapping("/customer")
@CrossOrigin(origins = {"http://localhost:4200"})
public class CustomerController {
    @Autowired
    CustomerServiceImpl customerService;


    @PostMapping("/register")
    public ResponseEntity<Customer> registerCustomer(@RequestBody Customer newCustomer) {
        try {
            Customer registeredCustomer = customerService.registerCustomer(newCustomer);
            if (newCustomer.getDateOfBirth() != null && newCustomer.getDateOfBirth().isAfter(LocalDate.of(2010, 1, 1))) {
                System.out.println("Date of birth should not be greater than 2010");
                return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
            }

            return new ResponseEntity<>(registeredCustomer, HttpStatus.CREATED);
        } catch (CustomerAlreadyRegisteredException e) {
            System.out.println("Customer with provided credential is already registered.");
            return new ResponseEntity<>( HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public ResponseEntity <Customer> loginUser(@RequestBody Customer existingUser) {

        String userName= existingUser.getUserName();
        String password=existingUser.getPassword();
        try {
            Optional<Customer> loggedInUser = customerService.loginUser(userName, password);
            if (loggedInUser.isPresent()) {
                Customer customer=loggedInUser.get();
                return new ResponseEntity<>(customer, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
           // e.printStackTrace();
            System.out.println("User not found with given userName and password: " +userName+ " "+password);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/logOut/{userName}")
    public ResponseEntity<String> logoutUser(@PathVariable String userName){
        try {
            customerService.logoutUser(userName);
            return new ResponseEntity<>("Logged out successfully",HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/updateCustomer/{userName}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable String userName,
                                                   @RequestBody Customer updatedCustomer) {
        try {
            Customer updated = customerService.updateCustomer(userName, updatedCustomer);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } catch (CustomerNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (InvalidMobileNumberException e) {
            System.out.println("Mobile number should be of 10 digits.");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
           // System.out.println("password should be of 8 digits and first letter should be capital only.");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getCustomerByUsername/{userName}")
    public ResponseEntity<Customer> getCustomerByUsername(@PathVariable String userName) {
        try {
            Optional<Customer> customer = Optional.ofNullable(customerService.getCustomerByUsername(userName));

            if (customer.isPresent()) {
                return new ResponseEntity<>(customer.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/getAllCustomers")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        try {
            List<Customer> customers = customerService.getAllCustomers();
            return new ResponseEntity<>(customers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/deleteCustomer/{customerId}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long customerId) {
        try {
            customerService.deleteCustomer(customerId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (CustomerNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    }


