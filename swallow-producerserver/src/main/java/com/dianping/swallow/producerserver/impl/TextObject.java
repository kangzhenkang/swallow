/**
 * Project: swallow-producerserver
 * 
 * File Created at 2012-6-25
 * $Id$
 * 
 * Copyright 2010 dianping.com.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Dianping Company. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with dianping.com.
 */
package com.dianping.swallow.producerserver.impl;


/**
 * TODO Comment of TextObject
 * 
 * @author tong.song
 */
public class TextObject {
   private String  topic;
   private String  content;
   private boolean isACK;

   public String getTopic() {
      return topic;
   }

   public void setTopic(String topic) {
      this.topic = topic;
   }

   public String getContent() {
      return content;
   }

   public void setContent(String content) {
      this.content = content;
   }

   public boolean isACK() {
      return isACK;
   }

   public void setACK(boolean isACK) {
      this.isACK = isACK;
   }

   @Override
   public String toString() {
      return getTopic() + "\t" + getContent() + "\t" + isACK;
   }
}