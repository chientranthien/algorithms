package com.chientt.mutualfriend;

//import org.junit.Test;
import com.chientt.mutualfriend.model.User;
import com.chientt.mutualfriend.service.impl.UserService;
import com.chientt.mutualfriend.service.impl.UserServiceImpl;
import java.util.Arrays;
import java.util.Collections;
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

    @Test
    public void testCase2() {
        User user1 = new User();
        user1.setFriends(Arrays.asList(1L, 2L, 3L, 4L));

        User user2 = new User();
        user2.setFriends(Arrays.asList(2L, 3L));

        List<Long> mutualFriends = userService.findMutualFriends(user1, user2);
        List<Long> expectation = Arrays.asList(2L, 3L);

        assertThat(mutualFriends).isEqualTo(expectation);
    }

    @Test
    public void testCase3() {
        User user1 = new User();
        user1.setFriends(Arrays.asList(3L, 4L, 5L, 6L));

        User user2 = new User();
        user2.setFriends(Arrays.asList(1L, 2L));

        List<Long> mutualFriends = userService.findMutualFriends(user1, user2);

        assertThat(mutualFriends).isEqualTo(Collections.EMPTY_LIST);
    }

    @Test
    public void testCase4() {
        User user1 = new User();
        user1.setFriends(Arrays.asList(3L, 4L, 5L, 6L));

        User user2 = new User();
        user2.setFriends(Arrays.asList(7L, 8L));

        List<Long> mutualFriends = userService.findMutualFriends(user1, user2);

        assertThat(mutualFriends).isEqualTo(Collections.EMPTY_LIST);
    }

    @Test
    public void testCase5() {
        User user1 = new User();
        user1.setFriends(Arrays.asList(3L, 4L, 5L, 6L));

        User user2 = new User();
        user2.setFriends(Arrays.asList(6L, 7L, 8L));

        List<Long> mutualFriends = userService.findMutualFriends(user1, user2);
        List<Long> expectation = Arrays.asList(6L);

        assertThat(mutualFriends).isEqualTo(expectation);
    }
}
