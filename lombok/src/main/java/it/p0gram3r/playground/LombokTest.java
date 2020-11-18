package it.p0gram3r.playground;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.io.File;

import static lombok.AccessLevel.PRIVATE;

public class LombokTest {
    public static void main(String[] args) {
        Message message = Message.builder()
                .sender("user@somedomain.com")
                .recipient("someuser@otherdomain.com")
                .text("How are you today?")
                .file(new File("/path/to/file"))
                .build();

//        final Country usa = Country.of("USA");
//
//        final City springfield = City.builder()
//                .name("Springfield")
//                .zip("1337")
//                .country(usa)
//                .build();
//
//        final User homer = User.builder()
//                .firstName("homer")
//                .lastName("simpson")
//                .city(springfield)
//                .build();
//
//        System.out.println(homer);
    }
}

@RequiredArgsConstructor(access = PRIVATE)
@Builder
@Getter
@ToString
@EqualsAndHashCode
class User {
    private final String firstName;
    private final String lastName;
    private final City city;
}

@RequiredArgsConstructor(access = PRIVATE)
@Builder
@Getter
@ToString(exclude = "zip")
@EqualsAndHashCode
class City {
    private final String name;
    private final String zip;
    private final Country country;
}

@RequiredArgsConstructor(staticName = "of")
@Builder
@Getter
@ToString(includeFieldNames = false)
@EqualsAndHashCode
class Country {
    private final String name;
}
