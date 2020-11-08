package com.bakatkin.crud.service;

import com.bakatkin.crud.model.Role;
import com.bakatkin.crud.model.User;
import com.bakatkin.crud.repository.RoleRepo;
import com.bakatkin.crud.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceJpaImpl implements UserService, UserDetailsService {

    private UserRepo userRepo;
    private RoleRepo roleRepo;

    @Autowired
    public void setUserRepo(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Autowired
    public void setRoleRepo(RoleRepo roleRepo) {
        this.roleRepo = roleRepo;
    }

    @Transactional
    @Override
    public void add(User user) {
        userRepo.save(user);
    }

    @Override
    public List<User> allUsers() {
        return userRepo.findAll();
    }

    @Override
    @Transactional
    public void changeUser(Long id, String name, String surname, String address, String password, Set<Role> roles) {
        User user = userRepo.getOne(id);
        user.setName(name);
        user.setSurname(surname);
        user.setAddress(address);
        user.setPassword(password);
        user.setRoles(roles);
        userRepo.save(user);
    }

    @Override
    public void deleteById(Long id) {
        userRepo.deleteById(id);
    }

    @Override
    public User getById(Long id) {
        return userRepo.getOne(id);
    }

    @Override
    public User getByName(String name) {
        return userRepo.findByUserName(name);
    }

    @Transactional
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        User user = userRepo.findByUserName(name);
        if (user == null){
            throw new UsernameNotFoundException("User not found");
        }
        return new org.springframework.security.core.userdetails.User(
                user.getName(),
                user.getPassword(),
                rolesToAuthorities(user.getRoles())
        );
    }

    private Collection<? extends GrantedAuthority> rolesToAuthorities(Collection<Role> roles){
        return roles.stream().map(r -> new SimpleGrantedAuthority(r.getRole())).collect(Collectors.toList());
    }

    @Override
    public List<Role> getAllRoles(){
        return roleRepo.findAll();
    }
}
