/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chientt.mutualfriend.service.impl;

import com.chientt.mutualfriend.model.User;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author chientt
 */
public class UserServiceImpl implements UserService {

    @Override
    public List<Long> findMutualFriends(User user1, User user2) {
        List<Long> friendList1 = user1.getFriends();
        List<Long> friendList2 = user2.getFriends();

        if (isNullOrEmpty(friendList1) || isNullOrEmpty(friendList2)) {
            return Collections.EMPTY_LIST;
        }
        List<Long> lowerBoundArray;
        List<Long> upperBoundArray;
        if (friendList1.get(0) < friendList2.get(0)) {
            lowerBoundArray = friendList1;
            upperBoundArray = friendList2;
        } else {
            lowerBoundArray = friendList2;
            upperBoundArray = friendList1;
        }

        if (!isEntersectional(lowerBoundArray, upperBoundArray)) {
            return Collections.EMPTY_LIST;
        }
        long lowerVal = upperBoundArray.get(0);

        int lowerIndex = Collections.binarySearch(lowerBoundArray, lowerVal);
        List<Long> result = new ArrayList<>();

        for (int i = lowerIndex, j = 0; i < lowerBoundArray.size(); i++) {
            long val = lowerBoundArray.get(i);

            while (j < upperBoundArray.size() && upperBoundArray.get(i) <= val) {
                if (upperBoundArray.get(i) == val) {
                    result.add(val);
                }
                j++;
            }
        }

        return result;
    }

    private boolean isNullOrEmpty(List<Long> list) {
        return list == null || list.isEmpty();
    }

    private boolean isEntersectional(List<Long> lower, List<Long> upper) {
        return lower.get(lower.size() - 1) >= upper.get(0);
    }
}
