package com.lec.spring.controller;

import com.lec.spring.domain.Attachment;
import com.lec.spring.repository.AttachmentRepository;
import com.lec.spring.service.AttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;


@RestController   // 데이터를 response 하기 위함.    <-  @Controller + @ResponseBody
public class AttachmentController {

    @Value("${app.upload.path}")  // org.springframework.beans.factory.annotation.Value
    private String uploadDir;

    @Autowired
    private AttachmentService attachmentService;



    private AttachmentRepository attachmentRepository;

    @Autowired
    public void setFileService(AttachmentService attachmentService) {
        this.attachmentService = attachmentService;
    }

    public AttachmentController() {
        System.out.println(getClass().getName() + "() 생성");
    }


    // 파일다운로드 (id: 첨부파일 id)
    // ResponseEntity<T> 를 사용하여
    // '직접' Response data 를 구성
    @RequestMapping("board/download")
    public ResponseEntity<Object> download(Long id) {
        if (id == null) return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);  // 400

        Attachment file = attachmentService.findById(id);
        if (file == null) return new ResponseEntity<>(null, HttpStatus.NOT_FOUND); // 404

        String sourceName = file.getSourcename();  // 원본이름
        String fileName = file.getFilename();  // 저장된 파일명

        String path = new File(uploadDir, fileName).getAbsolutePath();

        try {
            // 파일 유형 (Mimetype) 추출
            String mimeType = Files.probeContentType(Paths.get(path));

            // 유형이 지정되지 않은 경우
            if (mimeType == null) {
                mimeType = "application/octet-stream";  // 일련의 8bit 스트림 타입.  유형이 알려지지 않은 파일에 대한 형식 지정
            }

            Path filePath = Paths.get(path);
            Resource resource = new InputStreamResource(Files.newInputStream(filePath));

            // response header 세팅
            HttpHeaders headers = new HttpHeaders();
            // ↓ 원본 파일 이름(sourceName) 으로 다운로드 하게 하기위한 세팅
            headers.setContentDisposition(ContentDisposition.builder("attachment").filename(URLEncoder.encode(sourceName, "utf-8")).build());
            headers.setCacheControl("no-cache");
            headers.setContentType(MediaType.parseMediaType(mimeType));

            // ResponseEntity<T> 리턴 (body, header, status)
            return new ResponseEntity<>(resource, headers, HttpStatus.OK);  // 200

        } catch (IOException e) {
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);  // 409
        }
    }

//    private final String directoryPath = "/Users/Won/Desktop/HotProject/upload";
//
//    @GetMapping("/{url}")
//    public List<BufferedImage> getImageUrl(@PathVariable String url, String filename, Long id) {
//        List<BufferedImage> images = new ArrayList<>();
//
//        if (url.equals(filename) && id != null) {
//            // upload 디렉토리에서 파일 목록을 읽어옵니다.
//            File[] files = new File(directoryPath).listFiles();
//
//            if (files != null) {
//                // 파일 목록을 순회하며 이미지 파일을 찾습니다.
//                for (File file : files) {
//                    if (file.isFile() && file.getName().toLowerCase().endsWith(".jpg") && file.getName().equals(filename + ".jpg")) {
//                        // 일치하는 파일을 BufferedImage로 읽어옵니다.
//                        try {
//                            BufferedImage image = ImageIO.read(file);
//                            if (image != null) {
//                                images.add(String.valueOf(image));
//                            }
//                        } catch (IOException e) {
//                            // 이미지를 읽을 수 없는 경우 예외 처리
//                            e.printStackTrace();
//                            // 필요에 따라 예외 처리를 수행하세요.
//                        }
//                    }
//                }
//            }
//        } else {
//            // 다른 경우에 대한 처리
//            // 적절한 응답을 보내거나 예외를 처리하세요.
//        }
//
//        return images;
//    }


    // 실제 데이터베이스에서 이미지 URL을 가져오는 로직을 수행하는 메서드 (가정)
//    private String getImageUrlFromDatabase(String url) {
//        // 데이터베이스에서 imageId에 해당하는 이미지 URL을 가져오는 로직을 구현합니다.
//        // 이 메서드는 예시일 뿐, 실제 데이터베이스와 연동하여 이미지 URL을 가져오는 로직으로 대체되어야 합니다.
//        // 예를 들어, JPA나 JDBC를 사용하여 데이터베이스에 쿼리를 실행하여 이미지 URL을 가져올 수 있습니다.
//
//        // 여기에서는 간단히 URL을 하드코딩하여 반환합니다. 실제로는 데이터베이스에서 해당 이미지 URL을 가져와야 합니다.
//        return  url; // 이미지 URL 반환 (가정)
//    }
//    private String imgFileName(String filename){
//
//        return filename;
//    }

//    private Image getImageImgFilePathUpolad(String filename){
//        String directoryPath = "/Users/Won/Desktop/HotProject/upload"; // 실제 경로로 수정 필요
//        if ()
//    }

//    @GetMapping("{url}") // 이미지 불러오기 엔드포인트
//    public ResponseEntity<Resource> getImage(@PathVariable String filename) {
//        // 이미지가 저장된 디렉토리 경로 설정
//        String directoryPath = "/Users/Won/Desktop/HotProject/upload"; // 실제 경로로 수정 필요
//
//        // 파일 이름을 URL 안전 문자열로 변환
//        String encodedFilename = URLEncoder.encode(filename, StandardCharsets.UTF_8);
//
//        Path imagePath = Paths.get(directoryPath).resolve(encodedFilename);
//
//        try {
//            // 이미지 파일을 로드하여 Resource 객체로 변환
//            Resource resource = new UrlResource(imagePath.toUri());
//
//            // 이미지의 MIME 타입 확인
//            String mimeType = Files.probeContentType(imagePath);
//
//            // response header 설정
//            HttpHeaders headers = new HttpHeaders();
//            headers.setContentType(MediaType.parseMediaType(mimeType));
//
//            // ResponseEntity로 이미지 반환
//            return ResponseEntity.ok().headers(headers).body(resource);
//        } catch (IOException e) {
//            // 이미지를 찾을 수 없는 경우 404 에러 반환
//            e.printStackTrace();
//            return ResponseEntity.notFound().build();
//        }
//    }

}

