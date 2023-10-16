package com.example.practiceMyself.controller;

import com.example.practiceMyself.dto.CustomerReqDto;
import com.example.practiceMyself.dto.CustomerResDto;
import com.example.practiceMyself.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/customer")
public class CustomerRestController {
    private final CustomerService customerService;

    @PostMapping
    public CustomerResDto saveCustomer(@RequestBody CustomerReqDto customerReqDto) {
        return customerService.saveCustomer(customerReqDto);
    }

    @GetMapping("/{id}")
    public CustomerResDto getCustomerById(@PathVariable Long id) {
        return customerService.getCustomerId(id);
    }

    @GetMapping
    public List<CustomerResDto> getCustomer() {
        return customerService.getCustomer();
    }

    @PatchMapping("/{email}")
    public CustomerResDto updateCustomer(@PathVariable String email, @RequestBody CustomerReqDto customerReqDto) {
        return customerService.updateCustomer(email, customerReqDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.ok(id + " successfully deleted user ");
    }
}
