package net.middlemind.MmgGameApiJava.MmgApiTests;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author Victor G. Brusca, Middlemind Games
 */
@RunWith(Suite.class)
@Suite.SuiteClasses(
    {
        Mmg9SliceUnitTest.class
        ,MmgBmpUnitTest.class
        ,MmgCfgFileEntryUnitTest_2.class
        ,MmgColorUnitTest.class
        ,MmgContainerUnitTest.class
        ,MmgDirUnitTest_2.class
        ,MmgFontUnitTest_2.class
        ,MmgObjUnitTest.class
        ,MmgRectUnitTest.class
        ,MmgRectUnitTest_2.class            
        ,MmgVector2UnitTest.class
        ,MmgVector2UnitTest_2.class
        ,MmgVector2IntUnitTest_2.class            
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
