import com.matheuscordeiro.user.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceUnitTest {
    UserService userService;

    @Mock
    UserRepository userRepository;

    @Mock
    SettingRepository settingRepository;

    @BeforeEach
    void init() {
        var mailClient = new MailClient();
        userService = new DefaultUserService(userRepository, settingRepository, mailClient);

        Mockito.lenient().when(settingRepository.getUserMinAge()).thenReturn(10);

        when(settingRepository.getUserNameMinLength()).thenReturn(4);

        Mockito.lenient()
                .when(userRepository.isUsernameAlreadyExists(any(String.class)))
                .thenReturn(false);
    }

    @Test
    void givenValidUser_whenSaveUser_thenSucceed(@Mock MailClient mailClient) {
        // Given
        var user = new User("Jerry", 12);
        when(userRepository.insert(any(User.class))).then(new Answer<User>() {
            int sequence = 1;

            @Override
            public User answer(InvocationOnMock invocation) throws Throwable {
                User user = (User) invocation.getArgument(0);
                user.setId(sequence++);
                return user;
            }
        });

        userService = new DefaultUserService(userRepository, settingRepository, mailClient);

        // When
        var insertedUser = userService.register(user);

        // Then
        verify(userRepository).insert(user);
        assertNotNull(user.getId());
        verify(mailClient).sendUserRegistrationMail(insertedUser);
    }
}
