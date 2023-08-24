package com.esmabeydili.repository.implementation;

import com.esmabeydili.domain.Role;
import com.esmabeydili.exception.ApiException;
import com.esmabeydili.repository.RoleRepository;
import com.esmabeydili.rowmapper.RoleRowMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

import static com.esmabeydili.enumeration.RoleType.ROLE_USER;
import static com.esmabeydili.enumeration.VerificationType.ACCOUNT;
import static com.esmabeydili.query.RoleQuery.*;
import static com.esmabeydili.query.UserQuery.INSERT_ACCOUNT_VERIFICATION_URL_QUERY;
import static com.esmabeydili.query.UserQuery.INSERT_USER_QUERY;
import static java.util.Objects.requireNonNull;

@Repository
@RequiredArgsConstructor
@Slf4j//loglama kodunu basitleştirmek için kullanıyoruz
public class RoleRepositoryImpl implements RoleRepository<Role> {

    private final NamedParameterJdbcTemplate jdbc;

    @Override
    public Role create(Role data) {
        return null;
    }

    @Override
    public Collection<Role> list(int page, int pageSize) {
        return null;
    }

    @Override
    public Role get(Long id) {
        return null;
    }

    @Override
    public Role update(Role data) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void addRoleToUser(Long userId, String roleName) {

        log.info("Adding role {} to user id : {} ",roleName,userId);

        try{
            Role role= jdbc.queryForObject(SELECT_ROLE_BY_NAME_QUERY,Map.of("name",roleName),new RoleRowMapper());
            jdbc.update(INSERT_ROLE_TO_USER_QUERY,Map.of("userId",userId,"roleId", Objects.requireNonNull(role).getId()));
        }
        catch (EmptyResultDataAccessException exception){
            throw new ApiException("No role found by name "+ROLE_USER.name());
        }catch (Exception exception){
            throw new ApiException("An error occurred.Please try again!");
        }

    }

    @Override
    public Role getRoleByUserId(Long userId) {
        return null;
    }

    @Override
    public Role getRoleByUserEmail(String email) {
        return null;
    }

    @Override
    public void updateUserRole(Long userId, String email) {

    }
}
