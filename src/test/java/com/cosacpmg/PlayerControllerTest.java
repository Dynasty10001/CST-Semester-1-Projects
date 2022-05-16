package com.cosacpmg;

import com.j256.ormlite.dao.*;
import com.j256.ormlite.jdbc.*;
import com.j256.ormlite.support.*;
import org.junit.*;
import org.junit.runner.*;

import java.sql.SQLException;

import static org.junit.Assert.*;

public class PlayerControllerTest {

    private static PlayerController PC;

    @Before
    public void setUpMock() {
        try {
            PC = new PlayerController( new JdbcPooledConnectionSource("jdbc:h2:mem:myDb") );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
