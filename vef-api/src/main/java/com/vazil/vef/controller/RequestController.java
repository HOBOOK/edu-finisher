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
            form.add("p_id", "c67ec40e5a4442686367a39e92f3e791");
            form.add("s_id", "ujeb");
            form.add("wr_order", formData.getOrder());
            form.add("wr_page", (i+1));
            form.add("wr_id", formData.getId());

            String timestamp = "1675812390";

            int rand = (int) (Math.random() * 10000) + 1000000;
            int add = (int) Math.random() * 100000;

            timestamp = String.valueOf(Timestamp.valueOf(LocalDateTime.now()).getTime() - rand).substring(0, 10);
            log.info(timestamp);
            headers.set("Cookie", "f33d2ed86bd82d4c22123c9da444d8ab=MTY4NjA5Njc5Mw%3D%3D; PHPSESSID=1s4uu426fshqg2ldom4f5trmp3; 2a0d2363701f23f8a75028924a3af643=NTkuMjEuMzQuMjE0; 96b28b766b7e0699aa91c9ff3d890663=aHR0cDovL3ZhemlsLmNhbXB1c2xpbmUua3Iv; play_head=%7B%22wr_id%22%3A1831300%2C%22p_id%22%3A%22c67ec40e5a4442686367a39e92f3e791%22%2C%22s_id%22%3A%22ujeb%22%2C%22wr_order%22%3A%221%22%7D; move_prev=true; play=%7B%22wr_id%22%3A%221831300%22%2C%22p_id%22%3A%22c67ec40e5a4442686367a39e92f3e791%22%2C%22s_id%22%3A%22ujeb%22%2C%22wr_order%22%3A%221%22%2C%22sample_mode%22%3Afalse%7D; page_time=%7B%22key%22%3A%22647fcc58d71ea%22%2C%22time%22%3A" + timestamp +"%7D");
            responseEntity = restTemplate.exchange(requestUrl, HttpMethod.POST, new HttpEntity<>(form, headers), String.class);

            timestamp = String.valueOf(Timestamp.valueOf(LocalDateTime.now()).getTime() - (rand - add)).substring(0, 10);
            log.info(timestamp);
            headers.set("Cookie", "f33d2ed86bd82d4c22123c9da444d8ab=MTY4NjA5Njc5Mw%3D%3D; PHPSESSID=1s4uu426fshqg2ldom4f5trmp3; 2a0d2363701f23f8a75028924a3af643=NTkuMjEuMzQuMjE0; 96b28b766b7e0699aa91c9ff3d890663=aHR0cDovL3ZhemlsLmNhbXB1c2xpbmUua3Iv; play_head=%7B%22wr_id%22%3A1831300%2C%22p_id%22%3A%22c67ec40e5a4442686367a39e92f3e791%22%2C%22s_id%22%3A%22ujeb%22%2C%22wr_order%22%3A%221%22%7D; move_prev=true; play=%7B%22wr_id%22%3A%221831300%22%2C%22p_id%22%3A%22c67ec40e5a4442686367a39e92f3e791%22%2C%22s_id%22%3A%22ujeb%22%2C%22wr_order%22%3A%221%22%2C%22sample_mode%22%3Afalse%7D; page_time=%7B%22key%22%3A%22647fcc58d71ea%22%2C%22time%22%3A" + timestamp +"%7D");
            responseEntity = restTemplate.exchange(requestUrl, HttpMethod.POST, new HttpEntity<>(form, headers), String.class);
        }


        return new ResponseEntity<>(responseEntity, HttpStatus.OK);
    }
}
