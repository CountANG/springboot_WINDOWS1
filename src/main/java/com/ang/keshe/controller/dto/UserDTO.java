package com.ang.keshe.controller.dto;

import lombok.Data;

/**
 * 接受前端登录的账号信息
 */
@Data
public class UserDTO {
    private String username;
    private String password;
    private String nickname;
    private String priority;
}
