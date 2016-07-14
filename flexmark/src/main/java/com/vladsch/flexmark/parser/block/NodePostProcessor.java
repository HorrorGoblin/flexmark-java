package com.vladsch.flexmark.parser.block;

import com.vladsch.flexmark.node.Document;
import com.vladsch.flexmark.parser.PostProcessor;

public abstract class NodePostProcessor implements PostProcessor {
    /**
     * @param document the node to post-process
     * @return the result of post-processing, may be a modified {@code document} argument
     */
    @Override
    final public Document processDocument(Document document) {
       return document; 
    }
}