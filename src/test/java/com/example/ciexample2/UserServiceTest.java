package com.example.ciexample2;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.util.Assert;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepo mockedRepo;

    @InjectMocks
    private UserService service;

    @Test
    public void findall_test_positive() {
        var users  = IntStream.rangeClosed(1,5).mapToObj(i -> new User(i, "name" + i)).collect(Collectors.toList());
        Mockito.when(mockedRepo.findAll()).thenReturn(users);
        var result = this.service.getAll();
        System.out.println(result.toString());
        assert result == users;
    }
}
