/**
 * Copyright (C) 2012 tachibanakikaku.com Limited.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.tachibanakikaku.webscripts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.alfresco.service.cmr.repository.StoreRef;
import org.alfresco.service.cmr.search.ResultSet;
import org.alfresco.service.cmr.search.SearchParameters;
import org.alfresco.service.cmr.search.SearchService;
import org.springframework.extensions.webscripts.Cache;
import org.springframework.extensions.webscripts.DeclarativeWebScript;
import org.springframework.extensions.webscripts.Status;
import org.springframework.extensions.webscripts.WebScriptRequest;

public class HelloMaven extends DeclarativeWebScript {

    private SearchService searchService;

    public void setSearchService(SearchService searchService) {
        this.searchService = searchService;
    }

    @Override
    protected Map<String, Object> executeImpl(WebScriptRequest req,
            Status status, Cache cache) {
        Map<String, Object> model = new HashMap<String, Object>();
        SearchParameters params = new SearchParameters();
        params.addStore(new StoreRef(StoreRef.PROTOCOL_WORKSPACE, "SpacesStore"));
        params.setLanguage(SearchService.LANGUAGE_LUCENE);
        StringBuffer q = new StringBuffer("+PATH:\"");
        q.append("/app:company_home/app:dictionary//*");
        q.append("\"");
        params.setQuery(q.toString());
        ResultSet results = searchService.query(params);
        List<String> nodes = new ArrayList<String>();
        for (int i = 0; i < results.length(); i++) {
            nodes.add(String.valueOf(results.getNodeRef(i)));
        }
        model.put("title", "--- Search Result ---");
        model.put("nodes", nodes);
        return model;
    }
}
