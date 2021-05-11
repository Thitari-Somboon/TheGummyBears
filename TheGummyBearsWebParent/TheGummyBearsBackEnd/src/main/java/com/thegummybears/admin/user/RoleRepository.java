package com.thegummybears.admin.user;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.thegummybears.common.entity.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Integer>{

}