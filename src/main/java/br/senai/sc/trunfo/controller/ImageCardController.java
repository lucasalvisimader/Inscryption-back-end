package br.senai.sc.trunfo.controller;

import br.senai.sc.trunfo.service.S3Service;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.net.URL;

@Controller
@CrossOrigin
@NoArgsConstructor
@RequestMapping("/imageCard")
public class ImageCardController {
    private S3Service s3Service;

    @Autowired
    private void setS3Service(S3Service s3Service) {
        this.s3Service = s3Service;
    }

    @PostMapping("/sendImage/{idCard}")
    public ResponseEntity<String> sendImage(@RequestParam("img") MultipartFile image, @PathVariable Long idCard) {
        s3Service.sendImage(image, idCard);
        return ResponseEntity.ok("Image uploaded successfully");
    }

    @GetMapping("/listImage/{bucketName}/{idCard}")
    public ResponseEntity<URL> list(@PathVariable String bucketName, @PathVariable Long idCard) {
        return ResponseEntity.ok(s3Service.listImage(bucketName, idCard));
    }
}
