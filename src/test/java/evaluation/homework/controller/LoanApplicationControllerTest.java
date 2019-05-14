package evaluation.homework.controller;

import evaluation.homework.domain.Loan;
import evaluation.homework.model.LoanApplicationModel;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigDecimal;
import java.net.URL;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Created by recruz.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LoanApplicationControllerTest {

    @LocalServerPort
    private int port;

    private URL baseURL;

    @Autowired
    private TestRestTemplate template;

    @Before
    public void setUp() throws Exception {
        this.baseURL = new URL("http://localhost:" + port + "/");
    }

    @Test
    public void sendLoanApplicationTest() throws Exception {
        LoanApplicationModel loanApplicationModel =
                new LoanApplicationModel("Pepe", "Martinez", new BigDecimal(12), 30L);
        URL url = new URL(baseURL, "loan");
        RequestEntity<LoanApplicationModel> request = RequestEntity.post(url.toURI())
                .contentType(MediaType.APPLICATION_JSON).body(loanApplicationModel);
        ResponseEntity<String> response = template.exchange(request, String.class);
        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
        assertThat(response.getBody(), equalTo("rejected"));
    }

    @Test
    public void getAllLoanApplications() throws Exception {
        URL url = new URL(baseURL, "loans");
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url.toString());
        HttpEntity<?> entity = new HttpEntity<>(headers);

        ResponseEntity<List<Loan>> response = template.exchange(builder.toUriString(), HttpMethod.GET, entity, new ParameterizedTypeReference<List<Loan>>(){});
        List<Loan> result = response.getBody();
        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
        assertThat(result.size(), equalTo(3));
    }

}