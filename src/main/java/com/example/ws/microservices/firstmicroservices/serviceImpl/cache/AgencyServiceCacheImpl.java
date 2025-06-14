package com.example.ws.microservices.firstmicroservices.serviceImpl.cache;

import com.example.ws.microservices.firstmicroservices.dto.templateTables.AgencyDTO;
import com.example.ws.microservices.firstmicroservices.entity.vision.simpleTables.Agency;
import com.example.ws.microservices.firstmicroservices.repository.cache.AgencyRepositoryCache;
import com.example.ws.microservices.firstmicroservices.service.cache.AgencyServiceCache;
import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AgencyServiceCacheImpl implements AgencyServiceCache {

    private final AgencyRepositoryCache agencyRepositoryCache;

    @Override
    public List<Agency> findAll() {
        return agencyRepositoryCache.findAll();
    }

    @Override
    public void preloadToCache(RedisTemplate<String, Object> redisTemplate) {
        List<AgencyDTO> agencies = getAllFromDB();
        redisTemplate.opsForValue().set("agencies", agencies);
//        for (AgencyDTO agency : agencies) {
//            redisTemplate.opsForValue().set("agency:" + agency.getId(), agency);
//        }
    }

    @Override
    public List<AgencyDTO> getAllFromDB() {
        return agencyRepositoryCache.findAllWithSite().stream().map(eachObject -> AgencyDTO.builder()
                .name(eachObject.getName())
                .id(eachObject.getId())
                .siteName(eachObject.getSite().getName())
                .build()).toList();
    }

    @Override
    public boolean supportsType(Class<?> type) {
        return type.equals(AgencyDTO.class);
    }

    @Override
    public Optional<Agency> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<Agency> findByAgencyName(String agencyName) {
        return Optional.empty();
    }

    @Override
    public Optional<Agency> findByAgencyCode(String agencyCode) {
        return Optional.empty();
    }

    @Override
    public Optional<Agency> findByAgencyNameAndAgencyCode(String agencyName, String agencyCode) {
        return Optional.empty();
    }

    @Override
    public Optional<Agency> save(Agency agency) {
        return Optional.empty();
    }

    @Override
    public Optional<Agency> update(Agency agency) {
        return Optional.empty();
    }

    @Override
    public Optional<Agency> delete(Long id) {
        return Optional.empty();
    }
}
