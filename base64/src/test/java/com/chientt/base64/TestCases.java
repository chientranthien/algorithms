package com.chientt.base64;

import com.chientt.base64.impl.PaddingBase64;
import org.assertj.core.api.Assertions;
import org.junit.Test;

/**
 *
 * @author chientt
 */
public class TestCases {

    @Test
    public void test1() {
        Encoder encoder = new PaddingBase64();

        String encoded = encoder.encode("Man");

        Assertions.assertThat(encoded).isEqualTo("TWFu");
    }
}
