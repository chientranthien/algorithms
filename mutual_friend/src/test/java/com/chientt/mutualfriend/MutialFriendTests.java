package com.chientt.mutualfriend;

//import org.junit.Test;
import com.chientt.mutualfriend.model.User;
import com.chientt.mutualfriend.service.impl.UserService;
import com.chientt.mutualfriend.service.impl.UserServiceImpl;
import java.util.Arrays;
import java.util.List;
import static org.assertj.core.api.Assertions.*;
import org.junit.Test;

/**
 *
 * @author chientt
 */
public class MutialFriendTests {

    UserService userService = new UserServiceImpl();

    @Test
    public void testCase1() {
        User user1 = new User();
        user1.setFriends(Arrays.asList(1L, 2L));

        User user2 = new User();
        user2.setFriends(Arrays.asList(1L, 2L));

        List<Long> mutualFriends = userService.findMutualFriends(user1, user2);
        List<Long> expectation = Arrays.asList(1L, 2L);

        assertThat(mutualFriends).isEqualTo(expectation);

    }

}
