package com.ang.keshe.service;

import com.ang.keshe.controller.dto.UserDTO;
import com.ang.keshe.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ANG
 * @since 2022-06-18
 */
public interface IUserService extends IService<User> {
    UserDTO login(UserDTO userDTO);
    User register(UserDTO userDTO);
    User editinfo(UserDTO userDTO);
}
