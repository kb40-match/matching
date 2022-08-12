package com.matching.kb40.backend.dao;

import com.matching.kb40.backend.dto.MydataDto;
import com.matching.kb40.backend.dto.UserDto;

import java.sql.SQLException;
import java.util.List;

public interface UserDao {

    public UserDto retrieve(String userId) throws SQLException;

    public List<UserDto> retrieveAll() throws SQLException;

//    public UserDto insert() throws SQLException;

    public void update(UserDto user) throws SQLException;

    public void delete(String userId) throws SQLException;

    public MydataDto retrieveMydata(String userId) throws SQLException;
}
