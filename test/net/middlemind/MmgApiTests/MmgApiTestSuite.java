/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.middlemind.MmgApiTests;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author Victor G. Brusca
 */
@RunWith(Suite.class)
@Suite.SuiteClasses(
    {
        MmgObjUnitTest.class
        ,MmgColorUnitTest.class
        ,MmgVector2UnitTest.class
        ,MmgRectUnitTest.class
        ,MmgBmpUnitTest.class
    }
)

public class MmgApiTestSuite {

    public static double DELTA_D = 0.00001;
    
    @BeforeClass
    public static void setUpClass() throws Exception {
        
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
    
}
