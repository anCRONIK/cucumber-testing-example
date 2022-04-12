package net.ancronik.samples.cucumber.acceptance.steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.SneakyThrows;
import net.ancronik.samples.cucumber.acceptance.HeaderSettingRequestCallback;
import net.ancronik.samples.cucumber.acceptance.ResponseResultErrorHandler;
import net.ancronik.samples.cucumber.acceptance.ResponseResults;
import net.ancronik.samples.cucumber.web.dto.NoteDto;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NotesControllerSteps extends net.ancronik.samples.cucumber.acceptance.AbstractSteps {

    @When("^user is fetching all notes")
    public void userFetchingAllNotes() {
        final HeaderSettingRequestCallback requestCallback = new HeaderSettingRequestCallback(generateDefaultHttpHeaders());
        final ResponseResultErrorHandler errorHandler = new ResponseResultErrorHandler();

        testRestTemplate.getRestTemplate().setErrorHandler(errorHandler);
        latestResponse = testRestTemplate.execute("/api/notes", HttpMethod.GET, requestCallback, response -> {
            if (errorHandler.hadError) {
                return (errorHandler.getResults());
            } else {
                return (new ResponseResults(response));
            }
        });
    }

    @SneakyThrows
    @Then("^fetched notes count is (\\d+)$")
    public void checkFetchedNotesCount(int count){
     //   Page<NoteDto> data =
        HttpStatus currentStatusCode = latestResponse.getTheResponse().getStatusCode();
        assertEquals(200, currentStatusCode.value());
    }

}
