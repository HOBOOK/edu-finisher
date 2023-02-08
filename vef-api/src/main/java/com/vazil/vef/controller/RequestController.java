package com.vazil.vef.controller;

import com.vazil.vef.FormData;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@RestController
@AllArgsConstructor
@Log4j2
@CrossOrigin
public class RequestController {
    @PostConstruct
    public void ok() {
        log.info(Timestamp.valueOf(LocalDateTime.now()).getTime());
    }
    @PostMapping("/test")
    public ResponseEntity<?> test(@RequestBody FormData formData) {
//        System.setProperty("sun.net.http.allowRestrictedHeaders", "true");
        String requestUrl = "http://vazil.campusline.kr/lms/plugin/player/ecom_player_update.php";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        headers.set("Origin", "http://vazil.campusline.kr");
        headers.set("Referer", "http://vazil.campusline.kr/lms/plugin/player/ecom_player.php");

        ResponseEntity<String> responseEntity = new ResponseEntity<>("실패", HttpStatus.INTERNAL_SERVER_ERROR);
        RestTemplate restTemplate = new RestTemplate();

        for(int i = 0; i < 14; i++) {
            MultiValueMap<String, Object> form = new LinkedMultiValueMap<>();
            form.add("p_id", "e31f75babe704036f65fc864d30e6f0e");
            form.add("s_id", "abku");
            form.add("wr_order", formData.getOrder());
            form.add("wr_page", (i+1));
            form.add("wr_id", formData.getId());

            String timestamp = "1675812390";

            int rand = (int) (Math.random() * 10000) + 1000000;
            int add = (int) Math.random() * 100000;

            timestamp = String.valueOf(Timestamp.valueOf(LocalDateTime.now()).getTime() - rand).substring(0, 10);
            log.info(timestamp);
            headers.set("Cookie", "f33d2ed86bd82d4c22123c9da444d8ab=MTY3NTQwMDI1OA==; 96b28b766b7e0699aa91c9ff3d890663=aHR0cDovL3ZhemlsLmNhbXB1c2xpbmUua3IvbG1zL2NsYXNzL3N0dWRlbnQv; PHPSESSID=6dm7rov2klvhdt7c0h75la4rc0; 2a0d2363701f23f8a75028924a3af643=NTkuMjEuMzQuMjE0; play={\"wr_id\":\"1703125\",\"p_id\":\"e31f75babe704036f65fc864d30e6f0e\",\"s_id\":\"abku\",\"wr_order\":\"5\",\"sample_mode\":false}; move_prev=false; page_time={\"key\":\"63e2e232d707c\",\"time\":" + timestamp +"}");
            responseEntity = restTemplate.exchange(requestUrl, HttpMethod.POST, new HttpEntity<>(form, headers), String.class);

            timestamp = String.valueOf(Timestamp.valueOf(LocalDateTime.now()).getTime() - (rand - add)).substring(0, 10);
            log.info(timestamp);
            headers.set("Cookie", "f33d2ed86bd82d4c22123c9da444d8ab=MTY3NTQwMDI1OA==; 96b28b766b7e0699aa91c9ff3d890663=aHR0cDovL3ZhemlsLmNhbXB1c2xpbmUua3IvbG1zL2NsYXNzL3N0dWRlbnQv; PHPSESSID=6dm7rov2klvhdt7c0h75la4rc0; 2a0d2363701f23f8a75028924a3af643=NTkuMjEuMzQuMjE0; play={\"wr_id\":\"1703125\",\"p_id\":\"e31f75babe704036f65fc864d30e6f0e\",\"s_id\":\"abku\",\"wr_order\":\"5\",\"sample_mode\":false}; move_prev=false; page_time={\"key\":\"63e2e232d707c\",\"time\":" + timestamp +"}");
            responseEntity = restTemplate.exchange(requestUrl, HttpMethod.POST, new HttpEntity<>(form, headers), String.class);
        }


        return new ResponseEntity<>(responseEntity, HttpStatus.OK);
    }
}
