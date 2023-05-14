package com.innovacion.conversor.service.impl;

import com.innovacion.conversor.dto.ConvertRequest;
import com.innovacion.conversor.dto.ConvertResponse;
import com.innovacion.conversor.service.IConversorService;

import com.innovacion.conversor.util.PythonExecutorUtil;
import org.python.core.PyObject;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnProperty(prefix = "app", name = "conversor-pdf", havingValue = "python")
public class ConversorServiceImpl implements IConversorService {

    @Override
    public ConvertResponse convert(ConvertRequest dto) {
        ConvertResponse response = new ConvertResponse();
        return response;
    }

    @Override
    public Integer example(int a, int b) {
        PyObject result = PythonExecutorUtil.executePythonFunction("sumar", 2, 3);
        assert result != null;
        return result.asInt();
    }

}
