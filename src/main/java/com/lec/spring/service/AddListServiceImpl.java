//package com.lec.spring.service;
//
//import com.lec.spring.config.PrincipalDetails;
//import com.lec.spring.domain.Attachment;
//import com.lec.spring.domain.TouristData;
//import com.lec.spring.domain.User;
//import com.lec.spring.repository.AttachmentRepository;
//import com.lec.spring.repository.TouristRepository;
//import com.lec.spring.repository.UserRepository;
//import com.lec.spring.util.U;
//import jakarta.servlet.http.HttpSession;
//import org.apache.ibatis.session.SqlSession;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.security.core.Authentication;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.util.StringUtils;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.multipart.MultipartFile;
//
//import javax.imageio.ImageIO;
//import java.awt.image.BufferedImage;
//import java.io.File;
//import java.io.IOException;
//import java.net.URLEncoder;
//import java.nio.charset.StandardCharsets;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.nio.file.StandardCopyOption;
//import java.security.MessageDigest;
//import java.security.NoSuchAlgorithmException;
//import java.util.Base64;
//import java.util.List;
//import java.util.Map;
//
//
//@Service
//public class AddListServiceImpl implements AddListService {
//    @Value("${app.upload.path}")
//    private String uploadDir;
//    private TouristRepository touristRepository;
//    private UserRepository userRepository;
//
//    private AttachmentRepository attachmentRepository;
//
//    @Autowired
//    public AddListServiceImpl(SqlSession sqlSession) {
//        touristRepository = sqlSession.getMapper(TouristRepository.class);
//        userRepository = sqlSession.getMapper(UserRepository.class);
//        attachmentRepository = sqlSession.getMapper(AttachmentRepository.class);
//        System.out.println("AddListService() 생성");
//    }
//    @Override
//    public int addDestination(TouristData touristData, Map<String, MultipartFile> files) {
//
//
//        User user = U.getLoggedUser();
//        // 여행지 추가 로직 구현
//
//
//
//
////        int cnt = touristRepository.save(touristData);
//
//        addFiles2(files, touristData.getId());
//
//        return  1;
//
//
//    }
//
//
//
//    private void addFiles2(Map<String, MultipartFile> files, Long id){
//        User user = U.getLoggedUser();
//        user = userRepository.findById(user.getUid());
//
//        if (files !=null) {
//            for (var entry : files.entrySet()) {
//                // 첨부 파일 정보 처리
//                if (!entry.getKey().startsWith("upfile")) continue;
//
//                // 물리적인 파일 저장
//                Attachment file = upload(entry.getValue());
//
//                // 성공적으로 파일 저장되면 DB에 저장
//                if (file != null) {
//                    file.setUid(user.getUid()); // User 객체에서 uid 가져와서 Attachment에 설정
//
//                    attachmentRepository.saveimg(file); // 첨부 파일 DB에 저장
//                }
//            }
//        }
//    }
//
//    // 물리적으로 파일 저장.  중복된 이름 rename 처리
//    private Attachment upload(MultipartFile multipartFile) {
//        Attachment attachment = null;
//
//        // 담긴 파일이 없으면 pass
//        String originalFilename = multipartFile.getOriginalFilename();
//        if(originalFilename == null || originalFilename.length() == 0) return null;
//
//        // 원본파일명
//        String sourceName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
//        // 저장될 파일명
//        String fileName = sourceName;
//
//        // 파일명 이 중복되는지 확인
//        File file = new File(uploadDir, sourceName);
//        if(file.exists()){  // 이미 존재하는 파일명,  중복되면 다름 이름으로 변경하여 저장
//            // a.txt => a_2378142783946.txt  : time stamp 값을 활용할거다!
//            int pos = fileName.lastIndexOf(".");
//            if(pos > -1){   // 확장자가 있는 경우
//                String name = fileName.substring(0, pos);  // 파일 '이름'
//                String ext = fileName.substring(pos + 1);   // 파일 '확장자'
//
//                // 중복방지를 위한 새로운 이름 (현재시간 ms) 를 파일명에 추가
//                fileName = name + "_" + System.currentTimeMillis() + "." + ext;
//            } else {  // 확장자가 없는 경우
//                fileName += "_" + System.currentTimeMillis();
//            }
//        }
//        // 저장할 파일명
//        System.out.println("fileName: " + fileName);
//
//        // java.nio
//        Path copyOfLocation = Paths.get(new File(uploadDir, fileName).getAbsolutePath());
//        System.out.println(copyOfLocation);
//
//        try {
//            // inputStream을 가져와서
//            // copyOfLocation (저장위치)로 파일을 쓴다.
//            // copy의 옵션은 기존에 존재하면 REPLACE(대체한다), 오버라이딩 한다
//
//            Files.copy(
//                    multipartFile.getInputStream(),
//                    copyOfLocation,
//                    StandardCopyOption.REPLACE_EXISTING    // 기존에 존재하면 덮어쓰기
//            );
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        String fileUrl = generateFileUrl(fileName);
//        attachment = Attachment.builder()
//                .filename(fileName)   // 저장된 이름
//                .sourcename(sourceName)  // 원본 이름
//                .url(fileUrl)
//                .build();
//
//        return attachment;
//    }
//
//    private String generateFileUrl(String fileName) {
//        try {
//            MessageDigest digest = MessageDigest.getInstance("SHA-256");
//            byte[] hash = digest.digest(fileName.getBytes());
//            String serverBaseUrl = "http://localhost:8080/";
//            // Base64 또는 다른 방식을 사용하여 바이트 배열을 문자열로 변환
//            String encodedHash = Base64.getUrlEncoder().withoutPadding().encodeToString(hash);
//
//            // 짧은 길이로 자른다거나 필요에 따라 추가적인 처리를 수행할 수 있음
//            String shortenedURL = serverBaseUrl+encodedHash.substring(0, 10); // 예시로 앞 10자리만 사용
//
//            return shortenedURL;
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//
//}
