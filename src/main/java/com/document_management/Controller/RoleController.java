package com.document_management.Controller;
import com.document_management.DTO.RoleDto;
import com.document_management.Service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping("/{roleId}")
    public ResponseEntity<RoleDto> getRoleById(@PathVariable Integer roleId) {
        RoleDto roleDto = roleService.getRoleById(roleId);
        return ResponseEntity.ok(roleDto);
    }

    @GetMapping("/")
    public ResponseEntity<List<RoleDto>> getAllRoles() {
        List<RoleDto> roleDtoList = roleService.getAllRoles();
        return ResponseEntity.ok(roleDtoList);
    }

    @PostMapping("/")
    public ResponseEntity<RoleDto> createRole(@RequestBody RoleDto roleDto) {
        RoleDto createdRoleDto = roleService.createRole(roleDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdRoleDto);
    }

    @PutMapping("/{roleId}")
    public ResponseEntity<RoleDto> updateRole(@PathVariable Integer roleId, @RequestBody RoleDto roleDto) {
        RoleDto updatedRoleDto = roleService.updateRole(roleId, roleDto);
        return ResponseEntity.ok(updatedRoleDto);
    }

    @DeleteMapping("/{roleId}")
    public ResponseEntity<Void> deleteRole(@PathVariable Integer roleId) {
        roleService.deleteRole(roleId);
        return ResponseEntity.noContent().build();
    }

}
