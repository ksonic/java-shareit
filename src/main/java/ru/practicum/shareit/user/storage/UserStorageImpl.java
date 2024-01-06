package ru.practicum.shareit.user.storage;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import ru.practicum.shareit.user.exception.DataNotFoundException;
import ru.practicum.shareit.user.model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserStorageImpl implements UserStorage {
    private final JdbcTemplate jdbcTemplate;

    private static User buildUser(ResultSet rs, int rowNum) throws SQLException {
        return User.builder()
                .id(rs.getLong("id"))
                .email(rs.getString("email"))
                .name(rs.getString("name"))
                .build();
    }

    @Override
    public List<User> getAll() {
        String sqlQuery = "SELECT * FROM users GROUP BY id";
        return jdbcTemplate.query(sqlQuery, UserStorageImpl::buildUser);
    }

    @Override
    public User create(User user) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(
                    "insert into users (name, email) values (?,?)", new String[]{"id"});
            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            return ps;
        }, keyHolder);
        Long id = keyHolder.getKey().longValue();
        return getById(id);
    }

    @Override
    public User getById(Long id) {
        String sqlQuery = "SELECT * FROM users u WHERE u.id = ?";
        return jdbcTemplate.query(sqlQuery, UserStorageImpl::buildUser, id).stream().findAny()
                .orElseThrow(() -> new DataNotFoundException(String.format("user with id %s now found", id)));
    }

    @Override
    public User update(User user) {
        String sqlQueryStatement = "UPDATE USERS SET ";
        List<Object> sqlArgs = new ArrayList<>();
        List<String> sqlConditions = new ArrayList<>();
        if (user.getName() != null) {
            sqlConditions.add("name = ?");
            sqlArgs.add(user.getName());
        }
        if (user.getEmail() != null) {
            sqlConditions.add("email = ?");
            sqlArgs.add(user.getEmail());
        }
        String sqlParams = String.join(" , ", sqlConditions);
        String resultQuery = sqlQueryStatement + sqlParams + " WHERE id = ?";
        sqlArgs.add(user.getId());
        jdbcTemplate.update(resultQuery, sqlArgs.toArray());
        return getById(user.getId());
    }

    @Override
    public boolean emailExists(String userEmail) {
        SqlRowSet userRows = jdbcTemplate.queryForRowSet("select * from users where email = ?", userEmail);
        if (userRows.next()) {
            log.info("User found: {} {}", userRows.getString("id"), userRows.getString("email"));
        } else {
            log.info("User with email {} not found.", userEmail);
            return false;
        }
        return true;
    }

    @Override
    public Boolean containsUser(Long id) {
        SqlRowSet userRows = jdbcTemplate.queryForRowSet("select * from users where id = ?", id);
        if (userRows.next()) {
            log.info("User found: {} {}", userRows.getString("id"), userRows.getString("id"));
        } else {
            log.info("User with email {} not found.", id);
            return false;
        }
        return true;
    }

    @Override
    public void delete(Long id) {
        String sqlQuery = "DELETE FROM users WHERE id = ?";
        jdbcTemplate.update(sqlQuery, id);
    }
}
