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
        List<Long> lowerArray;
        List<Long> upperArray;
        if (friendList1.get(0) < friendList2.get(0)) {
            lowerArray = friendList1;
            upperArray = friendList2;
        } else {
            lowerArray = friendList2;
            upperArray = friendList1;
        }

        if (!isEntersectional(lowerArray, upperArray)) {
            return Collections.EMPTY_LIST;
        }
        long lowerVal = upperArray.get(0);

        int lowerIndex = Math.abs(Collections.binarySearch(lowerArray, lowerVal));
        List<Long> result = new ArrayList<>();

        for (int i = lowerIndex, j = 0; i < lowerArray.size(); i++) {
            long val = lowerArray.get(i);

            while (j < upperArray.size() && upperArray.get(j) <= val) {
                if (upperArray.get(j) == val) {
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
