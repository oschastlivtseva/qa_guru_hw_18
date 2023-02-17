package guru.qa.tests;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class FirstTest {

    @Test
    public void createUser() {
        String name = "Agnes";
        String job = "director";
        String data = "{ \"name\": \"" + name + "\", \"job\": \"" + job + "\" }";

        given()
                .log().uri()
                .when()
                .contentType(ContentType.JSON)
                .body(data)
                .post("https://reqres.in/api/users")
                .then()
                .log().status()
                .log().body()
                .statusCode(201)
                .body("name", is(name))
                .body("job", is(job));
    }

    @Test
    public void getSingleUser() {
        int userId = 6;
        String firstName = "Tracey";
        String lastName = "Ramos";
        String email = "tracey.ramos@reqres.in";

        given()
                .log().uri()
                .when()
                .get("https://reqres.in/api/users/" + userId)
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("data.first_name", is(firstName))
                .body("data.last_name", is(lastName))
                .body("data.email", is(email));
    }

    @Test
    public void singleUserIsNotFound() {
        int userId = 800;

        given()
                .log().uri()
                .when()
                .get("https://reqres.in/api/users/" + userId)
                .then()
                .log().status()
                .log().body()
                .statusCode(404)
                .body(is("{}"));
    }

    @Test
    public void getListOfResources() {
        String firstColourName = "true red";
        String firstPantoneValue = "19-1664";
        String firstColourCode = "#BF1932";

        String secondColourName = "blue turquoise";
        String secondPantoneValue = "15-5217";
        String secondColourCode = "#53B0AE";

        given()
                .log().uri()
                .when()
                .get("https://reqres.in/api/unknown")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("data[2].name", is(firstColourName))
                .body("data[2].pantone_value", is(firstPantoneValue))
                .body("data[2].color", is(firstColourCode))
                .body("data[5].name", is(secondColourName))
                .body("data[5].pantone_value", is(secondPantoneValue))
                .body("data[5].color", is(secondColourCode));
    }

    @Test
    public void getSingleResource() {
        int resourceId = 3;
        String colourName = "true red";
        String pantoneValue = "19-1664";
        String colourCode = "#BF1932";

        given()
                .log().uri()
                .when()
                .get("https://reqres.in/api/unknown/" + resourceId)
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("data.name", is(colourName))
                .body("data.pantone_value", is(pantoneValue))
                .body("data.color", is(colourCode));
    }
}
