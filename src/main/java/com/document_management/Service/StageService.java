package com.document_management.Service;
import com.document_management.DTO.StageDto;
import com.document_management.Entity.Stage;
import com.document_management.Repository.StageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.persistence.EntityNotFoundException;

@Service
public class StageService {

    @Autowired
    private StageRepository stageRepository;

    public StageDto getStageById(int stageId) {
        Stage stage = stageRepository.findById(stageId)
                .orElseThrow(() -> new EntityNotFoundException("Stage not found"));
        return mapStageToStageDto(stage);
    }

    public StageDto createStage(StageDto stageDto) {
        Stage stage = mapStageDtoToStage(stageDto);
        Stage savedStage = stageRepository.save(stage);
        return mapStageToStageDto(savedStage);
    }

    public StageDto updateStage(int stageId, StageDto stageDto) {
        Stage stage = stageRepository.findById(stageId)
                .orElseThrow(() -> new EntityNotFoundException("Stage not found"));
        stage.setStageName(stageDto.getStageName());
        stage.setApproved(stageDto.isApproved()); // Set the approval status
        Stage savedStage = stageRepository.save(stage);
        return mapStageToStageDto(savedStage);
    }

    public void deleteStage(int stageId) {
        Stage stage = stageRepository.findById(stageId)
                .orElseThrow(() -> new EntityNotFoundException("Stage not found"));
        stageRepository.delete(stage);
    }

    private StageDto mapStageToStageDto(Stage stage) {
        return new StageDto(stage.getStageId(), stage.getStageName(), stage.isApproved());
    }

    private Stage mapStageDtoToStage(StageDto stageDto) {
        return new Stage(stageDto.getStageId(), stageDto.getStageName(), stageDto.isApproved());
    }
}
