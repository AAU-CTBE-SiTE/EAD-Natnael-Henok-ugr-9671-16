package com.craft;

import lombok.Data;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/** Shows every collection type Spring XML can inject. */
@Data
public class CollectionBean {
    private List<Subject> list;          // <list>  - duplicates allowed, keeps order
    private Set<String> tags;            // <set>   - no duplicates
    private Subject[] subjectArray;      // <array> - Java array
    private Map<String, String> capitals; // <map>  - unique keys
    private Properties settings;         // <props> - String-only key/value
}
