package com.example.ws.microservices.firstmicroservices.serviceImpl.templateTables;

import com.example.ws.microservices.firstmicroservices.dto.templateTables.ShiftDTO;
import com.example.ws.microservices.firstmicroservices.dto.templateTables.TeamDTO;
import com.example.ws.microservices.firstmicroservices.entity.template.Shift;
import com.example.ws.microservices.firstmicroservices.entity.template.Team;
import com.example.ws.microservices.firstmicroservices.repository.templateTables.ShiftRepository;
import com.example.ws.microservices.firstmicroservices.repository.templateTables.TeamRepository;
import com.example.ws.microservices.firstmicroservices.service.templateTables.ShiftService;
import com.example.ws.microservices.firstmicroservices.service.templateTables.TeamService;
import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TeamServiceImpl implements TeamService {

    private final TeamRepository teamRepository;

    @Override
    public List<Team> findAll() {
        return teamRepository.findAll();
    }

    @Override
    public void preloadToCache(RedisTemplate<String, Object> redisTemplate) {
        List<TeamDTO> teams = findAllWithSite();
        for (TeamDTO team : teams) {
            redisTemplate.opsForValue().set("team:" + team.getId(), team);
        }
    }

    @Override
    public List<TeamDTO> findAllWithSite() {
        return teamRepository.findAllWithSite().stream().map(eachObject -> TeamDTO.builder()
                .name(eachObject.getName())
                .id(eachObject.getId())
                .siteName(eachObject.getSite().getName())
                .build()).toList();
    }

    @Override
    public boolean supportsType(Class<?> type) {
        return type.equals(TeamDTO.class);
    }
}
