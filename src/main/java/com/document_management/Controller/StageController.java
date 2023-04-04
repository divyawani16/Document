package com.document_management.Controller;
import com.document_management.Entity.Stage;
import com.document_management.Service.StageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/stages")
public class StageController {

    private final StageService stageService;
    public StageController(StageService stageService) {
        this.stageService = stageService;
    }

    @GetMapping("/get")
    public ResponseEntity<List<Stage>> getAllStages() {
        List<Stage> stages = stageService.getAllStages();
        return ResponseEntity.ok(stages);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Stage> getStageById(@PathVariable Long id) {
        Stage stage = stageService.getStageById(id);
        return ResponseEntity.ok(stage);
    }

    @PostMapping("/add")
    public ResponseEntity<Stage> saveStage(@RequestBody Stage stage) {
        Stage savedStage = stageService.saveStage(stage);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedStage);
    }

//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteStageById(@PathVariable Long id) {
//        stageService.deleteStageById(id);
//        return ResponseEntity.noContent().build();
//    }
}
