package com.chientt.base64;

import com.chientt.md5.MD5Encoder;
import java.util.Arrays;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import com.chientt.md5.Hash;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author chientt
 */
public class DehashingTests {

    Hash encoder = new MD5Encoder();

    @Test
    public void test2() throws IOException {

        List<Float> durations = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            haha(durations, i);
        }
        float min = Float.MAX_VALUE,
                max = Float.MIN_VALUE,
                sum = 0;
        System.out.println(durations.size());
        for (Float duration : durations) {
            if (duration > max) {
                max = duration;
            } else {
                min = duration;
            }
            sum += duration;
        }
        
        System.out.println("max: "+max);
        System.out.println("min: "+min);
        System.out.println("avg: "+sum/durations.size());
    }

    private void haha(List<Float> durations, int page) {
        String url = "https://nct-api.uiza.io/api/resource/v1/media/entity/list";
        RestTemplate template = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "2b44900b-31ea-47c7-bfee-0556114ac940");

        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        map.add("limit", "99");
        map.add("page", String.valueOf(page));
//165.35
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);
        ResponseEntity<String> response = template.postForEntity(url, request, String.class);

        ObjectMapper om = new ObjectMapper();
        JSONObject json = new JSONObject(response.getBody());
        JSONArray jsonArray = json.getJSONArray("data");
        for (int i = 0; i < jsonArray.length(); i++) {
            float duration = jsonArray.getJSONObject(i).getFloat("duration");
            System.out.println(duration);
            durations.add(duration);
        }
    }
//    @Test

    public void test1() {
        List<String> inputs = Arrays.asList(
                "MD5 processes",
                "Message-Digest",
                "Data Security");
        List<String> expectations = Arrays.asList(
                "38b79b553ed9b565d8f80f293a0b92eb",
                "703a404c0e706b05e970cc3b1d137cb7",
                "6ebbd7e9d030b1cb13c27f56cba9376e");

        for (int i = 0; i < inputs.size(); i++) {
            String input = inputs.get(i);
            String result = encoder.hash(input);
            String expectation = expectations.get(i);
            Assertions.assertThat(result).isEqualTo(expectation);
        }
    }
}
