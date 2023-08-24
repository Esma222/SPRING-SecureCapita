package com.esmabeydili.repository.implementation;

import static com.esmabeydili.enumeration.RoleType.ROLE_USER;
import static com.esmabeydili.enumeration.VerificationType.ACCOUNT;
import static com.esmabeydili.query.UserQuery.*;

import com.esmabeydili.domain.Role;
import com.esmabeydili.domain.User;
import com.esmabeydili.exception.ApiException;
import com.esmabeydili.repository.RoleRepository;
import com.esmabeydili.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

import static java.util.Objects.requireNonNull;

@Repository
@RequiredArgsConstructor
@Slf4j
public class UserRepositoryImpl implements UserRepository<User> {


    // veritabanında belirli bir e-posta adresine sahip
    // kullanıcıların sayısını sorgulamak için kullanılıyor
    private final NamedParameterJdbcTemplate jdbc;
    private final RoleRepository<Role> roleRepository;
    private final BCryptPasswordEncoder encoder;//şifreyi şifreliyoruz



    @Override
    public User create(User user) {
        //Check the email is unique
        if (getEmailCount(user.getEmail().trim().toLowerCase())>0) throw new ApiException("Email already in use. Please use a different email and try again!");
        //Save new user
        try{

            KeyHolder holder =new GeneratedKeyHolder();
            SqlParameterSource parameters= getSqlParameterSource(user);
            jdbc.update(INSERT_USER_QUERY,parameters,holder);
            user.setId(requireNonNull(holder.getKey().longValue()));
            //add role to the user
            roleRepository.addRoleToUser(user.getId(),ROLE_USER.name());
            //send verification url
            String verificationUrl = getVerificationUrl(UUID.randomUUID().toString(), ACCOUNT.getType());
            //save url in verification table
            jdbc.update(INSERT_ACCOUNT_VERIFICATION_URL_QUERY, Map.of("userId",user.getId(),"url",verificationUrl));
            //send email to user verfication url
            //emailService.sendVerificationUrl(user.getFirstName(),user.getEmail(),verificationUrl,ACCOUNT.getType());
            user.setEnabled(false);
            user.setIsNotLocked(true);
            //return the newly created user
            return user;
            //ıf any errors, thow exception with proper message

        }
       catch (Exception exception){
            log.error(exception.getMessage());
            throw new ApiException("An error occurred.Please try again!");
        }

    }


    @Override
    public Collection<User> list(int page, int pageSize) {
        return null;
    }

    @Override
    public User get(Long id) {
        return null;
    }

    @Override
    public User update(User data) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
    private int getEmailCount(String email) {
        return jdbc.queryForObject(COUNT_USER_EMAIL_QUERY, Map.of("email",email),Integer.class);

    }

    private SqlParameterSource getSqlParameterSource(User user) {
        return new MapSqlParameterSource()
                .addValue("firstName",user.getFirstName())
                .addValue("lastName",user.getLastName())
                .addValue("email",user.getEmail())
                .addValue("password",encoder.encode(user.getPassword()));
    }

    private String getVerificationUrl(String key, String type){

        return ServletUriComponentsBuilder.fromCurrentContextPath().path("/user/verify/"+type+"/"+key).toUriString();

    }
}

