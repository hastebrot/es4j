/**
 * Copyright (c) 2016, All Contributors (see CONTRIBUTORS file)
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package com.eventsourcing.examples.order.commands;

import com.eventsourcing.EventStream;
import com.eventsourcing.Repository;
import com.eventsourcing.StandardCommand;
import com.eventsourcing.examples.order.events.NameChanged;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Accessors(fluent = true)
public class Rename extends StandardCommand<String, Void> {
    @Getter @Setter
    private UUID id;

    @Getter @Setter
    private String name;

    @Override
    public EventStream<Void> events(Repository repository) throws Exception {
        return EventStream.of(new NameChanged(id, name));
    }

    @Override
    public String onCompletion() {
        return name;
    }
}
