/**
 * #민호
 */

package com.lec.spring.service;

import com.lec.spring.domain.User;
import com.lec.spring.domain.role;
import com.lec.spring.repository.AuthorityRepository;
import com.lec.spring.repository.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.mail.HtmlEmail;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.PrintWriter;
import java.util.List;
import java.util.Random;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    private UserRepository userRepository;

    private AuthorityRepository authorityRepository;

    @Value("${spring.mail.host}")
    private String host;

    @Value("${spring.mail.port}")
    private int port;

    @Value("${spring.mail.username}")
    private String username;

    @Value("${spring.mail.password}")
    private String password;


    private String code;
    private String usernameInput;
    private String emailInput;


    @Autowired
    public UserServiceImpl(SqlSession sqlSession){
        userRepository = sqlSession.getMapper(UserRepository.class);
        authorityRepository = sqlSession.getMapper(AuthorityRepository.class);
        System.out.println(getClass().getName() + "() 생성");
    }



    @Override
    public boolean isExist(String username) {
        User user = findByUsername(username);
        return (user != null ) ? true : false;
    }

    @Override
    public boolean isExistNick(String nickname) {
        User user = findByNickname(nickname);
        return (user != null ) ? true : false;
    }

    @Override
    public boolean isExistEmail(String email) {
        User user = findByEmail(email);
        return (user != null ) ? true : false;
    }


    @Override
    public int signup(User user) {
        //DB 에는 회원 username 을 대문자로 저장
        user.setUsername(user.getUsername().toLowerCase());

        // password 는 암호화 해서 저장.  PasswordEncoder 객체 사용
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user); //새로운 회원 (User) 저장. id 값 받아옴.

        //신규 회원은 ROLE_MEMBER 권한을 부여하기
        role auth = authorityRepository.findByName("ROLE_MEMBER");

        Long user_id = user.getUid();
        Long auth_id = auth.getRole_id();
        authorityRepository.addAuthority(auth_id, user_id);

        return 1;
    }

    @Override
    public List<role> selectAuthoritiesById(Long uid) {//특정 회원 권한 가져오기
        User user = userRepository.findById(uid);

        return authorityRepository.findByUser(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User findByNickname(String nickname) {
        return userRepository.findByNickname(nickname);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public String findId(HttpServletResponse response, User user, String email) throws Exception {
        response.setContentType("text/html; charset=utf-8");
        PrintWriter out = response.getWriter();
        String uid = userRepository.findId(email);
        emailInput = email;

        if (uid == null) {
            out.println("<script>");
            out.println("alert('가입된 아이디가 없습니다.');");
            out.println("history.go(-1);");
            out.println("</script>");
            out.close();
            return null;
        } else if (uid.contains("google")) {
            out.println("<script>");
            out.println("alert('구글 계정으로 가입하셨습니다. 구글 로그인을 진행해주세요.');");
            out.println("history.go(-1);");
            out.println("</script>");
            out.close();
            return null;
        } else if (uid.contains("facebook")) {
            out.println("<script>");
            out.println("alert('페이스북 계정으로 가입하셨습니다. 페이스북 로그인을 진행해주세요.');");
            out.println("history.go(-1);");
            out.println("</script>");
            out.close();
            return null;
        } else if (uid.contains("naver")) {
            out.println("<script>");
            out.println("alert('네이버 계정으로 가입하셨습니다. 네이버 로그인을 진행해주세요.');");
            out.println("history.go(-1);");
            out.println("</script>");
            out.close();
            return null;
        } else {
            String newCode = "";
            for (int i = 0; i < 12; i++) {
                newCode += (char) ((Math.random() * 26) + 97);
            }

            code = newCode;

            // 인증코드 메일 발송
            sendMailOauth(user, code,"findpw");

            out.println("<script>");
            out.print("alert('이메일로 인증코드를 발송하였습니다.');");
            out.println("location.href='../user/checkId';");
            out.println("</script>");
            out.close();

            return uid;
        }
    }


    // 비밀번호 찾기
    @Override
    public String findpw(HttpServletResponse response, User user, String username, String email) throws Exception {
        response.setContentType("text/html; charset=utf-8");
        PrintWriter out = response.getWriter();

        user.setUsername(username);
        user.setEmail(email);

        // 아이디가 없으면
        if(userRepository.findByUsername(user.getUsername()) == null) {
            out.println("<script>");
            out.print("alert('일치하는 아이디가 없습니다.');");
            out.println("history.go(-1);");
            out.println("</script>");
            out.close();
            return null;
        }
        // 가입에 사용한 이메일이 아니면
        else if(!userRepository.findByUsername(user.getUsername()).getEmail().equals(user.getEmail())) {
            out.println("<script>");
            out.print("alert('이메일이 일치하지 않습니다.');");
            out.println("history.go(-1);");
            out.println("</script>");
            out.close();
            return null;

        } else {

            usernameInput = user.getUsername();
            emailInput = user.getEmail();

            String newCode = "";
            for (int i = 0; i < 12; i++) {
                newCode += (char) ((Math.random() * 26) + 97);
            }

            code = newCode;

            // 인증코드 메일 발송
            sendMailOauth(user, code,"findpw");

            out.println("<script>");
            out.print("alert('이메일로 인증코드를 발송하였습니다.');");
            out.println("location.href='../user/checkpw';");
            out.println("</script>");
            out.close();

            return "인증코드 발송";

        }
    }


    @Override
    public Long findByuid(String username) {

        return userRepository.findByuid(username);
    }

    // 이 메서드는 실제 데이터베이스에서 사용자의 관광지 ID를 조회하는 로직을 담게 됩니다.
    public Integer getTourIdByUserId(Integer userId) {
        // 여기에 데이터베이스에서 사용자의 관광지 ID를 조회하는 로직을 구현합니다.
        // 예를 들어, userId를 이용해 관련 정보를 데이터베이스에서 조회하고 그에 맞는 tourId를 반환하는 코드를 작성합니다.
        // 임시로 1을 반환하는 코드를 작성하겠습니다.
        return 1;
    }

    public void likeTour(Long userId, Long tourId) {
        // 좋아요 추가 로직

        userRepository.likesave(userId, tourId);
    }

    public void unlikeTour(Long uid, Long id) {
        // 좋아요 취소 로직
        System.out.println(uid);
        System.out.println(id);

        userRepository.deleteById(uid,id);
    }


    // 이메일 발송
    @Override
    public void sendMailOauth(User user, String code, String div) throws Exception {
        // Mail Server 설정
        String charSet = "utf-8";
        String hostSMTP = host;
        String hostSMTPid = username;
        String hostSMTPpwd = password;

        // 보내는 사람 EMail, 제목, 내용
        String fromEmail = username;
        String fromName = "핫도리";
        String subject = "";
        String msg = "";

        if(div.equals("findpw")) {
            subject = "핫도리 이메일 인증코드";
            msg += "<div align='center' style='border:1px solid black; font-family:verdana'>";
            msg += "<h3 style='color: blue;'>";
            msg += "핫도리 이메일 인증코드</h3>";
            msg += "<p>인증코드는 " + code + "입니다.<br> 해당 코드를 확인란에 입력해주세요.<br><br></p>";
            msg += "</div>";
        }
        // 받는 사람 E-Mail 주소
        String mail = user.getEmail();
        try {
            HtmlEmail email = new HtmlEmail();
            email.setDebug(true);
            email.setCharset(charSet);
            email.setSSL(true);
            email.setHostName(hostSMTP);
            email.setSmtpPort(port);


            email.setAuthentication(hostSMTPid, hostSMTPpwd);
            email.setTLS(true);
            email.addTo(mail, charSet);
            email.setFrom(fromEmail, fromName, charSet);
            email.setSubject(subject);
            email.setHtmlMsg(msg);
            email.send();
        } catch (Exception e) {
            System.out.println("메일 발송 실패 : " + e);
        }
    }

    // 인증코드 확인
    @Override
    public String checkId(HttpServletResponse response, User user, String code) throws Exception {
        response.setContentType("text/html; charset=utf-8");
        PrintWriter out = response.getWriter();

        if (!this.code.equals(code)) {
            out.println("<script>");
            out.print("alert('인증코드가 일치하지 않습니다.');");
            out.println("history.go(-1);");
            out.println("</script>");
            out.close();
            return null;

        } else {
            out.println("<script>");
            out.print("alert('확인되었습니다.');");
            out.println("location.href='../user/findIdResult';");
            out.println("</script>");
            out.close();

            return "인증코드 확인";
        }
    }

    @Override
    public String checkpw(HttpServletResponse response, User user, String code) throws Exception {
        response.setContentType("text/html; charset=utf-8");
        PrintWriter out = response.getWriter();

        if (!this.code.equals(code)) {
            out.println("<script>");
            out.print("alert('인증코드가 일치하지 않습니다.');");
            out.println("history.go(-1);");
            out.println("</script>");
            out.close();
            return null;

        } else {
            out.println("<script>");
            out.print("alert('확인되었습니다. 비밀번호를 변경해주세요.');");
            out.println("location.href='../user/setpw';");
            out.println("</script>");
            out.close();

            return "인증코드 확인";
        }
    }

    // 비밀번호 재설정
    @Override
    public String setpw(HttpServletResponse response, User user, String pw, String pw2) throws Exception {
        response.setContentType("text/html; charset=utf-8");
        PrintWriter out = response.getWriter();

        if (!pw.equals(pw2)) {
            out.println("<script>");
            out.print("alert('새 비밀번호와 새 비밀번호 확인 입력 값은 같아야 합니다.');");
            out.println("history.go(-1);");
            out.println("</script>");
            out.close();
            return null;

        } else {
            userRepository.updatepw(usernameInput, passwordEncoder.encode(pw));
            out.println("<script>");
            out.print("alert('변경이 완료되었습니다.');");
            out.println("location.href='../user/login';");
            out.println("</script>");
            out.close();

            return "비밀번호 변경";
        }

    }



    public void likeCamping(Long uid, Long campingid){

        userRepository.camlikesave(uid,campingid);
    }


    public void unlikeCamping(Long uid, Long campingid){

        userRepository.deleteBycamId(uid,campingid);
    }
}




