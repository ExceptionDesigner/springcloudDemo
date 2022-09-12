package com.bobby.domain;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.annotation.Transient;

import java.io.Serializable;
import java.util.Date;

@Data
public class BasePo implements Serializable {
    public final static String DEFAULT_USERNAME = "system";
    @TableId(type = IdType.ID_WORKER_STR)
    private String id;

    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createdTime = new Date();

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @TableField(fill = FieldFill.INSERT)
    private String createdBy = "system";

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Transient
    @TableField(exist = false)
    private int page=1;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Transient
    @TableField(exist = false)
    private int limit=20;
}
