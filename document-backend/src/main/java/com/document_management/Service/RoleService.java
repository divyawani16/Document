package com.document_management.Service;

import com.document_management.DTO.RoleDto;
import com.document_management.Entity.Role;
import com.document_management.Repository.RoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleService {
    private RoleRepository roleRepository;
    private ModelMapper modelMapper;

    @Autowired
    public RoleService(RoleRepository roleRepository, ModelMapper modelMapper) {
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
    }

    public RoleDto getRoleById(Integer roleId) {
        Role role = roleRepository.findById(roleId).orElseThrow(() -> new EntityNotFoundException("Role not found"));
        return modelMapper.map(role, RoleDto.class);
    }

    public RoleDto createRole(RoleDto roleDto) {
        Role role = modelMapper.map(roleDto, Role.class);
        Role createdRole = roleRepository.save(role);
        return modelMapper.map(createdRole, RoleDto.class);
    }

    public RoleDto updateRole(Integer roleId, RoleDto roleDto) {
        Role role = roleRepository.findById(roleId).orElseThrow(() -> new EntityNotFoundException("Role not found"));
        modelMapper.map(roleDto, role);
        Role updatedRole = roleRepository.save(role);
        return modelMapper.map(updatedRole, RoleDto.class);
    }

    public void deleteRole(Integer roleId) {
        roleRepository.deleteById(roleId);
    }

    public List<RoleDto> getAllRoles() {
        List<Role> roles = roleRepository.findAll();
        return roles.stream()
                .map(role -> modelMapper.map(role, RoleDto.class))
                .collect(Collectors.toList());
    }
}
