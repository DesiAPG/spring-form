package com.example.springform.app.interceptors;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.Random;

@Component("timeElapsedInterceptor")
public class TimeElapsedIntercept implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(TimeElapsedIntercept.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (request.getMethod().equalsIgnoreCase("post")) {
            return true;
        }

        logger.info("TimerIntercept : preHandle() entry");
        long initTime = System.currentTimeMillis();
        request.setAttribute("initTime", initTime);
        Random random = new Random();
        int delay = random.nextInt(500);
        Thread.sleep(delay);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if (request.getMethod().equalsIgnoreCase("post")) {
            return;
        }
        long finishTime = System.currentTimeMillis();
        long initTime = (Long) request.getAttribute("initTime");
        long timeElapsed = finishTime - initTime;
        if (modelAndView != null) {
            modelAndView.addObject("timeElapsed", timeElapsed);
        }
        logger.info("Time Elapsed " + timeElapsed + " milliseconds");
        logger.info("TimerIntercept : postHandle() exit");

    }
}
