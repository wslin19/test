package com.company.project.service.impl;

import com.company.project.dao.NodeMapper;
import com.company.project.model.Node;
import com.company.project.service.NodeService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2018/12/29.
 */
@Service
@Transactional
public class NodeServiceImpl extends AbstractService<Node> implements NodeService {
    @Resource
    private NodeMapper nodeMapper;

}
