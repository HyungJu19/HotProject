/**
 * #민호
 */

package com.lec.spring.service;

import com.lec.spring.domain.role;
import com.lec.spring.domain.User;
import com.lec.spring.repository.AuthorityRepository;
import com.lec.spring.repository.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.mail.HtmlEmail;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.PrintWriter;
import java.util.List;
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    private UserRepository userRepository;

    private AuthorityRepository authorityRepository;

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
    public String findId(HttpServletResponse response, String email) throws Exception {
        response.setContentType("text/html; charset=utf-8");
        PrintWriter out = response.getWriter();
        String uid = userRepository.findId(email);

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
            return uid;
        }
    }

    // 이메일 발송
    @Override
    public void sendMail(User user, String div) throws Exception {
        // Mail Server 설정
        String charSet = "utf-8";
        String hostSMTP = "smtp.naver.com";
        String hostSMTPid = "team_hot@naver.com";
        String hostSMTPpwd = "kdt907HOT!";

        // 보내는 사람 EMail, 제목, 내용
        String fromEmail = "team_hot@naver.com";
        String fromName = "핫도리";
        String subject = "";
        String msg = "";

        if(div.equals("findpw")) {
            subject = "핫도리 임시 비밀번호입니다.";
            msg += "<div align='center' style='border:1px solid black; font-family:verdana'>";
            msg += "<h3 style='color: blue;'>";
            msg += user.getUsername() + "님의 임시 비밀번호입니다.</h3>";
            msg += "<p>임시 비밀번호 : ";
            msg += user.getPassword() + "</p></div>";
        }
        // 받는 사람 E-Mail 주소
        String mail = user.getEmail();
        try {
            HtmlEmail email = new HtmlEmail();
            email.setDebug(true);
            email.setCharset(charSet);
            email.setSSL(true);
            email.setHostName(hostSMTP);
            email.setSmtpPort(587);

            email.setAuthentication(hostSMTPid, hostSMTPpwd);
            email.setTLS(true);
            email.addTo(mail, charSet);
            email.setFrom(fromEmail, fromName, charSet);
            email.setSubject(subject);
            email.setHtmlMsg(msg);
            email.send();
        } catch (Exception e) {
            System.out.println("메일발송 실패 : " + e);
        }
    }

    // 비밀번호 찾기
    @Override
    public void findpw(HttpServletResponse response, User user) throws Exception {
        response.setContentType("text/html; charset=utf-8");
        PrintWriter out = response.getWriter();

        // 아이디가 없으면
        if(userRepository.findByUsername(user.getUsername()) == null) {
            out.print("일치하는 아이디가 없습니다.");
            out.close();
        }
        // 가입에 사용한 이메일이 아니면
        else if(!userRepository.findByUsername(user.getUsername()).getEmail().equals(user.getEmail())) {
//        else if(userRepository.findByEmail(user.getEmail()) == null) {
            out.print("잘못된 이메일입니다.");
            out.close();
        } else {
            // 임시 비밀번호 생성
            String pw = "";
            for (int i = 0; i < 12; i++) {
                pw += (char) ((Math.random() * 26) + 97);
            }

            // 비밀번호 변경
            user.setPassword(pw);
            userRepository.updatepw(user);
            // 비밀번호 변경 메일 발송
            sendMail(user, "findpw");

            user.setPassword(passwordEncoder.encode(pw));
            userRepository.updatepw(user);

            out.print("이메일로 임시 비밀번호를 발송하였습니다.");
            out.close();

        }
    }


}
