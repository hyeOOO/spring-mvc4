package hello.login.domain.session;

import hello.login.domain.member.Member;
import hello.login.web.session.SessionManager;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import static org.assertj.core.api.Assertions.assertThat;

class SessionManagerTest {

    SessionManager sessionManager = new SessionManager();

    @Test
    void sessionTest() {
        // 응답 시 세션 생성
        MockHttpServletResponse response = new MockHttpServletResponse(); //톰캣이나 이런거 없이도 요청, 응답에 대한 테스트를 할 수 있게 해줌
        Member member = new Member();
        sessionManager.createSession(member, response);

        //요청에 응답 쿠키 저장(웹 브라우저가 요청을 보냈다고 가정)
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setCookies(response.getCookies());

        // 세션 조회
        Object result = sessionManager.getSession(request);
        assertThat(result).isEqualTo(member);

        // 세션 만료
        sessionManager.expire(request);
        Object expired = sessionManager.getSession(request);
        assertThat(expired).isNull();
    }

    @Test
    void getSession() {
    }

    @Test
    void expire() {
    }

    @Test
    void findCookie() {
    }
}