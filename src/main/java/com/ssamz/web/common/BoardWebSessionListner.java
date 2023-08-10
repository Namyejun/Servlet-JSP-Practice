package com.ssamz.web.common;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

@WebListener
public class BoardWebSessionListner implements HttpSessionAttributeListener {
    public BoardWebSessionListner() {
    	System.out.println("===> BoardWebSessionListner 생성");
    }
    public void attributeAdded(HttpSessionBindingEvent se)  { 
    	System.out.println("---> 세션에 정보를 등록함");
    }
    public void attributeRemoved(HttpSessionBindingEvent se)  { 
    	System.out.println("---> 세션에 등록된 정보가 삭제됨");
    }
    public void attributeReplaced(HttpSessionBindingEvent se)  { 
    	System.out.println("---> 세션에 등록된 정보를 덮어씀");
    }
	
}
