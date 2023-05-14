package com.innovacion.conversor.util;

import org.python.core.PyObject;
import org.python.util.PythonInterpreter;

public class PythonExecutor {
    public static void executePythonCode(String pythonCode) {
        try (PythonInterpreter interpreter = new PythonInterpreter()) {
            interpreter.exec(pythonCode);
        }
    }

    public static PyObject executePythonFunction(String functionName, Object... args) {
        try (PythonInterpreter interpreter = new PythonInterpreter()) {
            interpreter.execfile("python/script.py");

            PyObject pyFunction = interpreter.get(functionName);
            if (pyFunction != null && pyFunction.isCallable()) {
                return pyFunction.__call__((PyObject[]) args);
            }
        }
        return null;
    }
}
