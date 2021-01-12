package nl.risa.it.deel_1.jenkinscursus;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JenkinsDemoApplication {

    public static void main(String... args) throws IOException {
        String message = "Hello Jenkins";
        if (args.length == 1) {
            message = args[0];
        }

        Files.write(Paths.get("target/message.out"),
                    message.getBytes(StandardCharsets.UTF_8));
    }

}
