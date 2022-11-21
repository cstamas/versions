package org.codehaus.mojo.versions.reporting.model;

/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

import java.util.Map;
import java.util.TreeMap;
import org.codehaus.mojo.versions.api.Property;
import org.codehaus.mojo.versions.api.PropertyVersions;
import org.codehaus.mojo.versions.utils.PropertyComparator;

/**
 * Model class for using with the {@linkplain org.codehaus.mojo.versions.api.ReportRenderer} API
 */
public class PropertyUpdatesModel
{
    private final Map<Property, PropertyVersions> allUpdates;

    public PropertyUpdatesModel( PropertyComparator comparator, Map<Property, PropertyVersions> propertyUpdates )
    {
        this.allUpdates = new TreeMap<>( comparator );
        this.allUpdates.putAll( propertyUpdates );
    }

    public Map<Property, PropertyVersions> getAllUpdates()
    {
        return allUpdates;
    }

}
