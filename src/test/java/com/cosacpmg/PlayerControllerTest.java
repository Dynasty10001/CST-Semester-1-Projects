package com.cosacpmg;

import com.j256.ormlite.jdbc.*;
import controller.PlayerController;
import org.junit.*;

import java.sql.SQLException;

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
