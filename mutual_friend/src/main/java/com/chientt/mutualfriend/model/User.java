package com.chientt.mutualfriend.model;

import java.util.List;

/**
 *
 * @author chientt
 */
public class User {

    private long id;
    private String name;
    private List<Long> friends;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Long> getFriends() {
        return friends;
    }

    public void setFriends(List<Long> friends) {
        this.friends = friends;
    }

    public synchronized void addFriend(long userId) {
        if (userId <= 0) {
            return;
        }
        for (int i = friends.size() - 1; i >= 0; i--) {
            if (friends.get(i) <= userId) {
                friends.add(i + 1, userId);;
                break;
            }
        }
    }

    public synchronized void unfriend(long userId) {
        if (userId <= 0) {
            return;
        }
        friends.remove(userId);
    }
}
