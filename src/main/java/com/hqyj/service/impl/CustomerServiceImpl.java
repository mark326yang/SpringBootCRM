package com.hqyj.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hqyj.dao.CustomerMapper;
import com.hqyj.pojo.Admin;
import com.hqyj.pojo.Customer;
import com.hqyj.pojo.Manager;
import com.hqyj.service.CustomerService;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.thymeleaf.util.DateUtils;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author zyh
 * @version 1.0
 * @items
 * @Date:on 2021/10/22 at 10:42
 */

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    JavaMailSender javaMailSender;


    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerMapper customerMapper;


    @Override
    public HashMap<String, Object> selectCustomer(Customer c, HttpSession session) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        //定义分页对象
        Page<Customer> page=new Page<>(c.getPage(),c.getRow());
        QueryWrapper<Customer> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(c.getCustomerId()!=null,"customer_id",c.getCustomerId());
        queryWrapper.like((!StringUtils.isEmpty(c.getCustomerName())&&c.getCustomerName()!=null),
                "customer",c.getCustomerName());

        Admin admin;
        admin=(Admin)session.getAttribute("user");
        Integer managerId = admin.getManagerId();
        if (managerId!=null){
            queryWrapper.eq(true,"manager_id",managerId);
        }

        //查询数据库
        Page<Customer> cList= customerMapper.selectPage(page,queryWrapper);
        //当前页集合
        map.put("list",cList.getRecords());
        //总页数
        map.put("totalPage",cList.getPages());
        //总条数
        map.put("tatal",cList.getTotal());
        //当前页
        map.put("curPage",c.getPage());
        if (c.getPage()==1){
            //上一页
            map.put("prePage",1);

        }else {
            map.put("prePage",c.getPage()-1);

        }
        if (c.getPage()==cList.getPages()){
            //下一页
            map.put("nextPage",cList.getPages());

        }else {
            map.put("nextPage",c.getPage()+1);
        }
        //每页显示条数
        map.put("row",c.getRow());

        return map;
    }
    @Override
    public HashMap<String, Object> addPage(Customer customer, HttpSession session) {
        HashMap<String, Object> map = new HashMap<>();
        Manager manager = new Manager();
        Admin admin;
        admin = (Admin) session.getAttribute("user");
        manager.setManagerId(admin.getManagerId());
        customer.setManagerId(admin.getManagerId());
        if (admin != null){
            map.put("info","成功");
            map.put("customerAdd",customer);
        } else {
            map.put("info","失败");
            map.put("customerAdd",new Customer());
        }
        return map;
    }

    @Override
    public HashMap<String, Object> add(Customer customer) {
        HashMap<String ,Object> map=new HashMap<>();
        //新增
        int num=customerMapper.insert(customer);
        if (num>0){
            map.put("info","保存成功");

        }else {
            map.put("info","保存失败");
        }
        return map;
    }

    @Override
    public HashMap<String, Object> update(Customer customer) {
        HashMap<String,Object> map=new HashMap<>();
        int num = customerMapper.updateById(customer);
        if (num>0){
            map.put("info","保存成功");
        }else{
            map.put("info","保存失败");

        }
        return map;
    }

    @Override
    public HashMap<String, Object> del(Integer customerId) {
        //删除
        HashMap<String, Object> map = new HashMap<>();
        int num = customerMapper.deleteById(customerId);
        if (num>0){
            map.put("info","保存成功");
        }else{
            map.put("info","保存失败");

        }
        return map;
    }

    /*private String formatExcelDate(String day) {
        Calendar calendar = new GregorianCalendar(1900,0,-1);
        Date gregorianDate = calendar.getTime();
        String formatExcelDate = DateUtils.format(DateUtils.addDay(gregorianDate,day), DateUtils.YYYYMMDD);
        return formatExcelDate;
    }*/
    @Override
    public void excelCustomer(HttpServletResponse response) {

        OutputStream output = null;

        try {

            //创建HSSFWorkbook对象
            HSSFWorkbook wb = new HSSFWorkbook();
            //创建HSSFSheet对象
            HSSFSheet sheet = wb.createSheet();

            //创建HSSFRow对象，先写入列
            HSSFRow rowName = sheet.createRow(0);
            //写入 第一列的列明
            rowName.createCell(0).setCellValue("编号");
            rowName.createCell(1).setCellValue("姓名");
            rowName.createCell(2).setCellValue("性别");
            rowName.createCell(3).setCellValue("生日");
            rowName.createCell(4).setCellValue("电话");
            rowName.createCell(5).setCellValue("邮箱");
            rowName.createCell(6).setCellValue("学历");
            rowName.createCell(7).setCellValue("身份证号");
            rowName.createCell(8).setCellValue("单位");
            rowName.createCell(9).setCellValue("联系人");
            rowName.createCell(10).setCellValue("备注");
            rowName.createCell(11).setCellValue("经理编号");


            //查询所有用户信息
            List<Customer> customerList = customerMapper.selectList(null);






            //从第二行开始写入数据
            for (int i = 1; i <= customerList.size(); i++) {

                HSSFRow row = sheet.createRow(i);
                //编号
                row.createCell(0).setCellValue(customerList.get(i - 1).getCustomerId());
                //姓名
                row.createCell(1).setCellValue(customerList.get(i - 1).getCustomerName());
                //性别
                row.createCell(2).setCellValue(customerList.get(i - 1).getCustomerSex());
                //生日
                /**
                 * 需要做一个数字时间转换，导入Excel里的时间被转换成里从1900年到某人生日的天数，需要使用simpledateformat格式化一下
                 */
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String birthday = sdf.format(customerList.get(i - 1).getCustomerBirthday());
                row.createCell(3).setCellValue(birthday);
                //联系人电话
                row.createCell(4).setCellValue(customerList.get(i - 1).getCustomerPhone());
                //邮箱
                row.createCell(5).setCellValue(customerList.get(i - 1).getCustomerEmail());
                //学历
                row.createCell(6).setCellValue(customerList.get(i - 1).getCustomerEducation());
                //身份证
                row.createCell(7).setCellValue(customerList.get(i - 1).getCustomerIdnumber());
                //单位
                row.createCell(8).setCellValue(customerList.get(i - 1).getCustomerUnit());
                //联系人
                row.createCell(9).setCellValue(customerList.get(i - 1).getCustomerLinkman());
                //备注
                row.createCell(10).setCellValue(customerList.get(i - 1).getComments());
                //经理编号
                row.createCell(11).setCellValue(customerList.get(i - 1).getManagerId());


            }

            //输出Excel文件到页面
            output = response.getOutputStream();
            String fileName = "客户表";
            //解决中文名下载，编程下滑问题
            fileName = new String(fileName.getBytes("utf-8"), "ISO8859-1") + "";
            response.reset();
            ;
            response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xls");
            response.setContentType("application/msexcel");
            wb.write(output);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //释放资源
            try {
                output.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    @Override
    public HashMap<String,Object> selectBirthday(Customer customer) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        //定义分页对象
        Page<Customer> page=new Page<>(customer.getPage(),customer.getRow());

        //条件构造器
        QueryWrapper<Customer> queryWrapper = new QueryWrapper<>();

        //获取当前时间
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String format = sdf.format(c.getTime());
        //截取特定日期
        String creTime = format.substring(5, 10);

            queryWrapper.eq(customer.getCustomerId()!=null,"customer_id",customer.getCustomerId());
            queryWrapper.like((!StringUtils.isEmpty(customer.getCustomerName())&&customer.getCustomerName()!=null),
                    "customer",customer.getCustomerName());
            queryWrapper.like(true,"customer_birthday",creTime);
            //查询数据库
            Page<Customer> cList= customerMapper.selectPage(page,queryWrapper);
            //当前页集合
            map.put("list",cList.getRecords());
            //总页数
            map.put("totalPage",cList.getPages());
            //总条数
            map.put("total",cList.getTotal());
            //当前页
            map.put("curPage",customer.getPage());
            if (customer.getPage()==1){
                //上一页
                map.put("prePage",1);

            }else {
                map.put("prePage",customer.getPage()-1);

            }
            if (customer.getPage()==cList.getPages()){
                //下一页
                map.put("nextPage",cList.getPages());

            }else {
                map.put("nextPage",customer.getPage()+1);
            }
            //每页显示条数
            map.put("row",customer.getRow());

        return  map;


    }

    //@Scheduled(fixedRate = 6000)
    public boolean SendEmail(String email){
        try{
            String sender="1942355562@qq.com";
            String toUser=email;
            String subject="生日祝福";
            String text="祝您生日快乐,生活愉快";
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom(sender);
            mailMessage.setTo(email);
            mailMessage.setSubject(subject);
            mailMessage.setText(text);

            javaMailSender.send(mailMessage);

            System.out.println("邮箱发送成功");
            return true;
        }catch (Exception e){

            e.printStackTrace();
            System.out.println("邮箱发送失败");

        }
        return false;


    }




}
