package net.ancronik.samples.cucumber.acceptance.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.SneakyThrows;
import net.ancronik.samples.cucumber.acceptance.Context;
import net.ancronik.samples.cucumber.acceptance.HeaderSettingRequestCallback;
import net.ancronik.samples.cucumber.acceptance.ResponseResultErrorHandler;
import net.ancronik.samples.cucumber.acceptance.ResponseResults;
import net.ancronik.samples.cucumber.data.model.Note;
import net.ancronik.samples.cucumber.web.dto.NoteDto;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NotesControllerSteps extends net.ancronik.samples.cucumber.acceptance.AbstractSteps {

    @When("^user is fetching all notes")
    public void userFetchingAllNotes() {
        makeApiCall("/api/notes", HttpMethod.GET.name(), null);
    }

    @SneakyThrows
    @Then("^fetched notes count is (\\d+)$")
    public void checkFetchedNotesCount(int count) {
        //   Page<NoteDto> data =
        HttpStatus currentStatusCode = latestResponse.getTheResponse().getStatusCode();
        assertEquals(200, currentStatusCode.value());
    }

    @When("^user fetches note with selected id$")
    public void userFetchesNoteById() {
        makeApiCall("/api/notes/" + scenarioContext.getContext(Context.NOTE_ID), HttpMethod.GET.name(), null);
    }

    @And("^received data matches selected note$")
    public void checkResponseAgainstTheSelectedNote() throws JSONException {
        Note selectedNote = (Note) scenarioContext.getContext(Context.NOTE);

        JSONObject jsonObject = new JSONObject(latestResponse.getBody());

        assertEquals(jsonObject.getLong("id"), selectedNote.getId());
        assertEquals(jsonObject.getString("text"), selectedNote.getText());
        assertEquals(jsonObject.getBoolean("edited"), selectedNote.isEdited());
    }

}
