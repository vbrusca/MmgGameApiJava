package net.middlemind.MmgApiTests;

import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;
import net.middlemind.MmgGameApiJava.MmgBase.MmgApiUtils;
import net.middlemind.MmgGameApiJava.MmgBase.MmgBmp;
import net.middlemind.MmgGameApiJava.MmgBase.MmgColor;
import net.middlemind.MmgGameApiJava.MmgBase.MmgObj;
import net.middlemind.MmgGameApiJava.MmgBase.MmgRect;
import net.middlemind.MmgGameApiJava.MmgBase.MmgVector2;
import org.junit.Assert;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Victor G. Brusca
 */
public class MmgBmpUnitTest {
    
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
    public void testStaticMembers() {
        //do nothing
    }

    @Test
    @SuppressWarnings("UnusedAssignment")
    public void testEquals() {
        //VARS
        MmgBmp b1;
        MmgBmp b2;
        MmgBmp b3;
        MmgObj o1;
        MmgObj o2;
        Image i = null;
        String src = "/Users/victor/Documents/files/netbeans_workspace/MmgGameApiJava/cfg/drawable/a_btn.png";
        
        //TEST 1
        b1 = new MmgBmp();
        b2 = new MmgBmp();
        bmpEqualsTests(b1, b2);
        
        //TEST 2
        o1 = new MmgObj(10, 10, 50, 50, true, MmgColor.GetBlack());
        o2 = new MmgObj(10, 10, 50, 50, true, MmgColor.GetBlack());
        b1 = new MmgBmp(o1);
        b2 = new MmgBmp(o2);
        bmpEqualsTests(b1, b2);
        
        //TEST 3
        b3 = new MmgBmp();
        b1 = new MmgBmp(b3);
        b2 = new MmgBmp(b3);
        bmpEqualsTests(b1, b2);
        
        //TEST 4
        try {
            i = ImageIO.read(new File(src));
        }catch(Exception e) {
            MmgApiUtils.wrErr(e);
        }
        
        b1 = new MmgBmp(i);
        b2 = new MmgBmp(i);
        bmpEqualsTests(b1, b2);
        
        //TEST 5
        b1 = new MmgBmp(i, null, null, MmgVector2.GetOriginVec(), MmgVector2.GetUnitVec(), 45.0f);
        b2 = new MmgBmp(i, null, null, MmgVector2.GetOriginVec(), MmgVector2.GetUnitVec(), 45.0f);
        bmpEqualsTests(b1, b2);
        
        //TEST 6
        b1 = new MmgBmp(i, MmgVector2.GetOriginVec(), MmgVector2.GetOriginVec(), MmgVector2.GetUnitVec(), 45.0f);
        b2 = new MmgBmp(i, MmgVector2.GetOriginVec(), MmgVector2.GetOriginVec(), MmgVector2.GetUnitVec(), 45.0f);
        bmpEqualsTests(b1, b2);
    }

    private void bmpEqualsTests(MmgBmp b1, MmgBmp b2) {
        //VARS
        MmgVector2 o;
        MmgRect s;
        MmgRect d;
        Image i = null;
        String src = "/Users/victor/Documents/files/netbeans_workspace/MmgGameApiJava/cfg/drawable/a_btn.png";
        float r;
        
        //TEST 1
        Assert.assertEquals(true, b1.Equals(b2));
        
        //TEST 2
        o = new MmgVector2(20, 20);
        b1.SetOrigin(o);
        Assert.assertEquals(false, b1.Equals(b2));
        b2.SetOrigin(o);
        Assert.assertEquals(true, b1.Equals(b2));
        
        //TEST 3
        o = new MmgVector2(0.15, 0.15);
        b1.SetScaling(o);
        Assert.assertEquals(false, b1.Equals(b2));
        b2.SetScaling(o);
        Assert.assertEquals(true, b1.Equals(b2));
        
        //TEST 4
        s = new MmgRect(0, 0, 20, 20);
        b1.SetSrcRect(s);
        Assert.assertEquals(false, b1.Equals(b2));
        b2.SetSrcRect(s);
        Assert.assertEquals(true, b1.Equals(b2));
        
        //TEST 5
        d = new MmgRect(0, 0, 30, 30);
        b1.SetDstRect(d);
        Assert.assertEquals(false, b1.Equals(b2));
        b2.SetDstRect(d);
        Assert.assertEquals(true, b1.Equals(b2));

        //TEST 6        
        try {
            i = ImageIO.read(new File(src));
        }catch(Exception e) {
            MmgApiUtils.wrErr(e);
        }
        
        b1.SetImage(i);
        Assert.assertEquals(false, b1.Equals(b2));
        b2.SetImage(i);
        Assert.assertEquals(true, b1.Equals(b2));
        
        //TEST 7
        r = 90;
        b1.SetRotation(r);
        Assert.assertEquals(false, b1.Equals(b2));
        b2.SetRotation(r);
        Assert.assertEquals(true, b1.Equals(b2));
        
        //TEST 8 
        b1.DRAW_MODE = MmgBmp.MmgBmpDrawMode.DRAW_BMP_BASIC_CACHE;
        Assert.assertEquals(false, b1.Equals(b2));
        b2.DRAW_MODE = MmgBmp.MmgBmpDrawMode.DRAW_BMP_BASIC_CACHE;
        Assert.assertEquals(true, b1.Equals(b2));
    }
    
    @Test
    @SuppressWarnings("UnusedAssignment")
    public void testGettersSetters() {
        //VARS
        MmgBmp b1;
        String s;
        MmgRect d;
        Image i = null;        
        String src = "/Users/victor/Documents/files/netbeans_workspace/MmgGameApiJava/cfg/drawable/a_btn.png";
        MmgVector2 v;
        float f;
        
        //TEST 1
        b1 = new MmgBmp();
        s = "T1";
        b1.SetBmpIdStr(s);
        Assert.assertEquals(s, b1.GetBmpIdStr());
        
        //TEST 2
        d = new MmgRect(0, 0, 50, 50);
        b1.SetDstRect(d);
        Assert.assertEquals(true, d.Equals(b1.GetDstRect()));
        
        //TEST 3        
        try {
            i = ImageIO.read(new File(src));
        }catch(Exception e) {
            MmgApiUtils.wrErr(e);
        }
        b1.SetImage(i);
        Assert.assertEquals(i, b1.GetImage());
        
        //TEST 4
        v = new MmgVector2(100, 50);
        b1.SetOrigin(v);
        Assert.assertEquals(true, v.Equals(b1.GetOrigin()));
        
        //TEST 5
        f = 32.0f;
        v = new MmgVector2(100, 50);
        b1.SetRotation(f);
        Assert.assertEquals(f, b1.GetRotation(), MmgApiTestSuite.DELTA_D);
    }
    
    @Test
    @SuppressWarnings("UnusedAssignment")
    public void testCloning() {
        
    }
    
    @Test
    @SuppressWarnings("UnusedAssignment")
    public void testConstructors() {
        
    }
}
