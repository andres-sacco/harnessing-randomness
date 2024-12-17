package com.twa.reservations.common;

import net.datafaker.Faker;
import org.junit.jupiter.api.Test;

class DataFakerExamples {

    private static final Faker faker = new Faker();

    @Test
    void should_print_all_values() {
        // Call methods to generate and display data
        printNameRelatedData();
        printAddressRelatedData();
        printCompanyRelatedData();
        printDateRelatedData();
        printInternetRelatedData();
        printPhoneRelatedData();
        printAviationRelatedData();
        printEntertainmentRelatedData();
    }

    // 1. Name-related Faker Data
    public static void printNameRelatedData() {
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String fullName = faker.name().fullName();
        String prefix = faker.name().prefix();
        String suffix = faker.name().suffix();

        System.out.println("Name-related Data:");
        System.out.println("First Name: " + firstName);
        System.out.println("Last Name: " + lastName);
        System.out.println("Full Name: " + fullName);
        System.out.println("Prefix: " + prefix);
        System.out.println("Suffix: " + suffix);
        System.out.println();
    }

    // 2. Address-related Faker Data
    public static void printAddressRelatedData() {
        String streetAddress = faker.address().streetAddress();
        String city = faker.address().city();
        String cityName = faker.address().cityName();
        String postalCode = faker.address().zipCode();
        String country = faker.address().country();
        String state = faker.address().state();

        System.out.println("Address-related Data:");
        System.out.println("Street Address: " + streetAddress);
        System.out.println("City: " + city);
        System.out.println("City Name: " + cityName);
        System.out.println("Postal Code: " + postalCode);
        System.out.println("Country: " + country);
        System.out.println("State: " + state);
        System.out.println();
    }

    // 3. Company-related Faker Data
    public static void printCompanyRelatedData() {
        String companyName = faker.company().name();
        String industry = faker.company().industry();
        String jobTitle = faker.job().title();
        String companySuffix = faker.company().suffix();

        System.out.println("Company-related Data:");
        System.out.println("Company Name: " + companyName);
        System.out.println("Industry: " + industry);
        System.out.println("Job Title: " + jobTitle);
        System.out.println("Company Suffix: " + companySuffix);
        System.out.println();
    }

    // 4. Date-related Faker Data
    public static void printDateRelatedData() {
        String dateOfBirth = faker.date().birthday().toString();
        String futureDate = faker.date().future(10, java.util.concurrent.TimeUnit.DAYS).toString();
        String pastDate = faker.date().past(10, java.util.concurrent.TimeUnit.DAYS).toString();

        System.out.println("Date-related Data:");
        System.out.println("Date of Birth: " + dateOfBirth);
        System.out.println("Future Date: " + futureDate);
        System.out.println("Past Date: " + pastDate);
        System.out.println();
    }

    // 5. Internet-related Faker Data
    public static void printInternetRelatedData() {
        String email = faker.internet().emailAddress();
        String url = faker.internet().url();
        String username = faker.internet().slug();

        System.out.println("Internet-related Data:");
        System.out.println("Email: " + email);
        System.out.println("URL: " + url);
        System.out.println("Username: " + username);
        System.out.println();
    }

    // 6. Phone-related Faker Data
    public static void printPhoneRelatedData() {
        String phoneNumber = faker.phoneNumber().phoneNumber();
        String extension = faker.phoneNumber().extension();

        System.out.println("Phone-related Data:");
        System.out.println("Phone Number: " + phoneNumber);
        System.out.println("Extension: " + extension);
        System.out.println();
    }

    // 7. Aviation-related Faker Data
    public static void printAviationRelatedData() {
        String airline = faker.aviation().airline();
        String airport = faker.aviation().airport();

        System.out.println("Aviation-related Data:");
        System.out.println("Airline: " + airline);
        System.out.println("Airport Code: " + airport);
        System.out.println();
    }

    // 8. Entertainment-related Faker Data
    public static void printEntertainmentRelatedData() {
        String artist = faker.artist().name();
        String character = faker.superhero().name();
        String movieGenre = faker.book().genre();
        String movieQuote = faker.harryPotter().quote();

        System.out.println("Entertainment-related Data:");
        System.out.println("Artist: " + artist);
        System.out.println("Character: " + character);
        System.out.println("Movie Genre: " + movieGenre);
        System.out.println("Movie Quote: " + movieQuote);
        System.out.println();
    }

}
