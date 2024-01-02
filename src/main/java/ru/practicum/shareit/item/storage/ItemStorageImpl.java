package ru.practicum.shareit.item.storage;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.user.exception.DataNotFoundException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@Qualifier("itemStorageImpl")
@RequiredArgsConstructor
public class ItemStorageImpl implements ItemStorage {
    private final JdbcTemplate jdbcTemplate;

    private static Item buildItem(ResultSet rs, int rowNum) throws SQLException {
        return Item.builder()
                .id(rs.getLong("id"))
                .name(rs.getString("name"))
                .description(rs.getString("description"))
                .available(rs.getBoolean("available"))
                .owner(rs.getLong("owner"))
                .build();
    }

    @Override
    public Item create(Item item) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(
                    "insert into items (name, description,owner) values (?,?,?)", new String[]{"id"});
            ps.setString(1, item.getName());
            ps.setString(2, item.getDescription());
            ps.setLong(3, item.getOwner());
            return ps;
        }, keyHolder);
        Long id = keyHolder.getKey().longValue();
        return getById(id);
    }

    @Override
    public Item update(Item item) {
        String sqlQueryStatement = "UPDATE ITEMS SET ";
        List<Object> sqlArgs = new ArrayList<>();
        List<String> sqlConditions = new ArrayList<>();
        if (item.getName() != null) {
            sqlConditions.add("name = ?");
            sqlArgs.add(item.getName());
        }
        if (item.getDescription() != null) {
            sqlConditions.add("description = ?");
            sqlArgs.add(item.getDescription());
        }
        if (item.getAvailable() != null) {
            sqlConditions.add("available = ?");
            sqlArgs.add(item.getAvailable());
        }
        String sqlParams = String.join(" , ", sqlConditions);
        String resultQuery = sqlQueryStatement + sqlParams + " WHERE id = ? and owner=?";
        sqlArgs.add(item.getId());
        sqlArgs.add(item.getOwner());
        jdbcTemplate.update(resultQuery, sqlArgs.toArray());
        return getById(item.getId());
    }

    @Override
    public Item getById(Long id) {
        String sqlQuery = "SELECT * FROM items u WHERE u.id = ?";
        return jdbcTemplate.query(sqlQuery, ItemStorageImpl::buildItem, id).stream().findAny()
                .orElseThrow(() -> new DataNotFoundException(String.format("items with id %s now found", id)));
    }

    @Override
    public List<Item> getAll(Long userId) {
        String sqlQuery = "SELECT * FROM items where owner=?";
        return new ArrayList<>(jdbcTemplate.query(sqlQuery, ItemStorageImpl::buildItem, userId));
    }

    @Override
    public List<Item> getSearchResults(String query) {
        String sqlQuery = "select ID, NAME, DESCRIPTION, AVAILABLE, OWNER from ITEMS " +
                "where available=true " +
                "and (name iLike CONCAT('%',?,'%') " +
                "or description iLike CONCAT('%',?,'%'))";
        return new ArrayList<>(jdbcTemplate.query(sqlQuery, ItemStorageImpl::buildItem, query, query));
    }

    @Override
    public boolean checkOwner(Item item) {
        Long id = item.getOwner();
        String sqlQuery = "SELECT * FROM users where id=?";
        SqlRowSet userRows = jdbcTemplate.queryForRowSet(sqlQuery, id);
        if (userRows.next()) {
            log.info("Owner found: {} {}", userRows.getString("id"), userRows.getString("id"));
        } else {
            log.info("Owner {} not found.", id);
            return false;
        }
        return true;
    }

    @Override
    public boolean checkItems(Item item) {
        Long owner = item.getOwner();
        Long id = item.getId();
        String sqlQuery = "SELECT * FROM items where id=? and owner=?";
        SqlRowSet userRows = jdbcTemplate.queryForRowSet(sqlQuery, id, owner);
        if (userRows.next()) {
            log.info("Item found: {} {}", userRows.getString("id"), userRows.getString("id"));
        } else {
            log.info("Item {} not found.", id);
            return false;
        }
        return true;
    }

}

