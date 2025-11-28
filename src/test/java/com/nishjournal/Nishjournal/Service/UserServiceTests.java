package com.nishjournal.Nishjournal.Service;

import com.nishjournal.Nishjournal.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class UserServiceTests {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void testFindByUserName(){
        assertEquals(4,4);
        assertNotNull(userRepository.findByUserName("ram"));
    }
}
