package org.example;

import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.lang.ArchCondition;
import com.tngtech.archunit.lang.ConditionEvents;
import com.tngtech.archunit.lang.SimpleConditionEvent;
import org.example.exceptions.FileReadingException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomConditions {
    public static ArchCondition<JavaClass> notHaveUnsafeInTxtFiles(String txtFilesDirectory) {
        return new ArchCondition<JavaClass>("not have Unsafe > 0 in any txt file") {
            @Override
            public void check(JavaClass javaClass, ConditionEvents events) {
                File dir = new File(txtFilesDirectory);
                List<File> unsafeFiles = new ArrayList<>();

                if (dir.isDirectory()) {
                    for (File file : dir.listFiles((d, name) -> name.endsWith(".txt"))) {
                        boolean unsafeFoundInFile = false;
                        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                            String line;
                            Pattern pattern = Pattern.compile("Unsafe:\\s*(\\d+)");
                            while ((line = reader.readLine()) != null) {
                                Matcher matcher = pattern.matcher(line);
                                if (matcher.find() && Integer.parseInt(matcher.group(1)) > 0) {
                                    unsafeFoundInFile = true;
                                    break;
                                }
                            }
                        } catch (IOException e) {
                            throw new FileReadingException("Error reading file: " + file.getName(), e);
                        }

                        if (unsafeFoundInFile) {
                            unsafeFiles.add(file);
                        }
                    }

                    if (!unsafeFiles.isEmpty()) {
                        for (File unsafeFile : unsafeFiles) {
                            String message = String.format("Class %s has Unsafe > 0 in txt file %s", javaClass.getFullName(), unsafeFile.getName());
                            events.add(SimpleConditionEvent.violated(javaClass, message));
                        }
                    }
                }
            }
        };
    }
}
