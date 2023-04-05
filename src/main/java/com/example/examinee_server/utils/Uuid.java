package com.example.examinee_server.utils;

import java.util.UUID;

// uuid 生成器
public class Uuid {
    public static String generateUuid() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
}
