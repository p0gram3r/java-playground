import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Named;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class PointlessTest {

    @Nested
    class when_doing_something {

        @Test
        void then_something_should_happen() {
        }

        @Test
        @DisplayName("@DisplayName takes precedence over generation")
        void override_generator() {
        }
    }

    @ParameterizedTest
    @MethodSource("namedArguments")
    void whenUsingNamedInterface_thenGenerateCustomDisplayNames(String givenArg) {
        // Test
    }

    private static Stream<Arguments> namedArguments() {
        return Stream.of(
                Arguments.of(Named.of("Testing with a city", "Tokyo")),
                Arguments.of(Named.of("Testing with a country", "Japan")),
                Arguments.of(Named.of("Testing with a continent", "Asia"))
        );
    }
}
