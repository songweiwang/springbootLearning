package com.sw.chapter21.web;

import com.sw.chapter21.entity.User;
import com.sw.chapter21.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping(value = "/users")
@Api(tags = "用户管理")
@Slf4j
public class UserController {

    //    static Logger logger = LogManager.getLogger(UserController.class);
    @Autowired
    private UserService userService;

    @Autowired
    private User defaultUser;
    // 线程安全的map， 模拟user的存储
    static Map<Long, User> users = Collections.synchronizedMap(new HashMap<Long, User>());

    //处理get请求， 获取用户列表
    @GetMapping("/get/{mode}")
    @ApiOperation(value = "获取用户列表", notes = "获取用户列表")
//    @ApiImplicitParam(paramType = "path", dataType = "int", name = "用户区域", value = "", , example = "1")
    public List<User> getUserList(@PathVariable(required = false) Integer mode) {
        if (users.size() == 0) {
            users.put(defaultUser.getId(), defaultUser);
        }
        if (mode == null) {
            mode = 0;
        }
        List<User> r = new ArrayList<User>();
        switch (mode) {
            case 0:
                log.info("获取内存中的用户");
                r.addAll(users.values());
                break;
            case 1:
                log.info("获取数据库中的用户");
                r.addAll(userService.getAllUsers());
                break;
            case 2:
                log.info("获取内存和数据库中的所有用户");
                r.addAll(users.values());
                r.addAll(userService.getAllUsers());
                break;
            default:
                log.error("传入参数错误");
                break;
        }


        return r;
    }

    //处理post请求，增加用户
    @PostMapping
    @ApiOperation(value = "新增用户", notes = "根据User对象创建用户")
    public String postUser(@Valid @RequestBody User user) {
        //RequestBody 注解， 用来绑定通过http请求中application/json上送的数据
//        logger.info(user.getId());
        if (null == user.getId()) {
            return "can't be null";
        }
        users.put(user.getId(), user);
        return "success";
    }

    //处理"users/{id}"的get请求，用来获取url中id值的user信息
    @GetMapping("/{id}")
    @ApiOperation(value = "根据ID获取用户详细信息", notes = "根据url的id来获取用户详细信息")
    public User getUser(@PathVariable Long id) {
        //url中的id可以通过注解@PathVariable绑定到函数的参数
        return users.get(id);
    }

    //处理put请求， 用来更新user信息
    @PutMapping("/{id}")
    @ApiImplicitParam(paramType = "path", dataType = "Long", name = "id", value = "用户编号", required = true, example = "1")
    @ApiOperation(value = "更新用户详细信息", notes = "根据url的id来指定更新对象，并根据传过来的user信息来更新用户详细信息")
    public String updateUser(@PathVariable Long id, @RequestBody User user) {
        User u = users.get(id);
        u.setAge(user.getAge());
        u.setName(user.getName());
//        users.put(id,u);
        return "success";
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除用户", notes = "根据url的id来指定删除对象")
    public String deleteUser(@PathVariable Long id) {
        if (users.containsKey(id)) {
            users.remove(id);
            return "success";
        } else
            return "false, there is no user with id " + id;
    }

    @GetMapping("/find/{username}")
    @ApiOperation(value = "查询用户", notes = "根据url的name来获取对象的详细信息")
    public User getUserByName(@PathVariable("username") String name) {
        return userService.findUserByName(name);
    }
}
