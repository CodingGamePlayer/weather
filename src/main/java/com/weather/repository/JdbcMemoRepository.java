package com.weather.repository;

import com.weather.domain.Memo;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class JdbcMemoRepository {
    private final JdbcTemplate jdbcTemplate;


    public Memo save(Memo memo) {
        String sql = "INSERT INTO memo VALUES (?,?)";
        jdbcTemplate.update(sql, memo.getId(), memo.getText());
        return memo;
    }

    public List<Memo> findAll(){
        String sql = "select * from memo";
        return jdbcTemplate.query(sql, memoRowMapper());
    }

    public Memo findById(int id) {
        String sql= "select * from memo where id = ?";

        return jdbcTemplate.query(sql, memoRowMapper(), id)
                .stream().findAny()
                .orElseThrow(() -> new IllegalArgumentException());
    }

    private RowMapper<Memo> memoRowMapper() {
        return ((rs, rowNum) -> new Memo(
                rs.getInt("id"),
                rs.getString("text")
        ));
    }

}
