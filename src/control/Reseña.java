package control;

public class Reseña {
    String comentario;
    int rating;
    Huesped autor;

    public Reseña(){

    }
    public Reseña(String comment, int rating, Huesped autor){
        this.comentario = comment;
        this.rating = rating;
        this.autor = autor;
    }

    //getters y setters
    public int getRating(){
        return rating;
    }

    boolean esValida(){

    }

    String resumen(){

    }
}
