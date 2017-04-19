package com.csy.module.wx.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.csy.util.wx.Sign;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("wx")
public class SignController {
	
	@RequestMapping("sign")
	@ResponseBody
	public void baseAuthorize(HttpServletRequest request, 
			HttpServletResponse response,String url) throws IOException{
		Map<String, String> map = Sign.sign(url);
		JSONObject object = JSONObject.fromObject(map);
		response.getWriter().println(object);
	}
}
