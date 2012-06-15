/*
 * Copyright 2009 Red Hat, Inc.
 *
 * Red Hat licenses this file to you under the Apache License, version 2.0
 * (the "License"); you may not use this file except in compliance with the
 * License.  You may obtain a copy of the License at:
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package com.dianping.swallow.consumerserver.netty2;

import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;

import com.dianping.swallow.common.example.message.DemoBean;
import com.dianping.swallow.common.message.BeanMessage;
import com.dianping.swallow.common.message.ByteMessage;
import com.dianping.swallow.common.message.TextMessage;

@SuppressWarnings("unused")
public class EchoServerHandler extends SimpleChannelUpstreamHandler {

   @Override
   public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) {
      //服务器发送消息

      //1 测试TextMessage
      TextMessage m = new TextMessage();
      m.setContent("这是一则来自服务器的测试消息。");

      //2 测试BeanMessage
      //      BeanMessage m = new BeanMessage();
      //      DemoBean demoBean = new DemoBean();
      //      demoBean.setA(22);
      //      demoBean.setB("hello");
      //      m.writeBeanAsJsonString(demoBean);

      //3 测试ByteMessage
      //      ByteMessage m = new ByteMessage();
      //      m.setContent(new byte[] { 1, 2, 3, 4, 5 });

      e.getChannel().write(m);
   }

}