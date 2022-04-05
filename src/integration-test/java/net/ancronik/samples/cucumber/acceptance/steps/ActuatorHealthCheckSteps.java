package net.ancronik.samples.cucumber.acceptance.steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.gherkin.internal.com.eclipsesource.json.JsonObject;
import io.cucumber.java.en.And;
import net.ancronik.samples.cucumber.acceptance.AbstractSteps;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ActuatorHealthCheckSteps extends AbstractSteps {

    @And("^the client receives dependencies health statuses$")
    public void checkReceivedMeasurementUnits(DataTable table) {
        String body = latestResponse.getBody();

        JsonObject jsonData = JsonObject.readFrom(body);

        table.entries().forEach(e -> {

            JsonObject c = jsonData.get("components").asObject();
            JsonObject h = c.get(e.get("dependency_name")).asObject();

            assertEquals(e.get("status"), h.getString("status", null));
        });
    }
}
