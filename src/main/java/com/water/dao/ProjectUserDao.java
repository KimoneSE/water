package com.water.dao;

import com.water.entity.ProjectUser;

import java.util.List;

/**
 * Created by Kimone.
 */
public interface ProjectUserDao {
    public boolean add(ProjectUser projectUser);

    public boolean deleteByProjctID(long projectID);

    public List<ProjectUser> findByProjectID(long projectID);
}
