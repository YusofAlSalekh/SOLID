package org.example;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import static org.example.FileGeneration.StringUtils.toLowerCaseFirstLetter;

public class DynamicVerifyGenerator {

    public static void main(String[] args) throws Exception {
        List<Class<?>> implementations = findImplementations("org.example", Incrementation.class);
        generateVerifyClass("org.example", "VerifyInputParam", implementations);
    }

    public static List<Class<?>> findImplementations(String packageName, Class<?> interfaceClass) throws IOException, ClassNotFoundException {
        List<Class<?>> classes = new ArrayList<>();
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        String path = packageName.replace('.', '/');
        Enumeration<URL> resources = classLoader.getResources(path);
        List<String> dirs = new ArrayList<>();
        while (resources.hasMoreElements()) {
            URL resource = resources.nextElement();
            dirs.add(resource.getFile());
        }
        for (String directory : dirs) {
            classes.addAll(findClasses(new File(directory), packageName));
        }

        List<Class<?>> implementations = new ArrayList<>();
        for (Class<?> clazz : classes) {
            if (interfaceClass.isAssignableFrom(clazz) && !Modifier.isAbstract(clazz.getModifiers())) {
                implementations.add(clazz);
            }
        }
        return implementations;
    }

    public static List<Class<?>> findClasses(File directory, String packageName) throws ClassNotFoundException {
        List<Class<?>> classes = new ArrayList<>();
        if (!directory.exists()) {
            return classes;
        }
        File[] files = directory.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                classes.addAll(findClasses(file, packageName + "." + file.getName()));
            } else if (file.getName().endsWith(".class")) {
                classes.add(Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6)));
            }
        }
        return classes;
    }

    public static void generateVerifyClass(String packageName, String className, List<Class<?>> implementations) throws IOException {
        try (PrintWriter writer = new PrintWriter(className + ".java", "UTF-8")) {
            writer.println("package " + packageName + ";");
            writer.println();
            writer.println("import static jbse.meta.Analysis.ass3rt;");
            writer.println();
            writer.println("public class " + className + " {");
            writer.println();
            for (Class<?> impl : implementations) {
                writer.println("    " + impl.getSimpleName() + " " + toLowerCaseFirstLetter(impl.getSimpleName()) + " = new " + impl.getSimpleName() + "();");
            }
            writer.println();
            for (int i = 0; i < implementations.size(); i++) {
                for (int j = i + 1; j < implementations.size(); j++) {
                    writer.println("    public void verify" + implementations.get(i).getSimpleName() + implementations.get(j).getSimpleName() + "(Param input) {");
                    writer.println("        Param a = " + toLowerCaseFirstLetter(implementations.get(i).getSimpleName()) + ".method(input);");
                    writer.println("        Param b = " + toLowerCaseFirstLetter(implementations.get(j).getSimpleName()) + ".method(input);");
                    writer.println("        ass3rt(a.equals(b));");
                    writer.println("    }");
                    writer.println();
                }
            }
            writer.println("}");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
