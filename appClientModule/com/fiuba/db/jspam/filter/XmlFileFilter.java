package com.fiuba.db.jspam.filter;

import java.io.File;
import java.io.FileFilter;

/**
 * @author PNT
 *
 */
public class XmlFileFilter implements FileFilter {

    /**
     * {@inheritDoc}
     */
    public boolean accept(File pathname) {
        if ((pathname.isFile()) && (pathname.getName().endsWith(".xml"))) {
            return true;
        }

        return false;
    }

}
