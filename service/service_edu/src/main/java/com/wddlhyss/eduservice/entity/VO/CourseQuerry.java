package com.wddlhyss.eduservice.entity.VO;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CourseQuerry {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "课程名称,模糊查询")
    private String title;

    @ApiModelProperty(value = "课程状态normal已发布")
    private String status;
}
