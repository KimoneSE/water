package com.water.service;

import com.water.entity.ProjectUser;

import java.util.List;

/**
 * Created by Kimone.
 */
public interface ProjectUserService {
    public boolean add(String userList,long projectID);

    public boolean modify(String userList,long projectID);

    public List<ProjectUser> findByProjectID(long projectID);

    public boolean deleteByProjectID(long projectID);
}
