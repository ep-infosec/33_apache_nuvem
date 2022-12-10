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

package org.apache.nuvem.cloud.xmpp;

import java.io.Serializable;

/**
 * Contains the error details used.
 */
public final class Error implements Serializable {

    /**
	 * serial id.
	 */
	private static final long serialVersionUID = 234776753829641455L;

	/**
     * Description of the error.
     */
    private String description;

    /**
     * The actual error code.
     */
    private ErrorCode code;

    public Error(ErrorCode code) {
        this.code = code;
    }

    public Error(ErrorCode code, String description) {
        this.code = code;
        this.description = description;
    }

    public String description() {
        return description;
    }

    public ErrorCode code() {
        return code;
    }

}
