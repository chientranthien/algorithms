package com.chientt.md5;

import com.chientt.md5.MD5Encoder;
import java.util.Arrays;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import com.chientt.md5.Hash;

/**
 *
 * @author chientt
 */
public class HashingTests {

    Hash encoder = new MD5Encoder();

    @Test
    public void test1() {
        List<String> expectations = Arrays.asList(
                "MD5 processes",
                "Message-Digest",
                "Data Security");
        List<String> inputs  = Arrays.asList(
                "38b79b553ed9b565d8f80f293a0b92eb",
                "703a404c0e706b05e970cc3b1d137cb7",
                "6ebbd7e9d030b1cb13c27f56cba9376e");

        for (int i = 0; i < inputs.size(); i++) {
            String input = inputs.get(i);
            String result = encoder.dehash(input);
            String expectation = expectations.get(i);
            Assertions.assertThat(result).isEqualTo(expectation);
        }
    }
}
