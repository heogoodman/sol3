package com.example.practiceMyself.service;

import com.example.practiceMyself.dto.CustomerReqDto;
import com.example.practiceMyself.dto.CustomerResDto;
import com.example.practiceMyself.entity.Customer;
import com.example.practiceMyself.exception.BusinessException;
import com.example.practiceMyself.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

// @Transactional 을 사용하는 이유는 문제가 생겼을 때 롤백을 하기 위해
@Transactional
@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    //
    private final ModelMapper modelMapper;

    public CustomerResDto saveCustomer(CustomerReqDto customerReqDto) {
        Customer customer = modelMapper.map(customerReqDto, Customer.class);
        Customer savedCustomer = customerRepository.save(customer);
        return modelMapper.map(savedCustomer, CustomerResDto.class);
    }

    // @Transactional을 사용하면 순서대로 처리하기 때문에 속도가 느려지는데
    // 단순히 읽어오는 작업만 할 때 Transactional(readOnly = true)을 사용하면 조금이나마 속도가 빨리짐
    @Transactional(readOnly = true)
    public CustomerResDto getCustomerId(Long id) {
        Customer customer = customerRepository.findById(id)
                // 아이디로 사용자를 찾지 못할 시 BusinessException으로 예외를 발생시킴
                .orElseThrow(()->new BusinessException("can't find user", HttpStatus.NOT_FOUND));
        // customer를 CustomerResDto로 바꿔주기 위해 map을 사용
        CustomerResDto customerResDto = modelMapper.map(customer, CustomerResDto.class);
        return customerResDto;
    }

    @Transactional(readOnly = true)
    public List<CustomerResDto> getCustomer() {
        List<Customer> customersList = customerRepository.findAll();
        // Stream의 Customer로 바꾸고
        List<CustomerResDto> customerResDtoList = customersList.stream()
                // Stream의 CustomerResDto로 변경
                // 배열로 잡는 것을 맵을 통해서 customer를 하나씩 가져온 것을 CustomerResDto로 변경 후
                .map(customer -> modelMapper.map(customer, CustomerResDto.class))
                // List로 다시 변경
                .collect(Collectors.toList());
        return customerResDtoList;
    }

    public CustomerResDto updateCustomer(String email, CustomerReqDto customerReqDto) {
        Customer exitCustomer = customerRepository.findByEmail(email)
                .orElseThrow(()-> new BusinessException(email + "user not found", HttpStatus.NOT_FOUND));
        exitCustomer.setName(customerReqDto.getName());
        return modelMapper.map(exitCustomer, CustomerResDto.class);
    }

    public void deleteCustomer(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new BusinessException(id + "user not found", HttpStatus.NOT_FOUND));
        customerRepository.delete(customer);
    }
}
