package com.document_management.Service;
import com.document_management.Entity.Stage;
import com.document_management.Repository.StageRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StageService {

    private final StageRepository stageRepository;

    public StageService(StageRepository stageRepository) {
        this.stageRepository = stageRepository;
    }

    public List<Stage> getAllStages() {
        return stageRepository.findAll();
    }

    public Stage getStageById(Long id) {
        return stageRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Stage with id " + id + " not found"));
    }

    public Stage saveStage(Stage stage) {
        return stageRepository.save(stage);
    }

//    public void deleteStageById(Long id) {
//
//        stageRepository.deleteById(id);
//    }

}
