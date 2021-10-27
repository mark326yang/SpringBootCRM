package com.hqyj.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hqyj.dao.CustomerFeedbackMapper;
import com.hqyj.pojo.Admin;
import com.hqyj.pojo.CustomerFeedback;
import com.hqyj.pojo.Manager;
import com.hqyj.pojo.ServiceLog;
import com.hqyj.service.CustomerFeedbackService;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 客户反馈表 服务实现类
 * </p>
 *
 * @author rock
 * @since 2021-10-22
 */
@Service
public class CustomerFeedbackServiceImpl implements CustomerFeedbackService {
    // 注入CustomerFeedbackMapper 接口
    @Autowired(required = false)
    private CustomerFeedbackMapper customerFeedbackMapper;

    @Override
    public HashMap<String, Object> select(CustomerFeedback customerFeedback, HttpSession session) {
        HashMap<String, Object> map = new HashMap<>();
        // 创建分页对象
        Page<CustomerFeedback> page = new Page<>(customerFeedback.getPage(), customerFeedback.getRow());
        // 构建查询条件构造器
        QueryWrapper<CustomerFeedback> queryWrapper = new QueryWrapper<>();
        // 匹配id
        queryWrapper.eq(customerFeedback.getCustomerFeedbackId() != null, "customer_feedback_id", customerFeedback.getCustomerFeedbackId());
        // 匹配客户名
        queryWrapper.like((!StringUtils.isEmpty(customerFeedback.getCustomerName()) && customerFeedback.getCustomerName() != null),
                "customer_name", customerFeedback.getManagerName()
        );
        // 匹配当前客户
        Admin admin;
        admin = (Admin) session.getAttribute("user");
        if (admin.getCustomerId()!=null){
            queryWrapper.eq(true,"customer_id",admin.getCustomerId());
        }

        // 查询 并传入查询条件
        Page<CustomerFeedback> fList = customerFeedbackMapper.selectPage(page, queryWrapper);
        //当前页集合
        map.put("list", fList.getRecords());
        //总页数
        map.put("totalPage", fList.getPages());
        //总条数
        map.put("total", fList.getTotal());
        //当前页
        map.put("curPage", customerFeedback.getPage());
        if (customerFeedback.getPage() == 1) {
            //上一页
            map.put("prePage", 1);

        } else {
            map.put("prePage", customerFeedback.getPage() - 1);

        }
        if (customerFeedback.getPage() == fList.getPages()) {
            //下一页
            map.put("nextPage", fList.getPages());

        } else {
            map.put("nextPage", customerFeedback.getPage() + 1);

        }
        //每页显示条数
        map.put("row", customerFeedback.getRow());


        return map;
    }

    @Override
    public HashMap<String, Object> add(CustomerFeedback customerFeedback) {
        HashMap<String, Object> map = new HashMap<>();
        // 新增反馈记录
        int num = customerFeedbackMapper.insert(customerFeedback);
        if (num > 0){
            map.put("info","保存成功");
        } else {
            map.put("info","保存失败");
        }
        return map;
    }

    @Override
    public HashMap<String, Object> addPage(CustomerFeedback customerFeedback, HttpSession session) {
        HashMap<String, Object> map = new HashMap<>();
        Admin admin = new Admin();
        admin = (Admin) session.getAttribute("user");

        customerFeedback = customerFeedbackMapper.selectCustomerAndManagerByAdminSysName(admin);
        List<ServiceLog> serviceLogList = customerFeedbackMapper.selectServiceLogByCustomerFeedback(customerFeedback);
        if (serviceLogList.size() > 0){
            map.put("info","成功");
            map.put("feedbackAdd",customerFeedback);
        } else {
            map.put("info","失败");
            map.put("feedbackAdd",new CustomerFeedback());
        }
        return map;
    }

    @Override
    public void excelFeedback(HttpServletResponse response) {
        OutputStream output = null;
        try {
            // 创建HSSFWorkbook对象
            HSSFWorkbook wb = new HSSFWorkbook();
            // 创建HSSFSheet对象
            HSSFSheet sheet = wb.createSheet();
            // 创建HSSFRow对象,先写入列名
            HSSFRow colName = sheet.createRow(0);
            // 写入 第一列的列名
            colName.createCell(0).setCellValue("编号");
            colName.createCell(1).setCellValue("客户姓名");
            colName.createCell(2).setCellValue("经理姓名");
            colName.createCell(3).setCellValue("满意度");
            colName.createCell(4).setCellValue("反馈内容");

            // 查询所有反馈信息
            List<CustomerFeedback> customerFeedbackList = customerFeedbackMapper.selectList(null);

            for (int i = 1; i <= customerFeedbackList.size(); i++) {
                // 从第二行开始写入数据
                HSSFRow row = sheet.createRow(i);
                // 编号
                row.createCell(0).setCellValue(customerFeedbackList.get(i-1).getCustomerFeedbackId());
                // 客户姓名
                row.createCell(1).setCellValue(customerFeedbackList.get(i-1).getCustomerName());
                // 经理姓名
                row.createCell(2).setCellValue(customerFeedbackList.get(i-1).getManagerName());
                // 满意度
                row.createCell(3).setCellValue(customerFeedbackList.get(i-1).getCustomerStatisfaction());
                // 反馈内容
                row.createCell(4).setCellValue(customerFeedbackList.get(i-1).getCustomerFeedback());

            }

            // 输出Excel 文件到页面
            output = response.getOutputStream();
            String fileName = "客户反馈表";
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
