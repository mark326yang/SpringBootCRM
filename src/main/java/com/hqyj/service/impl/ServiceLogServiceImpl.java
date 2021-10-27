package com.hqyj.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hqyj.pojo.Admin;
import com.hqyj.pojo.CustomerFeedback;
import com.hqyj.pojo.Manager;
import com.hqyj.pojo.ServiceLog;
import com.hqyj.dao.ServiceLogMapper;
import com.hqyj.service.ServiceLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.crypto.Data;
import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**
 * <p>
 * 服务记录表 服务实现类
 * </p>
 *
 * @author rock
 * @since 2021-10-21
 */
@Service
public class ServiceLogServiceImpl extends ServiceImpl<ServiceLogMapper, ServiceLog> implements ServiceLogService {
    //注入mapper
    @Autowired(required = false)
    ServiceLogMapper serviceLogMapper;
    @Override
    public HashMap<String, Object> select(ServiceLog s, HttpSession session) {
        HashMap<String,Object> map=new HashMap<String, Object>();
        //定义分页对象
        Page<ServiceLog> page=new Page<>(s.getPage(),s.getRow());
        /*-------------mybatit plus的分页条件查询 -------------- */
        //查询分页
        QueryWrapper<ServiceLog> queryWrapper=new QueryWrapper<>();
        //编号查询
        queryWrapper.eq(s.getServiceId()!=null,"service_id",s.getServiceId());
        //客户名的模糊查询
        queryWrapper.like(!StringUtils.isEmpty(s.getCustomerName())&&s.getCustomerName()!=null,"customer_name",s.getCustomerName());
        Admin admin;
        admin = (Admin) session.getAttribute("user");
        if (admin.getManagerId()!=null) {
            queryWrapper.eq(true, "manager_id", admin.getManagerId());
        }
        Page<ServiceLog> sList=serviceLogMapper.selectPage(page,queryWrapper);

        /*-------------自定义的的分页条件查询 -------------- */
        //Page<Admin> aList = adminMapper.selectByCon(page,a);


        //当前页集合
        map.put("list",sList.getRecords());
        //总页数
        map.put("totalPage",sList.getPages());
        //总条数
        map.put("total",sList.getTotal());
        //当前页
        map.put("curPage",s.getPage());
        if(s.getPage()==1){
            //上一页
            map.put("prePage",1);
        }else {
            //上一页
            map.put("prePage",s.getPage()-1);
        }
        if(s.getPage()==sList.getPages()){
            //下一页
            map.put("nextPage",sList.getPages());
        }else {
            //下一页
            map.put("nextPage", s.getPage() + 1);
        }
        //每页显示条数
        map.put("row",s.getRow());
        return map;
    }

    @Override
    public HashMap<String, Object> add(ServiceLog s) {
        HashMap<String, Object> map = new HashMap<>();
        System.err.println(s);
        Date startTime = s.getServiceStartdate();
        Date endTime = s.getServiceEnddate();

        if (startTime.getTime()>endTime.getTime()){
            System.out.println("结束日期和开始日期冲突");
            map.put("info", "保存失败");
        } else {
            // 新增
            int num = serviceLogMapper.insert(s);
            if (num > 0) {
                map.put("info", "保存成功");
            } else {
                map.put("info", "保存失败");
            }
        }
            return map;

    }

    @Override
    public HashMap<String, Object> del(Integer serviceId) {
        HashMap<String, Object> map = new HashMap<>();
        // 删除
        int num = serviceLogMapper.deleteById(serviceId);
        if (num > 0){
            map.put("info","保存成功");
        } else {
            map.put("info","保存失败");
        }
        return map;
    }

    @Override
    public HashMap<String, Object> addPage(ServiceLog serviceLog, HttpSession session) {
        HashMap<String, Object> map = new HashMap<>();
        Admin admin = new Admin();
//        admin.setManagerId(serviceLog.getManagerId());
//        admin.setManagerId(18);
        admin = (Admin) session.getAttribute("user");
        /*serviceLog= serviceLogMapper.selectCustomerNameServiceLogByManager(admin);*/
        List<ServiceLog> serviceLogAdd = serviceLogMapper.selectCustomerNameServiceLogByManager(admin);
        if (serviceLogAdd.size() > 0){
            map.put("info","成功");
            map.put("serviceLogAdd",serviceLogAdd);
        } else {
            map.put("info","失败");
            map.put("serviceLogAdd",new ServiceLog());
        }
        return map;
    }
    @Override
    public HashMap<String,Object> update(ServiceLog s){
        HashMap<String,Object> map=new HashMap<>();
        Date startTime = s.getServiceStartdate();
        Date endTime = s.getServiceEnddate();


        if (startTime.getTime()>endTime.getTime()){
            System.out.println("结束日期和开始日期冲突");
            map.put("info", "保存失败");
        } else {
            //修改
            int num = serviceLogMapper.updateById(s);
            if (num > 0) {
                map.put("info", "保存成功");
            } else {
                map.put("info", "保存错误");
            }
        }

        return map;
    }


    @Override
    public void excelServiceLog(HttpServletResponse response) {
        OutputStream output = null;
        try {
            // 创建HSSFWorkbook对象
            HSSFWorkbook wb = new HSSFWorkbook();
            // 创建HSSFSheet对象
            HSSFSheet sheet = wb.createSheet();
            // 创建HSSFRow对象,先写入列名
            HSSFRow colName = sheet.createRow(0);
            // 写入 第一列的列名
            colName.createCell(0).setCellValue("服务编号");
            colName.createCell(1).setCellValue("服务类型");
            colName.createCell(2).setCellValue("客户姓名");
            colName.createCell(3).setCellValue("服务预估成本");
            colName.createCell(4).setCellValue("服务开始日期");
            colName.createCell(5).setCellValue("服务结束日期");
            colName.createCell(6).setCellValue("服务类容描述");
            colName.createCell(7).setCellValue("服务状态");
            colName.createCell(8).setCellValue("经理编号");
            // 查询所有服务信息
            List<ServiceLog> sList =serviceLogMapper.selectList(null);

            for (int i = 1; i < sList.size(); i++) {
                // 从第二行开始写入数据
                HSSFRow row = sheet.createRow(i);
                // 服务编号
                row.createCell(0).setCellValue(sList.get(i-1).getServiceId());
                // 服务类型
                row.createCell(1).setCellValue(sList.get(i-1).getServiceType());
                // 客户姓名
                row.createCell(2).setCellValue(sList.get(i-1).getCustomerName());
                // 服务预估成本
                row.createCell(3).setCellValue(sList.get(i-1).getEstimateCost());
                // 服务开始日期
                row.createCell(4).setCellValue(sList.get(i-1).getServiceStartdate());
                // 服务结束日期
                row.createCell(5).setCellValue(sList.get(i-1).getServiceEnddate());
                // 服务类容描述
                row.createCell(6).setCellValue(sList.get(i-1).getServiceContent());
                // 服务状态
                row.createCell(7).setCellValue(sList.get(i-1).getServiceState());
                // 经理编号
                row.createCell(8).setCellValue(sList.get(i-1).getManagerId());
            }

            // 输出Excel 文件到页面
            output = response.getOutputStream();
            String fileName = "服务记录表";
            // 解决中文文件名下载 编程下滑线的问题
            fileName = new String(fileName.getBytes("utf-8"),"ISO8859-1") + "";
            response.reset();
            response.setHeader("Content-disposition", "attachment; filename="+fileName+".xls");
            response.setContentType("application/msexcel");
            wb.write(output);

        } catch (Exception e){
            e.printStackTrace();
        } finally {
            try {
                // 关闭流对象
                output.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
