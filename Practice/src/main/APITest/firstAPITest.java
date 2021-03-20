package main.APITest;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class firstAPITest {

    private static final String spartanBaseUrl = "http://3.80.35.99:8000";

    private static Response response;



    /**
     * Checks spartan Status Code
     */
    public static void viewSpartanCODE() {

        System.out.println("response.statusCode() = " + response.statusCode());

    }

    /**
     * Shows body of request
     */
    public static void viewBody() {

        response.getBody()
                .prettyPrint();
    }

    public static void viewSpartan(int id) {

        response = RestAssured.get(spartanBaseUrl + "/api/spartans/" + id + "");
        viewSpartanCODE();
        viewBody();

    }

    @Test
    public void test() {

        viewSpartan(2);
        assertEquals(response.statusCode(), 200);

    }

    /**
     * Given accept type is jason
     * When user sends a get request to spartanAllURL
     * Then response stus code is 200
     * And response body should be json format
     */
    @Test
    public void test1() {

        Response response = RestAssured.given().accept(ContentType.JSON)
                .when().get(spartanBaseUrl + "/api/spartans");

        assertEquals(response.statusCode(), 200);
        assertEquals(response.contentType(), "application/json");

    }

    @Test
    public void pathTest() {
        viewSpartan(4); //===============

        Response response = RestAssured.given().accept(ContentType.JSON)
                .and().pathParam("id", 4)
                .when().get(spartanBaseUrl + "/api/spartans/{id}");

        assertEquals("Correct Code", response.statusCode(), 200);
        assertEquals("Correct Content", response.contentType(), "application/json");
        assertTrue("NAME IS PAIGE", response.body().asString().contains("Paige"));

        response.body().prettyPrint();

    }

    /**
     * Given accept type is json
     * And Id parameter value is 500
     * When user sends GET request to /api/spartans/{id}
     * Then response status code should be 404: not found
     * And response content-type: application/json
     * And  "Spartan Not Found" message should be in response payload
     */
    @Test
    public void negativePathTest() {

        Response id = RestAssured.given().contentType(ContentType.JSON)
                .pathParam("id", 500)
                .when().get(spartanBaseUrl + "/api/spartans/{id}");

        id.body().prettyPrint();

        assertEquals(id.statusCode(), 404);
        assertEquals(id.contentType(), "application/json");
        assertTrue(id.body().asString().contains("Not Found"));

    }

    @Test
    public void queryParameter() {
        RestAssured.baseURI = spartanBaseUrl;
        Response response = RestAssured.given().accept(ContentType.JSON)
                .and().queryParam("gender", "Female")
                .and().queryParam("nameContains", "Evel")
                .when().get("/api/spartans/search");

        assertEquals(response.statusCode(), 200);
        assertEquals(response.contentType(), "application/json");
        assertTrue(response.body().asString().contains("Eveleen"));

        response.prettyPrint();

    }

    @Test
    public void queryParameterMap() {

        Map<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("gender", "Female");
        paramsMap.put("nameContains", "Evel");

        Response response = RestAssured
                .given().accept(ContentType.JSON)
                .and().queryParams(paramsMap)
                .when().get(spartanBaseUrl + "/api/spartans/search")
                .thenReturn();

        response.prettyPrint();

    }

    @Test
    public void pathMethod() {

        RestAssured.baseURI = spartanBaseUrl;
        Response response = RestAssured.given().accept(ContentType.JSON)
                .pathParam("id", 10)
                .when().get(spartanBaseUrl + "/api/spartans/{id}");


        System.out.println("id.path(\"name\").toString() = " + response.path("name").toString());

        assertEquals(response.statusCode(), 200);
        assertEquals(response.contentType(), "application/json");

        var id = response.path("id");
        var name = response.path("name");
        var gender = response.path("gender");
        var phone = response.path("phone");

        System.out.println("id = " + id);
        System.out.println("name = " + name);
        System.out.println("gender = " + gender);
        System.out.println("phone = " + phone);

        assertEquals(name, "Lorenza");
        assertEquals(id, 10);
        assertEquals(gender, "Female");
        assertEquals(phone, 3312820936L);
    }

    @Test
    public void pathMethodWhenMultipleDuplicateJSONKeys() {
        RestAssured.baseURI = spartanBaseUrl;

        response = RestAssured.get("/api/spartans");

        int firstID = response.path("id[0]");
        var firstName = response.path("name[0]");
        var lastFirstName = response.path("name[-1]");


        System.out.println("firstID = " + firstID);

        System.out.println("firstName = " + firstName);

        System.out.println("lastFirstName = " + lastFirstName);

        //EXTRACT ALL FIRSTNAMES  ADD TO ARRAY

        List<String> names = response.path("name");

        System.out.println(names);
        System.out.println(names.size());

        List<Object> phoneNumbers = response.path("phone");

        for (Object phoneNumber : phoneNumbers) {
            System.out.println("\n" + phoneNumber);
        }

    }

    @Test
    public void jsonPath() {
        RestAssured.baseURI = spartanBaseUrl;

        response = RestAssured.given().accept(ContentType.JSON)
                .pathParam("id", 11)
                .when().get("/api/spartans/{id}");

        assertEquals(response.statusCode(), 200);


        var jsonPath = response.jsonPath();

        var idJson = jsonPath.getInt("id");
        var name = jsonPath.getString("name");
        var gender = jsonPath.getString("gender");
        var phone = jsonPath.getLong("phone");

        System.out.println("idJson = " + idJson);
        System.out.println("name = " + name);
        System.out.println("gender = " + gender);
        System.out.println("phone = " + phone);


    }
    /**
     *
     *  Given accept type is Json
     *  And path param id is 15
     *  When user sends a get request to spartans/{id}
     *  Then status code is 200
     *  And content type is Json
     *  And json data has following
     *
     */
    @Test
    public void chaining() {

        RestAssured.baseURI = spartanBaseUrl;

        RestAssured.given().accept(ContentType.JSON)
                .pathParam("id", 15)
                .when().get("/api/spartans/{id}")
                //response
                .then().statusCode(200)
                .and().assertThat().contentType("application/json");


    }

    @Test
    public void HamCrest() {

        RestAssured.baseURI = spartanBaseUrl;
        RestAssured.given().accept(ContentType.JSON)
                .pathParam("id", 15)
                .when().get("/api/spartans/{id}")
                .then().assertThat().statusCode(200)
                .and().assertThat().contentType("application/json")
                .and().assertThat()
                //sHamCrest Matchers<>
                .body("id", Matchers.equalTo(15),
                        "name", Matchers.equalTo("Meta"),
                        "gender", Matchers.equalTo("Female"),
                        "phone", Matchers.equalTo(1938695106));


    }

    /**
     * PreReq
     * -String contains("value")----> boolean verification
     * -Path()
     * -JsonPath
     * -HamCrest Matchers(Chaining)
     * <p>
     * -JSON response  -----> Java dataStructures/Collections
     * {
     * "id": 15,
     * "name": "Meta",
     * "gender": "Female",
     * "phone": 1938695106
     * }
     * Mao<String,Object>
     * JSON --> Java Collections
     * De-Serialization
     * Json Parser
     * GSON
     * Jackson 1.X
     * Jackson 2.X
     */
    @Test
    public void deSerialization() {

        baseURI = spartanBaseUrl;
        response = given().accept(ContentType.JSON)
                    .pathParam("id", 11)
                       .and().get("/api/spartans/{id}");


        //Convert Json response to java Collections(Map)
        Map<String,Object> spartanMap = response.body().as(Map.class);


        System.out.println(spartanMap.get("phone"));

        assertEquals(spartanMap.get("name"), "Nona");
    }

    @Test
    public void deSerialization2(){

        response = given().accept(ContentType.JSON)
                .when().get(spartanBaseUrl+"/api/spartans");

        //Convert Entire json Body to List of maps

        List<Map<String,Object>> listOfSpartans = response.body().as(List.class);

        //print all data of first spartan

        System.out.println(listOfSpartans.get(0));
        System.out.println(listOfSpartans.get(0).get("name"));
        System.out.println(listOfSpartans.get(0).get("phone"));
        int count = 1;
        for (Map<String, Object> map : listOfSpartans ) {
            System.out.println(count+" : "+map);
            count++;
        }

    }


}


class spartanTestPOJODeSerialization {

    
}
