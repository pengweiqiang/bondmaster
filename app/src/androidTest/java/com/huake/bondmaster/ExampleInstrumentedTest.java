package com.huake.bondmaster;

import android.support.test.runner.AndroidJUnit4;

import com.huake.bondmaster.util.RSAUtils;

import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
//        Context appContext = InstrumentationRegistry.getTargetContext();

//        assertEquals("com.huake.bondmaster", appContext.getPackageName());
        System.out.println("123");
        String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCJ3UEiNSpawIpRjQem4l0c/D+lzWgZBN6wg8w5" +
                "Bg3lSFXlWAYodCyUlE8lVQXRFOyPXTXMzK/yNtVPq4yR2I1fqpw3NYI4lX5RMCXafD/kiiiQMm0P" +
                "7VElUkP4rRma9/D+e+NGAsyb5uFkMm8zspkPKdXN+eTifBA4Ynm/SdIktQIDAQAB";
        try {
            String ss = RSAUtils.encryptByPublicKey("123456",RSAUtils.getPublicKey(publicKey));
            System.out.println(ss);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
