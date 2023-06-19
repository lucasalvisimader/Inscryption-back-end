package br.senai.sc.trunfo.service;

import br.senai.sc.trunfo.model.dto.ImageCardDTO;
import br.senai.sc.trunfo.model.entity.Card;
import br.senai.sc.trunfo.model.entity.ImageCard;
import br.senai.sc.trunfo.model.exception.NotFoundException;
import br.senai.sc.trunfo.repository.S3Repository;
import com.amazonaws.services.s3.AmazonS3;
import lombok.NoArgsConstructor;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.multipart.MultipartFile;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.springframework.beans.factory.annotation.Value;
import java.io.IOException;
import java.net.URL;
import java.util.UUID;

@Service
@NoArgsConstructor
public class S3Service {
    private static final String BUCKET_NAME = "bucket-romario";
    @Value("${accessKey}")
    private String accessKey;
    @Value("${secretKey}")
    private String secretKey;
    private S3Repository s3Repository;

    @Autowired
    public S3Service(S3Repository s3Repository) {
        this.s3Repository = s3Repository;
    }

    @Autowired
    private CardService cardService;
    private static URL url;

    public void sendImage(MultipartFile image, Long idCard) {
        try {
            String keyName = "image-trump-inscryption" + UUID.randomUUID() + ".png";
            // Criando o cliente Amazon S3 com as credenciais pré-cadastradas
            BasicAWSCredentials basicAWSCredentials = new BasicAWSCredentials(accessKey, secretKey);
            AmazonS3 amazonS3Client = new AmazonS3Client(basicAWSCredentials);

            amazonS3Client.putObject(new PutObjectRequest(BUCKET_NAME,
                    keyName, image.getInputStream(), null));
            Card card = cardService.list(idCard);

            ImageCardDTO imageCardDTO = new ImageCardDTO(keyName, card);
            ImageCard imageCard = new ImageCard();
            BeanUtils.copyProperties(imageCardDTO, imageCard);
            if (s3Repository.findByCard(card) == null) {
                s3Repository.save(imageCard);
            }
        } catch (AmazonS3Exception | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public URL listImage(String bucketName, Long idCard) {
        try {
            BasicAWSCredentials basicAWSCredentials = new BasicAWSCredentials(accessKey, secretKey);
            AmazonS3 amazonS3Client = new AmazonS3Client(basicAWSCredentials);

            if (amazonS3Client.doesBucketExist(bucketName)) {
                System.out.println(s3Repository.findByCard(cardService.list(idCard)));
                System.out.println(cardService.list(idCard));
                url = amazonS3Client.generatePresignedUrl(bucketName,
                        s3Repository.findByCard(cardService.list(idCard)).getReference(),
                        DateTime.now().plusDays(1).toDate());
            }
        } catch (AmazonS3Exception e) {
            System.exit(0);
        } catch (NullPointerException e) {
            throw new RuntimeException(e);
        }
        System.out.println(url);
        return url;
    }

    public ImageCard list(Long id) {
        return s3Repository.findById(id).orElseThrow(() -> new NotFoundException("Image Card Not Found"));
    }
}
