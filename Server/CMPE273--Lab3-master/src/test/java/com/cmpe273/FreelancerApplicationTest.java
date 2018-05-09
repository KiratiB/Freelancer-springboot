package com.cmpe273;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.assertEquals;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;
import com.cmpe273.model.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FreelancerApplicationTest {

	public static RestTemplate restTemplateObj;

	@Bean
	public static RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@BeforeClass
    public static void prepare(){
        restTemplateObj = restTemplate();
    }

	@Test
	public void doLogin() {
	    User user = new User();
	    user.setEmail("sdfsdf@sd.com");
	    user.setPassword("sdf43342");
        ResponseEntity<String> res = restTemplateObj.postForEntity("http://localhost:8080/user/login", user, String.class);
        assertEquals(200, res.getStatusCodeValue());
	}

	@Test
    public void fetchAllUsers() {
        ResponseEntity<String> res = restTemplateObj.getForEntity("http://localhost:8080/user/all",  String.class);
        assertEquals(200, res.getStatusCodeValue());
    }

    @Test
    public void fetchAllProjects() {
        ResponseEntity<String> res = restTemplateObj.getForEntity("http://localhost:8080/project/all",  String.class);
        assertEquals(200, res.getStatusCodeValue());
    }


    @Test
    public void doSignup() {
        User user = new User();
        user.setEmail("sdfsdf@sd.com");
        user.setPassword("sdf43342");
        user.setUsername("abc");
        ResponseEntity<String> res = restTemplateObj.postForEntity("http://localhost:8080/project/signup",user,  String.class);
        assertEquals(200, res.getStatusCodeValue());
    }

    @Test
    public void fetchUserById() {
        User user = new User();
        user.setUserId(1);
        ResponseEntity<String> res = restTemplateObj.postForEntity("http://localhost:8080/project/fetchByUserId",user,  String.class);
        assertEquals(200, res.getStatusCodeValue());
    }
}
