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

import org.apache.nuvem.cloud.xmpp.JID;
import org.apache.nuvem.cloud.xmpp.XMPPEndPoint;
import org.apache.nuvem.cloud.xmpp.message.MessageListener;
import org.jivesoftware.smack.PacketListener;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Packet;

/**
 * Listens to Packets from XMPP Server and transfers them to the appropriate
 * Listener registered with the XMPPEndPoint.
 * 
 */
public class NuvemPacketListener implements PacketListener {

	private XMPPEndPoint endPoint;

	public NuvemPacketListener(XMPPEndPoint endPoint) {
		this.endPoint = endPoint;
	}

	@Override
	public void processPacket(Packet packet) {
		if (packet instanceof Message) {
			MessageListener listener = endPoint
					.getListenerFor(new JID(packet.getFrom()));
			listener.listen(MessageAdapter
					.toNuvemMessage((Message) packet));
		}
	}

}
