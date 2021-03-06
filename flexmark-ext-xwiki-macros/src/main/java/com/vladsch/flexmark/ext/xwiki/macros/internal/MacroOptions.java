package com.vladsch.flexmark.ext.xwiki.macros.internal;

import com.vladsch.flexmark.ext.xwiki.macros.MacroExtension;
import com.vladsch.flexmark.util.options.DataHolder;

class MacroOptions {
    public final boolean enableInlineMacros;
    public final boolean enableBlockMacros;
    public final boolean enableRendering;

    public MacroOptions(DataHolder options) {
        enableInlineMacros = MacroExtension.ENABLE_INLINE_MACROS.getFrom(options);
        enableBlockMacros = MacroExtension.ENABLE_BLOCK_MACROS.getFrom(options);
        enableRendering = MacroExtension.ENABLE_RENDERING.getFrom(options);
    }
}
