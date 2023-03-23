package it.cparisi.pakkiotto.extension.it;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class PakkiottoExtensionResourceTest {

    @Test
    public void testHelloEndpoint() {
        given()
                .when().get("/pakkiotto-extension")
                .then()
                .statusCode(200)
                .body(is("Hello pakkiotto-extension"));
    }
}
