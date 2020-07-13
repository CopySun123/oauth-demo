package com.copysun.microoauth2auth.service;

import org.apache.commons.lang.ArrayUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author copysun
 * 资源与角色匹配关系管理业务类
 */
@Service
public class ResourceServiceImpl {

    private Map<String, List<String>> resourceRolesMap;

    @Resource
    private RedisTemplate<String,Object> redisTemplate;

    @PostConstruct
    public void initData(){
        resourceRolesMap = new TreeMap<>();
        resourceRolesMap.put("api/hello", Arrays.asList("ADMIN"));
        resourceRolesMap.put("/api/user/currentUser", Arrays.asList("ADMIN","TEST"));
        redisTemplate.opsForHash().putAll("RESOURCE_ROLES_MAP",resourceRolesMap);
    }
}
