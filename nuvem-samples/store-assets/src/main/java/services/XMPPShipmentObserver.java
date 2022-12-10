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

package services;

import org.apache.nuvem.cloud.xmpp.JID;
import org.apache.nuvem.cloud.xmpp.Status;
import org.apache.nuvem.cloud.xmpp.XMPPEndPoint;
import org.apache.nuvem.cloud.xmpp.message.MessageBuilder;
import org.apache.nuvem.cloud.xmpp.presence.Presence;
import org.apache.nuvem.cloud.xmpp.presence.PresenceBuilder;
import org.apache.nuvem.cloud.xmpp.presence.PresenceListener;
import org.oasisopen.sca.annotation.Init;
import org.oasisopen.sca.annotation.Reference;
import org.oasisopen.sca.annotation.Scope;

@Scope("COMPOSITE")
public class XMPPShipmentObserver implements ShipmentObserver {

	@Reference
	private XMPPEndPoint endpoint;

	private static final String message = "Welcome to Nuvem Store";

	@Init
	public void init() {
		endpoint.presenceManager().registerListener(new Greeter(endpoint));
	}

	public void onStatusUpdate(Shipment shipment, Event event) {
		JID jid = shipment.getJidToUpdate();
		System.out.println("inviting user of jid : " + jid.asString());
		Status status = endpoint.invite(jid.asString());
		System.out.println("status of invite:" + status.hasErrors());
		if (!status.hasErrors()) {
			endpoint.sendMessage(new MessageBuilder()
					.toRecipient(shipment.getJidToUpdate().asString())
					.containing(event.toString()).build());
		}
	}

	public static class Greeter implements PresenceListener {
		private XMPPEndPoint xmppEndPoint;

		public Greeter(XMPPEndPoint endPoint) {
			this.xmppEndPoint = endPoint;
		}

		@Override
		public void listen(Presence presence) {
			if (presence.type() == Presence.Type.AVAILABLE) {
				PresenceBuilder builder = new PresenceBuilder()
						.to(presence.from()).withStatus(message)
						.withType(Presence.Type.AVAILABLE);
				xmppEndPoint.presenceManager().sendPresence(builder.build());
			}
		}

	}

}
