package org.flauschhaus.traces.journal.entry;

import android.databinding.BindingConversion;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.NotNull;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.Date;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class JournalEntry implements Serializable {

    private static final long serialVersionUID = 930790846715306027L;

    @Id
    private Long id;

    @Index
    private Date date;

    private String text;

    private String highlight;

    @NotNull
    int rating;

    @Generated(hash = 220202348)
    public JournalEntry(Long id, Date date, String text, String highlight,
            int rating) {
        this.id = id;
        this.date = date;
        this.text = text;
        this.highlight = highlight;
        this.rating = rating;
    }

    @Generated(hash = 673702888)
    public JournalEntry() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getHighlight() {
        return this.highlight;
    }

    public void setHighlight(String highlight) {
        this.highlight = highlight;
    }

    public int getRating() {
        return this.rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @BindingConversion
    public static String convert(Date date) {
        return DateFormat.getDateInstance(DateFormat.MEDIUM).format(date);
    }
}
