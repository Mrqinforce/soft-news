package com.soft1851.pojo.bo;

import com.soft1851.validate.CheckUrl;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @ClassName $(Name)
 * @Description TODO
 * @Author Qin jian
 * @Date 2020/11/24
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SaveFriendLinkBO {

    private String id;

    @NotBlank(message = "友情连接名不能为空")
    private String linkName;

    @NotBlank(message = "友情连接地址不能为空")
    @CheckUrl
    private String linkUrl;

    @NotNull(message = "请选择保留或删除")
    private Integer isDelete;
}
