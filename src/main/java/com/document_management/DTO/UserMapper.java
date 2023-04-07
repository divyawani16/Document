package com.document_management.DTO;
        import com.document_management.Entity.Users;
        import org.modelmapper.ModelMapper;
        import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    private ModelMapper modelMapper = new ModelMapper();

    public UserDto toUserDto(Users user) {
        return modelMapper.map(user, UserDto.class);
    }

    public Users toUser(UserDto userDto) {
        return modelMapper.map(userDto, Users.class);
    }
}

