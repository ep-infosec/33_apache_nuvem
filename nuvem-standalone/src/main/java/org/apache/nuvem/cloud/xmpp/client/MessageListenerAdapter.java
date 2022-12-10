/*
 * Licensed to the Apache Software Foundation (ASF) under one

 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.apache.nuvem.cloud.xmpp.client;

import org.apache.nuvem.cloud.xmpp.message.MessageBuilder;
import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.MessageListener;
import org.jivesoftware.smack.packet.Message;

/**
 * Message Listener to listen to smack api and adapt it to nuvem specific
 * messages.
 * 
 */
public class MessageListenerAdapter implements MessageListener {

	/**
	 * The Nuvem Listener.
	 */
	private org.apache.nuvem.cloud.xmpp.message.MessageListener nuvemListener;

	public MessageListenerAdapter(
			org.apache.nuvem.cloud.xmpp.message.MessageListener nuvemMessageListener) {
		if (nuvemMessageListener == null)
			throw new IllegalArgumentException("listener should not be null");
		this.nuvemListener = nuvemMessageListener;
	}

	@Override
	public void processMessage(Chat chat, Message message) {
		nuvemListener.listen(new MessageBuilder().from(message.getFrom())
				.containing(message.getBody()).build());
	}

}
