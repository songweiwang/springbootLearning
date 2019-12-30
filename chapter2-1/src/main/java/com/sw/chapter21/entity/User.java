package com.sw.chapter21.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Data
@ApiModel(description = "用户实体")
//@PropertySource(value = "default-user.properties")
@ConfigurationProperties(prefix = "default-user")
@Component
public class User {
    @Id
    @ApiModelProperty("用户编号")
    @NotNull
    private Long id;

    @ApiModelProperty("用户年龄")
    @Max(100)
    @Min(10)
    private int age;

    @ApiModelProperty("用户姓名")
    @NotBlank
    private String name;

}
