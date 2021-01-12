package nl.risa.it.deel_1.jenkinscursus;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import java.nio.file.Files;
import java.nio.file.Paths;
import org.junit.jupiter.api.Test;

class JenkinsDemoApplicationTests {

	protected static final String MESSAGE_OUT = "target/message.out";

	@Test
	void testOutputDefaultValue() throws Exception {
		JenkinsDemoApplication.main();

		assertThat(Files.readAllLines(Paths.get(MESSAGE_OUT)))
			.containsExactly("Hello Jenkins");
	}

	@Test
	void testOutputPassMessage() throws Exception {
		String HelloWorldMessage = "Hello World";
		JenkinsDemoApplication.main(HelloWorldMessage);

		assertThat(Files.readAllLines(Paths.get(MESSAGE_OUT)))
			.containsExactly(HelloWorldMessage);
	}

	@Test
	void testOutputDoesNotThrowAnException() {
		assertThatCode(JenkinsDemoApplication::main).doesNotThrowAnyException();
	}
}
