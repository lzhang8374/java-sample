package org.trex.sample.datasource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private void setDbSource(String sql) {
        String lcSql = sql.toLowerCase();
        if (lcSql.startsWith("insert") || lcSql.startsWith("update") || lcSql.startsWith("replace") || lcSql.startsWith("delete")) {
            ContextHolder.setDbType(ContextHolder.DB_TYPE_W);
        } else {
            ContextHolder.setDbType(ContextHolder.DB_TYPE_R);
        }
    }

    public void execute(String sql, Object... args) {
        this.setDbSource(sql);
        this.jdbcTemplate.update(sql, args);
    }

    public List<Map<String, Object>> getAll(String sql, Object... args) {
        this.setDbSource(sql);
        List<Map<String, Object>> list = this.jdbcTemplate.queryForList(sql, args);
        return list;
    }


}
