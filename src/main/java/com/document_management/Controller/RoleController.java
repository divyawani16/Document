package com.document_management.Controller;
import com.document_management.Entity.Role;
import com.document_management.Service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping("/roles")
    public List<Role> getAllRoles() {
        return roleService.getAllRoles();
    }

    @GetMapping("/roles/{id}")
    public Role getRoleById(@PathVariable Long id) {
        return roleService.getRoleById(id);
    }

    @PostMapping("/roles")
    public Role createRole(@RequestBody Role role) {
        return roleService.createRole(role);
    }

    @PutMapping("/roles/{id}")
    public Role updateRole(@PathVariable Long id, @RequestBody Role roleDetails) {
        return roleService.updateRole(id, roleDetails);
    }

//    @DeleteMapping("/roles/{id}")
//    public void deleteRole(@PathVariable Long id) {
//        roleService.deleteRole(id);
//    }

}
