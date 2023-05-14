package com.innovacion.conversor.service;

import com.innovacion.conversor.dto.ConvertRequest;
import com.innovacion.conversor.dto.ConvertResponse;

public interface IConversorService {

    ConvertResponse convert(ConvertRequest dto);
    Integer example(int a, int b);

}
