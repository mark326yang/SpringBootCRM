package com.hqyj.service;

import com.hqyj.MyApp;
import com.hqyj.dao.ServiceLogMapper;
import com.hqyj.pojo.Admin;
import com.hqyj.pojo.Manager;
import com.hqyj.pojo.ServiceLog;
import com.hqyj.service.ServiceLogService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.SimpleFormatter;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MyApp.class)
public class ServiceLogTest {
    //注入ServiceLogService
    @Autowired
    private ServiceLogService serviceLogService;
    //注入
    @Autowired(required = false)
    private ServiceLogMapper serviceLogMapper;
    @Test
    public void add(){
        ServiceLog serviceLog=new ServiceLog();
        serviceLog.setServiceType("上门服务");
        serviceLog.setCustomerName("马云22");
        serviceLog.setEstimateCost(2000.00);
        serviceLog.setServiceStartdate("2021-10-21");
        serviceLog.setServiceEnddate("2021-10-23");
        serviceLog.setServiceContent("上门检修产品，排查危险");
        serviceLog.setServiceState("已检修");
        serviceLog.setManagerId(1);
        HashMap<String,Object> add=serviceLogService.add(serviceLog);
        System.out.println(add.get("info"));
    }

    @Test
    public void select(){
//        HashMap<String,Object> select=serviceLogService.select(new ServiceLog());
//        List<ServiceLog> list=(List<ServiceLog>) select.get("list");
//        for (ServiceLog serviceLog : list) {
//            System.err.println(serviceLog);
//        }

    }

    @Test
    public void update() {
        ServiceLog serviceLog=serviceLogMapper.selectById(7);
        serviceLog.setServiceContent("一晚没有用到一度电");
        HashMap<String, Object> update = serviceLogService.update(serviceLog);
        System.out.println(update.get("info"));
    }

    @Test
    public void del(){
        HashMap<String,Object> del=serviceLogService.del(3);
        System.out.println(del.get("info"));
    }

    @Test
    public void test(){

        String beginTime = "2019-07-28 14:42:32";
        String endTime = "2018-07-29 12:26:32";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        Date date1 = null;
        try {
            date = sdf.parse(beginTime);
            date1 = sdf.parse(endTime);
        } catch (ParseException e){
            e.printStackTrace();
        }

        int num = date.compareTo(date1);
        System.err.println(num);
    }

    @Test
    public void test09(){
      Admin a=new Admin();
      a.setManagerId(18);
      List<ServiceLog> serviceLogs=serviceLogMapper.selectCustomerNameServiceLogByManager(a);
        for (ServiceLog s: serviceLogs
             ) {
            System.out.println(s);
        }
    }
}
