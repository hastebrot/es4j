/**
 * Copyright (c) 2016, All Contributors (see CONTRIBUTORS file)
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package com.eventsourcing.examples.order.events;

import com.eventsourcing.StandardEvent;
import com.eventsourcing.annotations.Index;
import com.eventsourcing.index.SimpleAttribute;
import com.googlecode.cqengine.query.option.QueryOptions;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.UUID;

import static com.eventsourcing.index.IndexEngine.IndexFeature.EQ;

@AllArgsConstructor
@NoArgsConstructor
@Accessors(fluent = true)
public class OrderCancelled extends StandardEvent {

    @Getter @Setter
    private UUID id;

    @Index({EQ})
    public static final SimpleAttribute<OrderCancelled, UUID> REFERENCE_ID = new SimpleAttribute<OrderCancelled, UUID>(
            "referenceId") {
        public UUID getValue(OrderCancelled orderCancelled, QueryOptions queryOptions) {
            return orderCancelled.id();
        }
    };
}
