package net.ancronik.samples.cucumber.acceptance.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.ancronik.samples.cucumber.acceptance.*;
import net.ancronik.samples.cucumber.data.model.Author;
import net.ancronik.samples.cucumber.data.model.Note;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GenericSteps extends AbstractSteps {

    @When("^the client calls (.+) with http method (.+)$")
    public void theClientCallsApi(String path, String method) {
       makeApiCall(path, method, null);
    }

    @Then("^the client receives status code of (\\d+)$")
    public void checkTheStatusCode(int statusCode) throws Throwable {
        HttpStatus currentStatusCode = latestResponse.getTheResponse().getStatusCode();
        assertEquals(statusCode, currentStatusCode.value());
    }

    @Given("^there are (\\d+) random notes in database$")
    public void insertRandomNotes(int count) {
       // noteRepository.deleteAll();
        //FIXME not working as expected, cascade problem?
        Author author = authorRepository.findByUsername(TEST_USER).orElseThrow();
        List<Note> notes = DataGenerator.generateRandomNotes(count, author);

        noteRepository.saveAll(notes);
    }

    @Given("^there are notes in database$")
    public void checkThereAreNotesInDbAndGenerateIfNot() {
        if (noteRepository.count() < 1) {
            insertRandomNotes(2);
        }
    }

    @And("^random note id is selected$")
    public void selectRandomNote(){
        Note note = noteRepository.findAll().iterator().next();
        scenarioContext.setContext(Context.NOTE_ID, note.getId());
        scenarioContext.setContext(Context.NOTE, note);
    }

}
