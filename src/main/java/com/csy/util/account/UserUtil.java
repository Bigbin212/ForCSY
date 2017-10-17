package com.csy.util.account;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.csy.module.user.entity.BUserAccount;
import com.csy.util.exception.account.UserNotFoundException;

/**
 * 用户操作工具类
 * @author wangqiang
 */
public class UserUtil{

  public static String getAccountByHttpRequest(HttpServletRequest request) throws UserNotFoundException{

    HttpSession session = request.getSession(true);

    BUserAccount account = (BUserAccount) session.getAttribute("user");

    if(account == null || account.getAccount() == null){
      throw new UserNotFoundException("未找到用户登录信息");
    }else{
      return account.getAccount();
    }
  }

  public static String getIpByHttpRequest(HttpServletRequest request){
    String ip = request.getHeader("x-forwarded-for");
    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
      ip = request.getHeader("Proxy-Client-IP");
    }
    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){

      ip = request.getHeader("WL-Proxy-Client-IP");
    }
    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
      ip = request.getRemoteAddr();
    }
    return ip;
  }
}
