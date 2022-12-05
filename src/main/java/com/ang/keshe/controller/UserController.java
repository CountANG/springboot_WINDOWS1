package com.ang.keshe.controller;
import com.ang.keshe.common.Constants;
import com.ang.keshe.common.Result;
import com.ang.keshe.controller.dto.UserDTO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.ang.keshe.service.IUserService;
import com.ang.keshe.entity.User;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author ANG
 * @since 2022-06-18
 */
@RestController
@RequestMapping("/user")
public class UserController {

        @Resource
        private IUserService userService;

        // 新增或者更新
        @PostMapping
        public boolean save(@RequestBody User user) {
                return userService.saveOrUpdate(user);
        }

        @PostMapping("/register")
        public Result register(@RequestBody UserDTO userDTO) {
                String username = userDTO.getUsername();
                String password = userDTO.getPassword();
                String nickname = userDTO.getNickname();
                if(StringUtils.isBlank(username) || StringUtils.isBlank(password) || StringUtils.isBlank(nickname))
                {
                        return Result.error(Constants.CODE_400, "参数错误");
                }
                return Result.success(userService.register(userDTO));
        }

        @PostMapping("/editinfo")
        public Result editinfo(@RequestBody UserDTO userDTO) {
                String username = userDTO.getUsername();
                String nickname = userDTO.getNickname();
                String password = userDTO.getPassword();
                if(StringUtils.isBlank(username) || StringUtils.isBlank(password) || StringUtils.isBlank(nickname))
                {
                        return Result.error(Constants.CODE_400, "参数错误");
                }
                return Result.success(userService.editinfo(userDTO));
        }

        @PostMapping("/login")
        public Result login(@RequestBody UserDTO userDTO) {
                String username = userDTO.getUsername();
                String password = userDTO.getPassword();
                if(StringUtils.isBlank(username) || StringUtils.isBlank(password))
                {
                        return Result.error(Constants.CODE_400, "参数错误");
                }
                UserDTO dto = userService.login(userDTO);


                return Result.success(dto);
        }

        @DeleteMapping("/{id}")
        public Boolean delete(@PathVariable Integer id) {
                return userService.removeById(id);
        }


        @GetMapping
        public List<User> findAll() {
                return userService.list();
        }

        @GetMapping("/{id}")
        public User findOne(@PathVariable Integer id) {
                return userService.getById(id);
        }

        @GetMapping("/page")
        public Page<User> findPage(@RequestParam Integer pageNum,
                                   @RequestParam Integer pageSize,
                                   @RequestParam(defaultValue = "") String name,
                                   @RequestParam(defaultValue = "") String code) {
                QueryWrapper<User> queryWrapper = new QueryWrapper<>();
                if (!"".equals(name)) {
                        queryWrapper.like("name", name);
                }
                if (!"".equals(code)) {
                        queryWrapper.like("code", code);
                }
                queryWrapper.orderByAsc("year");
                return userService.page(new Page<>(pageNum, pageSize), queryWrapper);
        }

}


