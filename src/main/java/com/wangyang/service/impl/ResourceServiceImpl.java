package com.wangyang.service.impl;

import com.wangyang.dao.IResourceDao;
import com.wangyang.pojo.Resource;
import com.wangyang.service.IResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ResourceServiceImpl implements IResourceService {

    @Autowired
    IResourceDao resourceDao;

    @Override
    public void add(Resource resource) {
        resourceDao.save(resource);
    }

    @Override
    public void delete(int id) {
        resourceDao.deleteById(id);
    }

    @Override
    public Page<Resource> page(int page, int size) {
        Page<Resource> resources = resourceDao.findAll(PageRequest.of(page, size, Sort.by(Sort.Order.desc("id"))));
        return resources;
    }
}
