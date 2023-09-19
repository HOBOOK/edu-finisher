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


    @PostMapping("/complete/{id}")
    public ResponseEntity<?> test(@PathVariable String id) {
//        System.setProperty("sun.net.http.allowRestrictedHeaders", "true");
        String requestUrl = "https://vazil.allteaching.biz/lms/plugin/player/ecom_player_update.php";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        headers.set("Origin", "https://vazil.allteaching.biz");
        headers.set("Host", "vazil.allteaching.biz");
        headers.set("Referer", "https://vazil.allteaching.biz/lms/plugin/player/ecom_player.php");
        headers.set("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");

        ResponseEntity<String> responseEntity = new ResponseEntity<>("실패", HttpStatus.INTERNAL_SERVER_ERROR);
        RestTemplate restTemplate = new RestTemplate();

        for(int i = 0; i < 14; i++) {
            MultiValueMap<String, Object> form = new LinkedMultiValueMap<>();
            form.add("p_id", "6050f6592207e80772e7375fdc6878ca");
            form.add("s_id", "ujec");
            form.add("wr_order", 22);
            form.add("wr_page", (i+1));
            form.add("wr_id", id);

            String timestamp = "1695085961";

            int rand = (int) (Math.random() * 10000) + 1000000;
            int add = (int) Math.random() * 100000;

            timestamp = String.valueOf(Timestamp.valueOf(LocalDateTime.now()).getTime() - rand).substring(0, 10);
            headers.set("Cookie", "PHPSESSID=w7~et4l440ht81rdklen00caic492; f33d2ed86bd82d4c22123c9da444d8ab=MTY5NTA4NTI5Mg%3D%3D; 96b28b766b7e0699aa91c9ff3d890663=aHR0cHM6Ly93d3cuZ29vZ2xlLmNvbS8%3D; 2a0d2363701f23f8a75028924a3af643=NTkuMjEuMzQuMjE0; _fbp=fb.1.1695085295456.306448687; play_head=%7B%22wr_id%22%3A1922378%2C%22p_id%22%3A%226050f6592207e80772e7375fdc6878ca%22%2C%22s_id%22%3A%22ujec%22%2C%22wr_order%22%3A%221%22%7D; play=%7B%22wr_id%22%3A%221922378%22%2C%22p_id%22%3A%226050f6592207e80772e7375fdc6878ca%22%2C%22s_id%22%3A%22ujec%22%2C%22wr_order%22%3A%221%22%2C%22sample_mode%22%3Afalse%7D; move_prev=false; page_time=%7B%22key%22%3A%226508f6e147e7b%22%2C%22time%22%3A" + timestamp +"%7D");
            responseEntity = restTemplate.exchange(requestUrl, HttpMethod.POST, new HttpEntity<>(form, headers), String.class);

            timestamp = String.valueOf(Timestamp.valueOf(LocalDateTime.now()).getTime() - (rand - add)).substring(0, 10);
            headers.set("Cookie", "PHPSESSID=w7~et4l440ht81rdklen00caic492; f33d2ed86bd82d4c22123c9da444d8ab=MTY5NTA4NTI5Mg%3D%3D; 96b28b766b7e0699aa91c9ff3d890663=aHR0cHM6Ly93d3cuZ29vZ2xlLmNvbS8%3D; 2a0d2363701f23f8a75028924a3af643=NTkuMjEuMzQuMjE0; _fbp=fb.1.1695085295456.306448687; play_head=%7B%22wr_id%22%3A1922378%2C%22p_id%22%3A%226050f6592207e80772e7375fdc6878ca%22%2C%22s_id%22%3A%22ujec%22%2C%22wr_order%22%3A%221%22%7D; play=%7B%22wr_id%22%3A%221922378%22%2C%22p_id%22%3A%226050f6592207e80772e7375fdc6878ca%22%2C%22s_id%22%3A%22ujec%22%2C%22wr_order%22%3A%221%22%2C%22sample_mode%22%3Afalse%7D; move_prev=false; page_time=%7B%22key%22%3A%226508f6e147e7b%22%2C%22time%22%3A" + timestamp +"%7D");
            responseEntity = restTemplate.exchange(requestUrl, HttpMethod.POST, new HttpEntity<>(form, headers), String.class);
        }


        return new ResponseEntity<>(responseEntity, HttpStatus.OK);
    }
}
