package com.example.uploadtest.vo;

import com.example.uploadtest.entity.TOrganization;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author Jun
 * @date 2022/12/1 10:14
 * @description TOrganizationVo
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class TOrganizationVo extends TOrganization {
    private Integer limit;
    private Integer page;
}
