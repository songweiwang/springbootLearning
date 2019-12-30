package com.sw.chapter21.web;

import com.sw.chapter21.entity.User;
import com.sw.chapter21.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@Api(tags = "其他Controller")
public class OtherController {
    @Autowired
    private User user;
    @Autowired
    private UserService userService;

    @GetMapping("randomUser/{isInsertDB}")
    @ApiOperation(value = "生成一个随机的用户", notes = "生成一个随机的用户，true插入数据库， false不插入数据库")
    public User getRandomUser(@PathVariable(required = false) boolean isInsertDB) {
        if (true == isInsertDB) {
            log.info("插入数据库");
            userService.insertDB(user);
        }
        return user;
    }
}
