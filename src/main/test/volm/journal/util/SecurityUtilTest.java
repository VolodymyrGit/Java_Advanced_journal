package volm.journal.util;

import org.junit.Test;

import static org.junit.Assert.*;

public class SecurityUtilTest {

    @Test
    public void getSecurePassword() {
        String securePassword = SecurityUtil.getSecurePassword("1", "1234");
        System.out.println(securePassword);
    }
}