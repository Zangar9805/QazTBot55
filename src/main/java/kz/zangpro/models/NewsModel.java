package kz.zangpro.models;

public class NewsModel {
    private String headerText;
    private String text;
    private String urlText;

    public void setHeaderText(String headerText) {
        this.headerText = headerText;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setUrlText(String urlText) {
        this.urlText = urlText;
    }

    public String getHeaderText() {
        return headerText;
    }

    public String getText() {
        return text;
    }

    public String getUrlText() {
        return urlText;
    }
}
