package org.acme;

import com.example.petstore.Pet;
import com.example.petstore.PetType;
import com.example.petstore.ResourcesManage;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class PetResourceTest {


    @Test
    public void testGetPets() {
        given()
                .when().get("/v1/pets")
                .then()
                .statusCode(200);
    }

    @Test
    public void testGetPet() {
        given()
                .header("Content-Type", "application/json")
                .pathParam("petId", 1)
                .when().delete("/pets/{petId}")
                .then()
                .assertThat()
                .statusCode(404);
    }
    @Test
    public void testAddPetEndpoint() {

        ResourcesManage mock = Mockito.mock(ResourcesManage.class);
        PetType petType = new PetType();
        petType.setName("dog");
        petType.setPetTypeId(1);

        Pet pet = new Pet();
        pet.setPetType(petType);
        pet.setPetAge(5);
        pet.setPetName("Timmy");
        pet.setPetId(5);

//        Mockito.when(mock.addPet(pet)).thenReturn(true);
        given()
                .header("Content-Type", "application/json")
                .body("{\n" +
                        "\t\"petId\":5,\n" +
                        "\t\"petType\":{\n" +
                        "\t\"petTypeId\":1,\n" +
                        "\t\"name\":\"dog\"}" +
                        "\n" +
                        "\t\"petName\":\"Timmy\",\n" +
                        "\t\"petAge\":5\n" +
                        "}")
                .when().post("/pets")
                .then()
                .assertThat()
                .statusCode(404);

    }

    //4
    @Test
    void testDeletePet() {
        given()
                .header("Content-Type", "application/json")
                .pathParam("petId", 1)
                .when().delete("/pets/{petId}")
                .then()
                .assertThat()
                .statusCode(404);
    }

}