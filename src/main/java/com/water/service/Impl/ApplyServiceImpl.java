package com.water.service.Impl;

import com.water.dao.ApplyDao;
import com.water.dao.SampleDao;
import com.water.dao.UploadDao;
import com.water.entity.Apply;
import com.water.service.ApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

/**
 * Created by 朱晨乾 on 2017/7/17.
 */
@Service
public class ApplyServiceImpl implements ApplyService {
    @Autowired
    private ApplyDao applyDao;

    @Autowired
    private SampleDao sampleDao;

    @Autowired
    private UploadDao uploadDao;

    @Override
    public boolean deleteApply(Long id) {
        if (applyDao.get(id) == null)
            return false;
        applyDao.delete(id);
//        sampleDao.deleteSampleByApplyID(id);
        return true;
    }

    @Override
    public ArrayList<Apply> searchApplicationByUser(String idUser) {
        List<Apply> list1 = applyDao.findApplyById(idUser);
        ArrayList<Apply> list2 = new ArrayList<Apply>();
        for(Apply temp : list1){
                list2.add(temp);
        }
        return list2;
    }

    @Override
    public ArrayList<Apply> findCheckedApply(String userid, String state) {
        List<Apply> applyList=applyDao.findApplyById(userid);
        ArrayList<Apply> resultlist=new ArrayList<Apply>();
        if(state.equals("待审核")){
            for (Apply temp:applyList) {
                if(temp.getState()==0){
                    resultlist.add(temp);
                }
            }

        }
        else if(state.equals("已审核")){
            for (Apply temp:applyList) {
//                (temp.getState()==1||temp.getState()==2)&&uploadDao.findSampleById(temp.getIdApply())==null
                if(temp.getState()==1){
                    resultlist.add(temp);
                }
            }
        }

        Collections.sort(resultlist, new Comparator<Apply>() {
            @Override
            public int compare(Apply o1, Apply o2) {
                int flag = -(o1.getApplyDate().compareTo(o2.getApplyDate()));
                return flag;
            }
        });
        return resultlist;

    }

    @Override
    public boolean addApply(Apply apply) {
        return applyDao.save(apply);
    }

    @Override
    public boolean updateState(Long idApply, Integer state, String responce) {
        Apply apply=applyDao.get(idApply);
        if(apply==null){
            return  false;

        }else {
            apply.setState(state);
            apply.setResponse(responce);
            applyDao.saveOrUpdate(apply);
            return  true;
        }

    }

    @Override
    public Apply getApplyByID(long id) {
        return applyDao.getApplyByID(id);
    }

    @Override
    public boolean updateState(Long id, Integer state) {
        Apply apply = applyDao.get(id);
        if (apply == null)
            return false;
        apply.setState(state);
        applyDao.saveOrUpdate(apply);
        return true;
    }

    @Override
    public Apply searchApplication(Long idApply) {
        return applyDao.get(idApply);
    }

    @Override
    public ArrayList<String> getApplicationList(int state) {
        List<String> arrayList = applyDao.findAllByState(state);
        ArrayList<String> list = new ArrayList<String>();
        for (int i =0;i<arrayList.size();i++) {
                list.add(String.valueOf(arrayList.get(i)));
            }
        return list;
    }

}
