package com.document_management.Controller;

import com.document_management.DTO.StageDto;
import com.document_management.Entity.Stage;
import com.document_management.Service.StageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/stages")
public class StageController {

    @Autowired
    private StageService stageService;

    @GetMapping("/{stageId}")
    public ResponseEntity<StageDto> getStageById(@PathVariable int stageId) {
        StageDto stageDto = stageService.getStageById(stageId);
        return ResponseEntity.ok(stageDto);
    }

    @PostMapping
    public ResponseEntity<StageDto> createStage(@RequestBody StageDto stageDto) {
        StageDto createdStageDto = stageService.createStage(stageDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdStageDto);
    }

@GetMapping("/abc")
public String hi(){
        return "abc";
}
    @PostMapping("/")
    public ResponseEntity<Stage> saveStage(@RequestBody Stage stage) {
        Stage savedStage = stageService.saveStage(stage);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedStage);


//     @PutMapping("/{stageId}")
//     public ResponseEntity<StageDto> updateStage(@PathVariable int stageId, @RequestBody StageDto stageDto) {
//         StageDto updatedStageDto = stageService.updateStage(stageId, stageDto);
//         return ResponseEntity.ok(updatedStageDto);

    }

    @DeleteMapping("/{stageId}")
    public ResponseEntity<Void> deleteStage(@PathVariable int stageId) {
        stageService.deleteStage(stageId);
        return ResponseEntity.noContent().build();
    }
}
