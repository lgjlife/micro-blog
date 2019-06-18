package com.microblog.center.banner;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringBootVersion;
import org.springframework.boot.ansi.AnsiColor;
import org.springframework.boot.ansi.AnsiOutput;
import org.springframework.boot.ansi.AnsiStyle;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.io.PrintStream;

@Component
public class SpringBootBanner implements Banner {


    private static final String[] BANNER = { "",
            " _____ ______   ___  ________  ________  ________  ________  ___       ________  ________     \n" +
                    "|\\   _ \\  _   \\|\\  \\|\\   ____\\|\\   __  \\|\\   __  \\|\\   __  \\|\\  \\     |\\   __  \\|\\   ____\\    \n" +
                    "\\ \\  \\\\\\__\\ \\  \\ \\  \\ \\  \\___|\\ \\  \\|\\  \\ \\  \\|\\  \\ \\  \\|\\ /\\ \\  \\    \\ \\  \\|\\  \\ \\  \\___|    \n" +
                    " \\ \\  \\\\|__| \\  \\ \\  \\ \\  \\    \\ \\   _  _\\ \\  \\\\\\  \\ \\   __  \\ \\  \\    \\ \\  \\\\\\  \\ \\  \\  ___  \n" +
                    "  \\ \\  \\    \\ \\  \\ \\  \\ \\  \\____\\ \\  \\\\  \\\\ \\  \\\\\\  \\ \\  \\|\\  \\ \\  \\____\\ \\  \\\\\\  \\ \\  \\|\\  \\ \n" +
                    "   \\ \\__\\    \\ \\__\\ \\__\\ \\_______\\ \\__\\\\ _\\\\ \\_______\\ \\_______\\ \\_______\\ \\_______\\ \\_______\\\n" +
                    "    \\|__|     \\|__|\\|__|\\|_______|\\|__|\\|__|\\|_______|\\|_______|\\|_______|\\|_______|\\|_______|\n" +
                    "                                                                                              " };

    private static final String SPRING_BOOT = "Jeesuite-libs With Spring Boot";

    private static final int STRAP_LINE_SIZE = 42;

    @Override
    public void printBanner(Environment environment, Class<?> sourceClass,
                            PrintStream printStream) {
        for (String line : BANNER) {
            printStream.println(line);
        }
        String version = SpringBootVersion.getVersion();
        version = (version == null ? "" : " (v" + version + ")");
        String padding = "";
        while (padding.length() < STRAP_LINE_SIZE
                - (version.length() + SPRING_BOOT.length())) {
            padding += " ";
        }

        printStream.println(AnsiOutput.toString(AnsiColor.GREEN, SPRING_BOOT,
                AnsiColor.DEFAULT, padding, AnsiStyle.FAINT, version));
        printStream.println();
    }
}
