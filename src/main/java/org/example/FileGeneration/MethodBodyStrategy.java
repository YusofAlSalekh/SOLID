package org.example.FileGeneration;

import org.example.DataClass;

import java.util.List;

public interface MethodBodyStrategy {
    String generateMethodBody(List<DataClass> dataClasses) throws Exception;
}
