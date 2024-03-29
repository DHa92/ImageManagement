package com.example.imagetagmanagement.service;

import com.example.imagetagmanagement.model.Image;
import com.example.imagetagmanagement.repository.ImageRepository;
import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@Transactional
public class ImageServicesImplTest {

    @Autowired
    private ImageRepository imageRepository;

    private Map<String, String> 샘플메타데이터() {
        Map<String, String> metadata = new HashMap<>();
        metadata.put("extension", "jpge");
        metadata.put("filesize", "100203");
        return metadata;
    }

    @Test
    @DisplayName("이미지 저장 및 조회")
    void 이미지저장및조회() {
        Image image = Image.builder().name("이미지").fileName("이미지.jpg").fileLocation("/이미지.jpg").metadata(샘플메타데이터()).build();
        imageRepository.save(image);

        Optional<Image> retrievedImage = imageRepository.findById(image.getUuid());

        retrievedImage.ifPresent(value -> {
            assertThat(value.getName()).isEqualTo("이미지");
            assertThat(value.getUuid()).isNotBlank();
            assertThat(value.getFileName()).isEqualTo("이미지.jpg");
            assertThat(value.getFileLocation()).isEqualTo("/이미지.jpg");
            assertThat(value.getMetadata()).isEqualTo(샘플메타데이터());

        });


    }
}
