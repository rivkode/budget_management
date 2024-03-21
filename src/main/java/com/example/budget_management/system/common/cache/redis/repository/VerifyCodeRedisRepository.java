package com.example.budget_management.system.common.cache.redis.repository;

import com.example.budget_management.system.common.cache.redis.dao.VerifyCode;
import org.springframework.data.repository.CrudRepository;

public interface VerifyCodeRedisRepository extends CrudRepository<VerifyCode, String> {
}
