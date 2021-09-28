package com.example.fit;

public class Modelanodya {
    String id, category, articletitle, typearticle;

    public Modelanodya(){

    }

    public Modelanodya(String id, String category, String articletitle, String typearticle){
        this.id = id;
        this.category = category;
        this.articletitle = articletitle;
        this.typearticle = typearticle;

    }

    public String getId() {
        return id;
    }

    public String getCategory() {
        return category;
    }

    public String getArticletitle() {
        return articletitle;
    }

    public String getTypearticle() {
        return typearticle;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setArticletitle(String articletitle) {
        this.articletitle = articletitle;
    }

    public void setTypearticle(String typearticle) {
        this.typearticle = typearticle;
    }
}
