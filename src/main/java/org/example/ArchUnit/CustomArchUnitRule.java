package org.example.ArchUnit;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;
import org.example.Incrementation;
import org.example.Sorter;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

public class CustomArchUnitRule {
    public static void main(String[] args) {
        JavaClasses importedClasses = new ClassFileImporter().importPackages("org.example");

        String packageName = "org.example";
        String outputDir = "build/classes/java/main";

        ArchRule txtFileCheckRule = classes()
                .that().implement(Incrementation.class)
                .should(CustomConditions.notHaveUnsafeInTxtFiles(packageName, outputDir, Incrementation.class));

        txtFileCheckRule.check(importedClasses);
    }
}