package com.chientt.base64;

import com.chientt.base64.impl.PaddingBase64;
import java.util.Arrays;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.Test;

/**
 *
 * @author chientt
 */
public class TestCases {

//    @Test
    public void test1() {
        Encoder encoder = new PaddingBase64();

        String encoded = encoder.encode("Man");

        Assertions.assertThat(encoded).isEqualTo("TWFu");
    }

    @Test
    public void test2() {
        List<String> cases = Arrays.asList("pleasure.", "leasure.",
                "easure.", "asure.", "sure.");
        List expectations = Arrays.asList("cGxlYXN1cmUu", "bGVhc3VyZS4=",
                "ZWFzdXJlLg==", "YXN1cmUu", "c3VyZS4=");
        Encoder encoder = new PaddingBase64();

        for (int i = 0; i < cases.size(); i++) {

            String encoded = encoder.encode(cases.get(i));
            System.out.println(cases.get(i));
            System.out.println(encoded);
            Assertions.assertThat(encoded).isEqualTo(expectations.get(i));

        }

    }
}
