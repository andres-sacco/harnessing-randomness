package com.twa.reservations.common;

import net.datafaker.Faker;
import net.datafaker.transformations.CsvTransformer;
import net.datafaker.transformations.JsonTransformer;
import net.datafaker.transformations.Schema;

import org.json.JSONArray;
import org.junit.jupiter.api.Test;

import static net.datafaker.transformations.Field.field;
import static org.assertj.core.api.Assertions.assertThatCode;

class JsonSchemaTest {

    private final Faker faker = new Faker();

    @Test
    void should_generate_a_json_with_address() {
        JsonTransformer<Object> jsonTransformer = JsonTransformer.builder().build();
        String jsonOutput = jsonTransformer.generate(retrieveCompositeSchema(), 5);

        System.out.println(jsonOutput);
        assertThatCode(() -> new JSONArray(jsonOutput)).doesNotThrowAnyException();
    }

    @Test
    void should_generate_csv_with_address() {
        CsvTransformer<Object> csvTransformer = CsvTransformer.builder().build();
        String csvOutput = csvTransformer.generate(retrieveCompositeSchema(), 5);

        System.out.println(csvOutput);
    }

    private Schema<Object, ?> retrieveCompositeSchema() {
        return Schema.of(
                field("name", () -> faker.name().nameWithMiddle()),
                field("street", () -> faker.address().streetAddress()),
                field("secondaryAddress", () -> faker.address().secondaryAddress()),
                field("postcode", () -> faker.address().postcode()),
                field("city", () -> faker.address().cityName()),
                field("state", () -> faker.address().state()),
                field("country", () -> faker.address().country())
        );
    }
}