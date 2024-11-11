package com.example.ws.microservices.firstmicroservices.repository;

import com.example.ws.microservices.firstmicroservices.entity.role.Role;
import com.example.ws.microservices.firstmicroservices.entity.role.UserRole;
import com.example.ws.microservices.firstmicroservices.entity.role.UserRoleId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

    @Query("SELECT r FROM Role r JOIN UserRole ur ON ur.role.id = r.id WHERE ur.user.id = :userId")
    List<UserRole> findRolesByUserId(@Param("userId") Long userId);

    @Query("""
        SELECT ur
        FROM UserRole ur
        WHERE ur.validTo <= :expirationDate
    """)
    List<UserRole> findExpiredRoles(@Param("expirationDate") LocalDateTime expirationDate);

    @Query("""
        SELECT ur.user.id
        FROM UserRole ur
        WHERE ur.role.name = :roleName
    """)
    List<Long> findUsersByRoleName(@Param("roleName") String roleName);

    void deleteById(UserRoleId id);

    @Modifying
    @Query("DELETE FROM UserRole ur WHERE ur.id.userId = :userId AND ur.id.roleId = :roleId")
    void deleteByUserIdAndRoleId(@Param("userId") Long userId, @Param("roleId") Integer roleId);
}
