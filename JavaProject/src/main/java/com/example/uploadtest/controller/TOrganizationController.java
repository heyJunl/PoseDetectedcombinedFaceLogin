package com.example.uploadtest.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.uploadtest.entity.TOrganization;
import com.example.uploadtest.service.TOrganizationService;
import com.example.uploadtest.vo.DataView;
import com.example.uploadtest.vo.TOrganizationVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Jun
 * @date 2022/12/1 10:22
 * @description TOrganizationController
 */
@RestController
public class TOrganizationController {
    @Autowired
    private TOrganizationService tOrganizationService;

    @GetMapping("/loadAllData")
    public DataView loadAllData(){
//        IPage<TOrganization> page = new Page<>(tOrganizationVo.getPage(), tOrganizationVo.getLimit());
        List<TOrganization> list = tOrganizationService.list();
        return new DataView(list);
    }
}
