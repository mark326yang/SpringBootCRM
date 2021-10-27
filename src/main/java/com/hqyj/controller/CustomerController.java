package com.hqyj.controller;

        import com.hqyj.pojo.Customer;
        import com.hqyj.pojo.Manager;
        import com.hqyj.pojo.ServiceLog;
        import com.hqyj.service.CustomerService;
        import org.apache.shiro.web.session.HttpServletSession;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.mail.SimpleMailMessage;
        import org.springframework.mail.javamail.JavaMailSender;
        import org.springframework.web.bind.annotation.RequestMapping;
        import org.springframework.web.bind.annotation.RestController;

        import javax.servlet.http.HttpSession;
        import java.util.Date;
        import java.util.HashMap;
        import java.util.List;

/**
 * @author zyh
 * @version 1.0
 * @items
 * @Date:on 2021/10/22 at 10:57
 */

@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    JavaMailSender javaMailSender;

    @Autowired(required = false)
    private Customer customer;

    @Autowired
    private CustomerService customerService;


    //加载表格数据
    @RequestMapping(value = "/list")
        public  HashMap<String,Object> listPage(Customer c,HttpSession session){



       return customerService.selectCustomer(c,session);


    }
    @RequestMapping(value = "/add")
    public HashMap<String,Object> add(Customer customer){

        return customerService.add(customer);
    }

    @RequestMapping(value = "/update")
    public HashMap<String ,Object> update(Customer customer){
        return customerService.update(customer);
    }

    @RequestMapping("/del")
    public HashMap<String ,Object> del(Integer customerId){
        return customerService.del(customerId);
    }

    @RequestMapping("/birthday")
    public HashMap<String,Object> selectBirthday(Customer customer){

        return customerService.selectBirthday(customer);
    }

    @RequestMapping("/send")
    public boolean sendEmail(String customerEmail){

       return customerService.SendEmail(customerEmail);
    }

    @RequestMapping(value = "/addPage")
    public HashMap<String,Object> addPage(Customer customer, HttpSession session){
        return customerService.addPage(customer,session);
    }





   /* public String send(){
        List<Customer> customerList=customerService.selectCustomerByCustomerBirthday();

        for (Customer customer : customerList) {
            Date date = new Date();
            //如果当前时间与客户生日相等，就发送
            if (date.equals(customer.getCustomerBirthday())){
                SimpleMailMessage ssm = new SimpleMailMessage();
                ssm.setFrom("1942355562@qq.com");
                ssm.setSubject("祝"+customer.getCustomerName()+"生日快乐");
                ssm.setText("祝"+customer.getCustomerName()+constructSentence(customer.getCustomerName(),customer.getCustomerSex())+"生日快乐");
                ssm.setTo(customer.getCustomerEmail());

                try{
                    javaMailSender.send(ssm);
                    return "发送成功";
                }catch (Exception e){
                    return "发送失败///"+e.getMessage();
                }

            }
        }

        return "发送结束";

    }



    private String constructSentence(String name,String sex) {
        StringBuffer sb=new StringBuffer();
        if (sex.equals("女")){
            sb.append("小姐姐");
        }else{
            sb.append("小哥哥");
        }
        return sb.toString();
    }

*/
}
