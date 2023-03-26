package com.get.businesslayer.service;

import com.get.businesslayer.IdType;
import com.get.presistence.repository.IdTypeRepository;

public class IdTypeService {
    IdTypeRepository idTypeRepository;

    public IdType save(IdType idType) {
        return idTypeRepository.save(idType);
    }
}
