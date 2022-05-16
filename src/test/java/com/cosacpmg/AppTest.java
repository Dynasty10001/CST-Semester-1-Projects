package com.cosacpmg;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import controllers.TeamController;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }
    
    
    @Test
    public void testDBConnection()
    {
        TeamController teamController = new TeamController(App.connection);
        assertNotNull(teamController.repo);
    }

}
