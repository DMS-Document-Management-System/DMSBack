package com.si.apirest.security.config;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.si.apirest.entity.PermissionEntity;
import com.si.apirest.entity.Person;
import com.si.apirest.entity.RoleEntity;
import com.si.apirest.enums.Permission;
import com.si.apirest.enums.Role;
import com.si.apirest.repository.PermissionRepository;
import com.si.apirest.repository.PersonRepository;
import com.si.apirest.repository.RolRepository;

import jakarta.annotation.PostConstruct;

@Component
public class AppInit {
    
    @Autowired
    private  PersonRepository personRepository;
    
    @Autowired
    private  PermissionRepository permissionRepository;
    
    @Autowired
    private  RolRepository rolRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private ModelMapper modelMapper;

    
	@Value("${app.admin.password}")
	private String adminPassword;


    @PostConstruct
    private void postConstruct() {
        if (permissionRepository.count()==0 ){
            createPermission();
        }
    }

	private void createPermission() {
		Set<PermissionEntity> permissionEntities = new HashSet<PermissionEntity>();
		List<PermissionEntity> permissionEntitiesSaved = new ArrayList<PermissionEntity>();
		//Guarda todos los permisos del enum Permission
		for (String nombre : Permission.getAllPermissionNames()) {
			permissionEntities.add(PermissionEntity.builder().nombre(nombre).build());
		}
		permissionEntitiesSaved = permissionRepository.saveAllAndFlush(permissionEntities);
	}
	

/*     private void createRoles() {
        Set<PermissionEntity> permissionEntities = new HashSet<PermissionEntity>();
			List<PermissionEntity> permissionEntitiesSaved = new ArrayList<PermissionEntity>();
			//Guarda todos los permisos del enum Permission
			for (String nombre : Permission.getAllPermissionNames()) {
				permissionEntities.add(PermissionEntity.builder().nombre(nombre).build());
			}
			permissionEntitiesSaved = permissionRepository.saveAllAndFlush(permissionEntities);

            //Se guarda el Rol ADMIN
			RoleEntity rolSaved = rolRepository.save(RoleEntity.builder()
            .name(Role.ADMIN.toString())
            .permissions(permissionEntitiesSaved)
            .build());

            //Se guarda el Rol USER
            List<PermissionEntity> permissionUser = new ArrayList<PermissionEntity>();
			for (PermissionEntity permission : permissionEntitiesSaved){
				if(permission.getNombre().equals(Permission.VER_HOME.toString()))
					permissionUser.add(permission);
			}

			rolRepository.save(RoleEntity.builder()
										.name(Role.USER.toString())
										.permissions(permissionUser)
										.build());
			
			//Crea el usuario Admin
			createUserAdmin(rolSaved);
	}

	private void createUserAdmin(RoleEntity rol) {
		System.out.println(adminPassword);
		Optional<Person> optionalUser = personRepository.findByUsuario(Role.ADMIN.toString());
        Person user= Person.builder()
		.usuario(Role.ADMIN.toString())
		.contraseña(passwordEncoder.encode(adminPassword))
		.role(rol)
		.enabled(true)
		.build();

		optionalUser.ifPresent(userAdmin-> {
			modelMapper.map(userAdmin, user);
		});
		
		personRepository.save(user);
	} */
}
