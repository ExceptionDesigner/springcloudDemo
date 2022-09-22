package com.bobby.cloud.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.Date;

/**
 * @author: Bobby
 * @create: 2020-04-24 16:58
 * @description: 用户实体
 **/
@Data
@Accessors(chain = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("user")
public class User implements Serializable {

    private static final long serialVersionUID = -6021410187541951459L;

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "sex")
    private String sex;

    @Column(name = "address")
    private String address;

    @Column(name = "password")
    private String password;

    /**
     * 删除标签
     */
    @Column(name = "is_deleted")
    private String isDeleted;

    @Column(name = "birthday")
    private Date birthday;



}
