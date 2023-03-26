package com.get.businesslayer;

import com.get.businesslayer.response.Item;
import com.get.presistence.repository.ItemRepository;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

    private ItemRepository itemRepository;
    private ModelMapper modelMapper;


    public ItemDTO convertToDto(Item item) {
        return modelMapper.map(item, ItemDTO.class);
    }

    public Item save(Item toSave) {
        return itemRepository.save(toSave);
    }
}

@Component
class ModelMapperConfig {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
