package com.wangyang.service;

import com.wangyang.pojo.Resource;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface IResourceService {

    void add(Resource resource);
    void delete(int id);
    Page<Resource> page(int page,int size);
}
