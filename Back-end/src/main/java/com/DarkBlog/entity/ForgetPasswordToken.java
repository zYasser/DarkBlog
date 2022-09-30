package com.DarkBlog.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

import java.util.concurrent.TimeUnit;

@Data
@AllArgsConstructor
@Builder
@RedisHash(value = "forget_token",timeToLive = 60)
public class ForgetPasswordToken {
    @Id
    public String token;
    public String username;
}
