package com.example.ws.microservices.firstmicroservices.serviceImpl.templateTables;

import com.example.ws.microservices.firstmicroservices.dto.templateTables.ShiftDTO;
import com.example.ws.microservices.firstmicroservices.entity.template.Shift;
import com.example.ws.microservices.firstmicroservices.repository.templateTables.ShiftRepository;
import com.example.ws.microservices.firstmicroservices.service.templateTables.ShiftService;
import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ShiftServiceImpl implements ShiftService {

    private final ShiftRepository shiftRepository;

    @Override
    public List<Shift> findAll() {
        return shiftRepository.findAll();
    }

    @Override
    public void preloadToCache(RedisTemplate<String, Object> redisTemplate) {
        List<ShiftDTO> shifts = getAllFromDB();
        for (ShiftDTO shift : shifts) {
            redisTemplate.opsForValue().set("shift:" + shift.getId(), shift);
        }
    }

    @Override
    public List<ShiftDTO> getAllFromDB() {
        return shiftRepository.findAllWithSite().stream().map(eachObject -> ShiftDTO.builder()
                .name(eachObject.getName())
                .id(eachObject.getId().shortValue())
                .siteName(eachObject.getSite().getName())
                .build()).toList();
    }

    @Override
    public boolean supportsType(Class<?> type) {
        return type.equals(ShiftDTO.class);
    }
}
