package com.example.practiceMyself.controller;

import com.example.practiceMyself.entity.Customer;
import com.example.practiceMyself.exception.BusinessException;
import com.example.practiceMyself.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/customer")
@RestController
public class CustomerBasicRestController {
    @Autowired
    private CustomerRepository customerRepository;

    @PostMapping
    public Customer create(@RequestBody Customer customer) {
        return customerRepository.save(customer);
    }

    @GetMapping
    public List<Customer> getCustomer() {
        return customerRepository.findAll();
    }

    @GetMapping("/{id}")
    public Customer getCustomer(@PathVariable Long id) {
        Optional<Customer> customerOptional = customerRepository.findById(id);
        Customer customer = customerOptional.orElseThrow(() -> new BusinessException("User Not Found", HttpStatus.NOT_FOUND));
        return customer;
    }

    @GetMapping("/email/{email}")
    public Customer getCustomerByEmail(@PathVariable String email) {
        return customerRepository.findByEmail(email).orElseThrow(() -> new BusinessException("user not found", HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("{email}")
    public ResponseEntity<?> deleteCustomer(@PathVariable String email) {
        Customer customer = customerRepository.findByEmail(email)
                .orElseThrow(()->new BusinessException("user not founnd", HttpStatus.NOT_FOUND));
        customerRepository.delete(customer);
        return ResponseEntity.ok(email + "님의 계정이 삭제 되었습니다");
    }
}
