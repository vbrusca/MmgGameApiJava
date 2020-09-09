package net.middlemind.MmgGameApiJava.MmgApiTests;

import net.middlemind.MmgGameApiJava.MmgBase.MmgHelper;
import net.middlemind.MmgGameApiJava.MmgBase.MmgSound;
import org.junit.Assert;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Victor G. Brusca, Middlemind Games
 */
public class MmgSoundUnitTest_2 {
    
    public MmgSoundUnitTest_2() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    @SuppressWarnings("UnusedAssignment")
    public void test1() {
        MmgSound s1, s2;
        
        s1 = MmgHelper.GetBasicSound("/Users/victor/Documents/files/netbeans_workspace/MmgGameApiJava/cfg/playable/auto_load/jump1.wav");    
        
        Assert.assertEquals(MmgSound.MMG_SOUND_GLOBAL_VOLUME, 0.64999f, 0.001);
        Assert.assertEquals(-24.0866f, s1.GetCurrentVolume(), 0.001);
        Assert.assertEquals(s1.GetCurrentRate(), 1.0f, 0.001);
    }    
}
