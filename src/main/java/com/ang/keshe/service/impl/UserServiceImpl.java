package com.ang.keshe.service.impl;

import com.ang.keshe.common.Constants;
import com.ang.keshe.controller.dto.UserDTO;
import com.ang.keshe.entity.User;
import com.ang.keshe.exception.ServiceException;
import com.ang.keshe.mapper.UserMapper;
import com.ang.keshe.service.IUserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ANG
 * @since 2022-06-18
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Override
    public UserDTO login(UserDTO userDTO) {
        User one = getUserInfo(userDTO);
        if (one != null) {
            BeanUtils.copyProperties(one, userDTO);
            return userDTO;
        } else {
            throw new ServiceException(Constants.CODE_600, "用户名或密码错误");
        }
    }

    @Override
    public User register(UserDTO userDTO) {
        User one = getUserInfoUsername(userDTO);
        if (one == null) {
            one = new User();
            BeanUtils.copyProperties(userDTO, one);
            save(one);  // 将用户对象存储到数据库
        } else {
            throw new ServiceException(Constants.CODE_600, "用户已存在");
        }
        return one;
    }

    @Override
    public User editinfo(UserDTO userDTO) {     //修改已有账户的昵称和密码
        User one = getUserInfoUsername(userDTO);
        if (one != null) {
            removeById(one.getId());
            BeanUtils.copyProperties(userDTO, one);
            save(one);

        } else {
            throw new ServiceException(Constants.CODE_500, "系统错误");
        }
        return one;
    }

    private User getUserInfo(UserDTO userDTO) {     //根据username和password查询对应账户
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", userDTO.getUsername());
        queryWrapper.eq("password", userDTO.getPassword());
        User one;
        try {
            one = getOne(queryWrapper); // 从数据库查询用户信息
        } catch (Exception e) {
            throw new ServiceException(Constants.CODE_500, "系统错误");
        }
        return one;
    }

    private User getUserInfoUsername(UserDTO userDTO) {     //根据username查询对应账户
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", userDTO.getUsername());
        User one;
        try {
            one = getOne(queryWrapper); // 从数据库查询用户信息
        } catch (Exception e) {
            throw new ServiceException(Constants.CODE_500, "系统错误");
        }
        return one;
    }



}