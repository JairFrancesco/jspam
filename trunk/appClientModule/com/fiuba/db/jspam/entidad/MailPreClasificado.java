package com.fiuba.db.jspam.entidad;

/**
 * @author PNT
 *
 */
public class MailPreClasificado extends Mail {
    private boolean spam;
    

    /**
     * @return the spam.
     */
    public boolean isSpam() {
        return spam;
    }

    /**
     * @param spam
     *            the spam to set.
     */
    public void setSpam(boolean spam) {
        this.spam = spam;
    }
}
