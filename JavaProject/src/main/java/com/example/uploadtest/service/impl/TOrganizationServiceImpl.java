package com.example.uploadtest.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.uploadtest.entity.TOrganization;
import com.example.uploadtest.mapper.TOrganizationMapper;
import com.example.uploadtest.service.TOrganizationService;
import org.springframework.stereotype.Service;

/**
 * @author Jun
 * @date 2022/12/1 10:19
 * @description TOrganizationServiceImpl
 */
@Service
public class TOrganizationServiceImpl extends ServiceImpl<TOrganizationMapper, TOrganization> implements TOrganizationService {
}
