package com.company.project.service.impl;

import com.company.project.dao.LogEquipmentMapper;
import com.company.project.model.LogEquipment;
import com.company.project.service.LogEquipmentService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2018/12/29.
 */
@Service
@Transactional
public class LogEquipmentServiceImpl extends AbstractService<LogEquipment> implements LogEquipmentService {
    @Resource
    private LogEquipmentMapper logEquipmentMapper;

}
