package br.senai.sc.trunfo.service;

import com.amazonaws.services.s3.AmazonS3;
import lombok.NoArgsConstructor;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.springframework.beans.factory.annotation.Value;
import java.io.IOException;
import java.net.URL;

@Service
@NoArgsConstructor
public class S3Service {
    private static final String BUCKET_NAME = "bucket-romario";
    @Value("${accessKey}")
    private String accessKey;
    @Value("${secretKey}")
    private String secretKey;

    private static URL url;

    public void sendImage(MultipartFile image) {
        try {
            // Criando o cliente Amazon S3 com as credenciais pré-cadastradas
            BasicAWSCredentials basicAWSCredentials = new BasicAWSCredentials(accessKey, secretKey);
            AmazonS3 amazonS3Client = new AmazonS3Client(basicAWSCredentials);

            // Criando as variáveis para envio
//            UUID keyName = UUID.randomUUID();

            amazonS3Client.putObject(new PutObjectRequest(BUCKET_NAME,
                    "image-trump-inscryption", image.getInputStream(), null));

        } catch (AmazonS3Exception | IOException e) {
            throw new RuntimeException(e);
        }
    }
    public URL listImage(String bucketName, String keyName) {
        try {
            BasicAWSCredentials basicAWSCredentials = new BasicAWSCredentials(accessKey, secretKey);
            AmazonS3 amazonS3Client = new AmazonS3Client(basicAWSCredentials);

            if (amazonS3Client.doesBucketExist(bucketName)) {
                url = amazonS3Client.generatePresignedUrl(bucketName, keyName,
                        DateTime.now().plusDays(1).toDate());
            }
        } catch (AmazonS3Exception e) {
            System.exit(0);
        }
        return url;
    }
}
