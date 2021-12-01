package com.springrest.springrest.services;


import com.springrest.springrest.entities.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    List<Users> list;
   /* @Autowired
    private UserDao userDao;*/

    public UserServiceImpl()
    {
        list=new ArrayList<>();
        list.add(new Users(Long.parseLong("1"),"Rupali","Mittal","rup@gmail.com","8007679042"));
        list.add(new Users(Long.parseLong("2"),"Rohit","Saroha","rochkilla@gmail.com","8007821548"));
    }
    @Override
    public List<Users> getUsers()
    {
        return  list;
        //return userDao.findAll();
    }

    @Override
    public Users getUser(Long userId)
    {
        Users u=null;
        for(Users user:list)
        {
            if(user.getUserId()==userId)
            {
                u=user;
                break;
            }

        }
        return  u;
        //return userDao.getById(userId);
    }

    @Override
    public Users addUser(Users user)
    {
        list.add(user);
        //userDao.save(user);
        return  user;
    }

    @Override
    public Users updateUser(Users user)
    {
        list.forEach(u->{
            if(u.getUserId()==user.getUserId()) {
                u.setEmailId(user.getEmailId());
                u.setPhoneNumber(user.getPhoneNumber());
            }
        });

        //userDao.save(user); // if userid is already present it will be updated.If userid not present then it will be added
        return  user;
    }

    @Override
    public void deleteUser(Long userId)
    {
       list=this.list.stream().filter(u->u.getUserId()!=userId).collect(Collectors.toList());
       //userDao.deleteById(userId);
        //Users user= userDao.getById(userId);
        //userDao.delete(user);
    }


}
