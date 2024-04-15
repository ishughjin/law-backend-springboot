package com.incypio.law.UserService.util;

import com.incypio.law.UserService.entity.UserEntity;

public class UserThreadLocal {
    private static final ThreadLocal<UserEntity> LOCAL = new ThreadLocal<>();
    private UserThreadLocal() {
    }

    static void put(UserEntity user) {
        LOCAL.set(user);
    }
    public static UserEntity get() {
        return LOCAL.get();
    }

    public static void remove() {
        LOCAL.remove();
    }
}
