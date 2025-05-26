package com.demo.httpsession;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.demo.httpsession.model.entity.UserEntity;
import com.demo.httpsession.model.entity.UserHistoryEntity;
import com.demo.httpsession.model.repository.IUserHistoryRepository;
import com.demo.httpsession.model.repository.IUserRepository;
@SpringBootTest
//@DataJpaTest
@ActiveProfiles("test") // Uses application-test.properties for H2 setup
public class UserHistoryRepositoryTest {

    @Autowired
    private IUserHistoryRepository iUserHistoryRepository;

    @Autowired
    private IUserRepository iUserRepository;
    
    @Test
    @DisplayName("Save UserHistory")
    public void saveUserHistory() {
        // Arrange
        UserEntity userEntity = new UserEntity();
        userEntity.setName("last name");
        userEntity.setSurname("surname");
        userEntity.setUsername("username");
        userEntity = iUserRepository.save(userEntity);
        // Act
        UserHistoryEntity userHistoryEntity = new UserHistoryEntity();
        userHistoryEntity.setUsersEntity(userEntity);
        userHistoryEntity = iUserHistoryRepository.save(userHistoryEntity);

        // Assert
        assertNotNull(userHistoryEntity);
    }


}
