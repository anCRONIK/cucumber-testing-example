package net.ancronik.samples.cucumber.acceptance.steps;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import net.ancronik.samples.cucumber.acceptance.AbstractSteps;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ActuatorHealthCheckSteps extends AbstractSteps {

    ObjectMapper mapper = new ObjectMapper();

    @And("^the client receives dependencies health statuses$")
    public void checkReceivedMeasurementUnits(DataTable table) throws JsonProcessingException {
        String body = latestResponse.getBody();

        Map<String, Object> map = mapper.readValue(body, Map.class);

        table.entries().forEach(e -> {
            Map c = (Map) map.get("components");
            Map h = (Map) c.get(e.get("dependency_name"));

            assertEquals(e.get("status"), h.get("status"));
        });
    }
}
