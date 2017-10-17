package com.csy.util.exception.account;

public class UserNotFoundException extends Exception{

  private static final long serialVersionUID = 1835913655683281662L;

  public UserNotFoundException(){
    super("未找到用户");
  }
  
  public UserNotFoundException(String exceptionMsg){
    super(exceptionMsg);
  }
}
