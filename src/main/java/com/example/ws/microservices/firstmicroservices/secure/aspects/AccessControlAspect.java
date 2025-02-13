package com.example.ws.microservices.firstmicroservices.secure.aspects;

import com.example.ws.microservices.firstmicroservices.dto.RoleDTO;
import com.example.ws.microservices.firstmicroservices.dto.SupervisorAllInformationDTO;
import com.example.ws.microservices.firstmicroservices.secure.CustomUserDetails;
import com.example.ws.microservices.firstmicroservices.service.redice.RedisCacheService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Aspect
@Component
@Slf4j
@AllArgsConstructor
public class AccessControlAspect {

    private final RedisCacheService redisCacheService;

    /**
     * Enforces access control before method execution based on the @AccessControl annotation.
     * Validates user roles, weight, department, and site access.
     *
     * @param joinPoint The join point representing the intercepted method.
     * @param accessControl The annotation containing access control rules.
     */
    @Before("@annotation(accessControl)")
    public void enforceAccess(JoinPoint joinPoint, AccessControl accessControl) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication.getPrincipal() instanceof CustomUserDetails userDetails)) {
            throw new AccessDeniedException("Invalid authentication.");
        }

        String expertisEmployee =  redisCacheService.getExpertisByUserId(userDetails.getUsername());

        SupervisorAllInformationDTO userInfo = redisCacheService
                .getFromCache("userDetails:" + expertisEmployee, SupervisorAllInformationDTO.class)
                .orElseThrow(() -> new AccessDeniedException("User data not found."));

        // Check if the user has full access (e.g., Admin role)
        if (accessControl.fullAccess()) {
            log.info("Full access granted for user: {}", userDetails.getUsername());
            return;
        }

        // Check if the user has sufficient role weight
        int maxWeight = userInfo.getRoles().stream()
                .mapToInt(RoleDTO::getWeight)
                .max()
                .orElse(0);
        if (maxWeight < accessControl.minWeight()) {
            throw new AccessDeniedException("Insufficient role weight. Required: " + accessControl.minWeight());
        }

        // Check allowed roles
        if (accessControl.allowedRoles().length > 0) {
            Set<String> userRoles = userInfo.getRoles().stream()
                    .map(RoleDTO::getName)
                    .collect(Collectors.toSet());

            Set<String> requiredRoles = new HashSet<>(Arrays.asList(accessControl.allowedRoles()));

            if (Collections.disjoint(userRoles, requiredRoles)) {
                throw new AccessDeniedException("Access denied: required roles not met.");
            }
        }

        // Check allowed departments
        if (accessControl.allowedDepartments().length > 0) {
            Set<String> allowedDepartments = new HashSet<>(Arrays.asList(accessControl.allowedDepartments()));
            if (!allowedDepartments.contains(userInfo.getDepartmentName())) {
                throw new AccessDeniedException("Access denied: department not allowed.");
            }
        }

        // Check allowed sites
        if (accessControl.allowedSites().length > 0) {
            Set<String> allowedSites = new HashSet<>(Arrays.asList(accessControl.allowedSites()));
            if (!allowedSites.contains(userInfo.getSiteName())) {
                throw new AccessDeniedException("Access denied: site not allowed.");
            }
        }

        log.info("Access granted for user: {}", userDetails.getUsername());
    }
}