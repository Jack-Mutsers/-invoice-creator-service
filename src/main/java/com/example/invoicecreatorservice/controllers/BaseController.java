package com.example.invoicecreatorservice.controllers;

import com.example.invoicecreatorservice.helpers.components.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@CrossOrigin
@Controller
public class BaseController {
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    protected int getCompanyId(HttpServletRequest request){
        final String requestTokenHeader = request.getHeader("Authorization").substring(7);
        return jwtTokenUtil.getCompanyFromToken(requestTokenHeader);
    }

    protected int getUserId(HttpServletRequest request){
        final String requestTokenHeader = request.getHeader("Authorization").substring(7);
        return jwtTokenUtil.getUserFromToken(requestTokenHeader);
    }

    protected  <T> Iterable<T> emptyIfNull(Iterable<T> iterable) {
        return iterable == null ? List.of() : iterable;
    }

}
