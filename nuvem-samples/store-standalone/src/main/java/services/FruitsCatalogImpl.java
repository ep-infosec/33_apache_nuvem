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

import java.util.ArrayList;
import java.util.List;

import org.oasisopen.sca.annotation.Init;
import org.oasisopen.sca.annotation.Property;
import org.oasisopen.sca.annotation.Reference;
import org.oasisopen.sca.annotation.Scope;
import org.oasisopen.sca.annotation.Service;

@Service(Catalog.class)
@Scope("COMPOSITE")
public class FruitsCatalogImpl implements Catalog {
    @Property
    public String currencyCode = "USD";

    @Reference
    public CurrencyConverter currencyConverter;

    private List<Item> catalog = new ArrayList<Item>();

    @Init
    public void init() {
        String currencySymbol = currencyConverter.getCurrencySymbol(currencyCode);
        catalog.add(new Item("Acai", currencyCode, currencySymbol, currencyConverter.getConversion("USD",
                                                                                                   currencyCode,
                                                                                                   2.99)));
        catalog.add(new Item("Carambola", currencyCode, currencySymbol, currencyConverter.getConversion("USD",
                                                                                                        currencyCode,
                                                                                                        3.55)));
        catalog.add(new Item("Cashew", currencyCode, currencySymbol, currencyConverter.getConversion("USD",
                                                                                                     currencyCode,
                                                                                                     1.55)));
    }

    public Item[] items() {
        Item[] catalogArray = new Item[catalog.size()];
        catalog.toArray(catalogArray);
        return catalogArray;
    }
}
