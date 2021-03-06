package ru.geekbrains.pocket.backend;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.geekbrains.pocket.backend.domain.User;
import ru.geekbrains.pocket.backend.repository.UserRepository;

import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMongoRepositoryTest {
    @Autowired
    private UserRepository userMongoRepository;


    @Before
    public void setUp() throws Exception {
        User user1 = new User("Alex", "pass", "a@mail.ru");
        User user2 = new User("Bob", "pass", "b@mail.ru");
        //update product, verify has ID value after update
        assertNull(user1.getId());
        assertNull(user2.getId());//null before update
        this.userMongoRepository.save(user1);
        this.userMongoRepository.save(user2);
        assertNotNull(user1.getId());
        assertNotNull(user2.getId());
    }

    @Test
    public void testFetchData() {
        /*Test data retrieval*/
        Optional<User> userA = userMongoRepository.findByUsername("Bob");
        assertNotNull(userA);
        /*Get all products, list should only have two*/
        Iterable<User> users = userMongoRepository.findAll();
        int count = 0;
        for (User p : users) {
            count++;
        }
        assertEquals(count, 2);
    }

    @Test
    public void testDataUpdate() {
        /*Test update*/
        Optional<User> userB = userMongoRepository.findByUsername("Bob");
        userB.get().setEmail("bob@mail.ru");
        userMongoRepository.save(userB.get());
        Optional<User> userC = userMongoRepository.findByUsername("Bob");
        assertNotNull(userC);
        assertEquals("bob@mail.ru", userC.get().getEmail());
    }

    @After
    public void tearDown() throws Exception {
        this.userMongoRepository.deleteAll();
    }

}
