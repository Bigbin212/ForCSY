package com.csy.util.log;

import javax.servlet.http.HttpServletRequest;

import com.csy.module.xtpz.dao.BQjLogMapper;
import com.csy.module.xtpz.entity.BQjLog;
import com.csy.util.SpringFactory;
import com.csy.util.account.UserUtil;
import com.csy.util.exception.account.UserNotFoundException;

/**
 * 日志接口类
 * @author wangqiang
 */
public class LogUtil{
  
  private static final BQjLogMapper _log = SpringFactory.getBean("BQjLogMapper");

  /**
   * 
   * 日志插入函数，通过request系统会自动获取IP以及操作用户，如果无法获取会抛出异常
   * @author wangqiang
   * @param request      http请求
   * @param model        模块类型代码项值
   * @param operType     操作类型代码项值
   * @param operRst      操作结果代码项值
   * @param description  操作描述
   *                     <p>
   *                       <b>要求：根据操作描述能确定唯一记录的情况下尽可能表达得清楚</b><br/>
   *                       <ul>
   *                        <li>审核了记录:*****</li>
   *                        <li>删除了账户:ABC</li>
   *                       </ul>
   *                     </p>
   * @throws UserNotFoundException <p>根据{@link javax.servlet.http.HttpServletRequest HttpServletRequest}无法获取用户信息时将抛出异常</p>
   */
  public static void insert(HttpServletRequest request, String model, String operType, String operRst,
      String description) throws UserNotFoundException{
    
    String account = UserUtil.getAccountByHttpRequest(request);
    
    String ip = UserUtil.getIpByHttpRequest(request);
    
    insert(account, ip, model, operType, operRst, description);
  }
  
  /**
   * 
   * 日志插入函数
   * @author wangqiang
   * @param account      操作账户
   * @param ip           操作ip
   * @param model        模块类型代码项值
   * @param operType     操作类型代码项值
   * @param operRst      操作结果代码项值
   * @param description  操作描述
   *                     <p>
   *                       <b>要求：根据操作描述能确定唯一记录的情况下尽可能表达得清楚</b><br/>
   *                       <ul>
   *                        <li>审核了记录:*****</li>
   *                        <li>删除了账户:ABC</li>
   *                       </ul>
   *                     </p>
   */
  public static void insert(String account, String ip, String model, String operType, String operRst,
      String description){
    BQjLog log = new BQjLog();
    log.setfAccount(account);
    log.setIp(ip);
    log.setModel(model);
    log.setOperRst(operRst);
    log.setOperType(operType);
    log.setDescription(description);
    _log.insertSelective(log);
  }
  
}
