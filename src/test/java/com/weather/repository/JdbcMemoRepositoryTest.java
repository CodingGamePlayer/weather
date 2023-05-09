package com.weather.repository;

import com.weather.domain.Memo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Transactional
class JdbcMemoRepositoryTest {

    @Autowired
    JdbcMemoRepository jdbcMemoRepository;

    @Autowired
    JpaMemoRepository jpaMemoRepository;

    @Test
    void insertMemoTest() {
        //given
        Memo memo = new Memo(1, "this is memo");
        //when
        jdbcMemoRepository.save(memo);
        Memo findMemo = jdbcMemoRepository.findById(1);
        //then
        assertEquals(memo.getId(), findMemo.getId());
        assertEquals(memo.getText(), findMemo.getText());
    }

    @Test
    void findAllMemoTest() {
        //given
        List<Memo> memoList = jdbcMemoRepository.findAll();
        //when
        assertNotNull(memoList);
        //then
    }

    @Test
    void insert () {
        //given
        Memo memo = new Memo();
        memo.setText("this is memo");

        //when
        jpaMemoRepository.save(memo);

        //then
        List<Memo> memos = jpaMemoRepository.findAll();
        assertEquals(memos.get(0).getId(), memo.getId());
        assertEquals(memos.get(0).getText(), memo.getText());
    }

    @Test
    void findById () {
        //given
        Memo memo = new Memo();
        memo.setText("this is memo");

        //when
        Memo saved = jpaMemoRepository.save(memo);
        Memo foundMemo = jpaMemoRepository.findById(saved.getId()).orElseThrow();

        //then
        assertEquals(memo.getText(), foundMemo.getText());
    }
}