package com.water.service.Impl;

import com.water.dao.ApplyDao;
import com.water.dao.UserDao;
import com.water.entity.Apply;
import com.water.service.ApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by 朱晨乾 on 2017/7/17.
 */
@Service
public class ApplyServiceImpl implements ApplyService {
    @Autowired
    private ApplyDao applyDao;

    @Autowired
    private UserDao userDao;

    //这个方法用来测试增加数据
    public void addApply() {
        Apply apply = new Apply();
        apply.setIdApply(Long.valueOf(55555555));
        apply.setAddress("南京市鼓楼区");
        apply.setImage("resources/img/");
        apply.setLatitude(28.55);
        apply.setLongitude(120.75);
        apply.setApplyDate(new Date(System.currentTimeMillis()));
        apply.setName("Tati");
        apply.setWaterAddress("渤海");
        apply.setNumber("33333333333");
        apply.setUser(userDao.get("FluteRRR"));
        apply.setState(0);
        applyDao.save(apply);
    }

    /**
     * 删除apply 不存在返回false
     *
     * @param id
     * @return
     */
    public boolean deleteApply(Long id) {
        if (applyDao.get(id) == null)
            return false;
        applyDao.delete(id);
        return true;
    }

    /**
     * 根据用户id返回申请列表
     * @param idUser
     * @return
     */
    public ArrayList<Apply> searchApplicationByUser(String idUser) {
        List<Apply> list1 = applyDao.findAll();
        ArrayList<Apply> list2 = new ArrayList<Apply>();
        for(Apply temp : list1){
            if(temp.getUser().getIdUser() == idUser){
                list2.add(temp);
            }
        }
        return list2;
    }

    public ArrayList<Apply> findCheckedApply(String userid, String state) {
        List<Apply> list=applyDao.findAll();
        ArrayList<Apply> userlist=new ArrayList<Apply>();
        ArrayList<Apply> resultlist=new ArrayList<Apply>();
        for (Apply temp:list
             ) {
            if(temp.getUser().getIdUser()==userid){
                userlist.add(temp);
            }
        }
        if(state=="待审核"){
            for (Apply temp:userlist
                 ) {
                if(temp.getState()==0){
                    resultlist.add(temp);
                }
            }

        }
        else if(state=="已审核"){
            for (Apply temp:userlist
                    ) {
                if(temp.getState()==1||temp.getState()==2){
                    resultlist.add(temp);
                }
            }
        }
        return resultlist;


    }

    /**
     * 更改状态 申请号不存在返回false
     *
     * @param id
     * @param state
     * @return
     */
    public boolean updateState(Long id, Integer state) {
        Apply apply = applyDao.get(id);
        if (apply == null)
            return false;
        apply.setState(state);
        applyDao.saveOrUpdate(apply);
        return true;
    }

    public void addApplication(Long idApply, Double longitude, Double latitude, String number, String address, Date applyDate, Integer state, String image, String name, String waterAddress, String idUser) {
        Apply apply = new Apply();
        apply.setIdApply(idApply);
        apply.setLongitude(longitude);
        apply.setLatitude(latitude);
        apply.setNumber(number);
        apply.setAddress(address);
        apply.setApplyDate(applyDate);
        apply.setState(state);
        apply.setImage(image);
        apply.setName(name);
        apply.setWaterAddress(waterAddress);
        apply.getUser().setIdUser(idUser);
        applyDao.save(apply);

    }

    /**
     * 根据申请号搜索apply
     *
     * @param idApply
     * @return
     */
    public Apply searchApplication(Long idApply) {
        return applyDao.get(idApply);
    }

    /**
     * 根据状态返回apply列表
     *
     * @param state
     * @return
     */
    public ArrayList<Apply> getApplicationList(int state) {
        List<Apply> arrayList = applyDao.findAll();
        ArrayList<Apply> list = new ArrayList<Apply>();
        for (Apply temp : arrayList) {
            if (temp.getState() == state) {
                list.add(temp);
            }
        }
        return list;
    }

//    public Apply sendApplication(String idApply, Double longitude, Double latitude, String number, String address, Integer postcode, Date applyDate, Integer state, byte[] image){return null;}
//
//    public Apply addApplication(long idApply, Double longitude, Double latitude, String number, String address, Date applyDate, Integer state,String image, String name){return null;}
}
