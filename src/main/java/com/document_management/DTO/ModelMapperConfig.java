//package com.document_management.DTO;
//
//import com.document_management.Entity.DocumentVersion;
//import org.modelmapper.ModelMapper;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class ModelMapperConfig {
//
//    @Bean
//    public ModelMapper modelMapper() {
//        ModelMapper modelMapper = new ModelMapper();
//
//        modelMapper.createTypeMap(DocumentVersion.class, DocumentVersionDto.class)
//                .addMapping(src -> src.getDocument().getDocumentId(),
//                        DocumentVersionDto::setDocumentId)
//                .addMapping(src -> src.getDocument().getName(),
//                        DocumentVersionDto::setDocumentName)
//                .addMapping(src -> src.getDocument().getAuthor(),
//                        DocumentVersionDto::setAuthorName);
//
//        return modelMapper;
//    }
//}
//
//
//
