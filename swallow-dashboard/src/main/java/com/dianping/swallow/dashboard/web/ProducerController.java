package com.dianping.swallow.dashboard.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dianping.swallow.common.message.Destination;
import com.dianping.swallow.common.producer.exceptions.RemoteServiceInitFailedException;
import com.dianping.swallow.common.producer.exceptions.SendFailedException;
import com.dianping.swallow.producer.Producer;
import com.dianping.swallow.producer.ProducerConfig;
import com.dianping.swallow.producer.ProducerMode;
import com.dianping.swallow.producer.impl.ProducerFactoryImpl;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.google.gson.Gson;

@Controller
public class ProducerController {

   private Map<String, Producer> producers = new ConcurrentHashMap<String, Producer>();
   private ProducerConfig        config    = new ProducerConfig();
   {
      config.setMode(ProducerMode.SYNC_MODE);
   }

   @RequestMapping(value = "/producer")
   public String dev() {
      return "index";
   }

   @RequestMapping(value = "/producer/sendMsg", method = RequestMethod.GET, produces = "application/javascript; charset=utf-8")
   @ResponseBody
   public Object config(String topic, String content, String callback) throws JsonGenerationException,
         JsonMappingException, IOException {
      Map<String, Object> map = new HashMap<String, Object>();
      try {
         Producer producer = producers.get(topic);
         if (producer == null) {
            producer = ProducerFactoryImpl.getInstance().createProducer(Destination.topic(topic), config);
         }
         producer.sendMessage(content);
         map.put("success", true);

      } catch (SendFailedException e) {
         StringBuilder error = new StringBuilder();
         error.append(e.getMessage());
         for (StackTraceElement element : e.getStackTrace()) {
            error.append(element.toString()).append("\n");
         }
         map.put("success", false);
         map.put("errorMsg", error.toString());
      } catch (RemoteServiceInitFailedException e) {
         StringBuilder error = new StringBuilder();
         error.append(e.getMessage());
         for (StackTraceElement element : e.getStackTrace()) {
            error.append(element.toString()).append("\n");
         }
         map.put("success", false);
         map.put("errorMsg", error.toString());
      } catch(RuntimeException e){
         StringBuilder error = new StringBuilder();
         error.append(e.getMessage());
         for (StackTraceElement element : e.getStackTrace()) {
            error.append(element.toString()).append("\n");
         }
         map.put("success", false);
//         map.put("errorMsg", error.toString());
         map.put("errorMsg", error.toString());
      }
      Gson gson = new Gson();
      return callback + "(" + gson.toJson(map) + ");";

   }

}